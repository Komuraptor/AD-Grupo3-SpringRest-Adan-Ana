package com.gestioncursos.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestioncursos.service.impl.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@PostMapping("/login/")
	public com.gestioncursos.entity.User login(@RequestParam("user") String username, @RequestParam("password") String password) {
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = getJWTToken(username);
		
		com.gestioncursos.entity.User usuario = new com.gestioncursos.entity.User();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setToken(token);
		return usuario;
	}
	
	@PostMapping("/register")
	public com.gestioncursos.entity.User saveUser(@RequestBody com.gestioncursos.entity.User user) {
		return userService.registrar(user);
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
