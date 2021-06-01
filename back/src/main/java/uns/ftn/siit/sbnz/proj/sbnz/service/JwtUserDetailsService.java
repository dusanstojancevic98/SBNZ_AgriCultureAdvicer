package uns.ftn.siit.sbnz.proj.sbnz.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.repository.KorisnikRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	KorisnikRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername = " + username);
		Korisnik user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}

}
