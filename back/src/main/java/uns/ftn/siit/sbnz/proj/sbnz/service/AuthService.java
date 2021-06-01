package uns.ftn.siit.sbnz.proj.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uns.ftn.siit.sbnz.proj.sbnz.config.JwtTokenUtil;
import uns.ftn.siit.sbnz.proj.sbnz.dto.JwtResponseDto;
import uns.ftn.siit.sbnz.proj.sbnz.dto.LoginDto;
import uns.ftn.siit.sbnz.proj.sbnz.exceptions.ResourceExistsException;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.repository.KorisnikRepository;


@Service
public class AuthService {

	@Autowired
	KorisnikRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public Korisnik register(Korisnik entity) {

		System.out.println(passwordEncoder.encode(entity.getPassword()));

		if(repository.findByEmail(entity.getEmail()) != null) {
			throw new ResourceExistsException("email already exist");
		}
		if(repository.findByUsername(entity.getUsername()) != null) {
			throw new ResourceExistsException("username already exist");
		}
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		System.out.println("register user = " + entity);

		return repository.save(entity);
	}
	
	public JwtResponseDto logIn(LoginDto authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException | InternalAuthenticationServiceException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Bad credentials.");
		}
		final Korisnik userDetails = (Korisnik) jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return new JwtResponseDto(jwt);
	}
	
}
