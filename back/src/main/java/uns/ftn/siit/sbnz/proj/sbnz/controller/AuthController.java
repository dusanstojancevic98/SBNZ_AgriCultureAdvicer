package uns.ftn.siit.sbnz.proj.sbnz.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import uns.ftn.siit.sbnz.proj.sbnz.dto.JwtResponseDto;
import uns.ftn.siit.sbnz.proj.sbnz.dto.LoginDto;
import uns.ftn.siit.sbnz.proj.sbnz.dto.RegisterDto;
import uns.ftn.siit.sbnz.proj.sbnz.model.Korisnik;
import uns.ftn.siit.sbnz.proj.sbnz.service.AuthService;
import uns.ftn.siit.sbnz.proj.sbnz.service.KorisnikService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	public KorisnikService korisnikService;

	@Autowired
	public AuthService service;


	@PostMapping("/register")
	public ResponseEntity<Korisnik> register(@RequestBody RegisterDto dto) {
		System.out.println("register = " + dto);
		try {
			return new ResponseEntity<>(service.register(new Korisnik(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getEmail(), dto.getPassword())), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto dto) {
		System.out.println("login user = " + dto);
		JwtResponseDto jwt = service.logIn(dto);
		if (jwt != null) {
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
