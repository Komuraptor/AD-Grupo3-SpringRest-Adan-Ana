package com.gestioncursos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestioncursos.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public com.gestioncursos.entity.User registrar(com.gestioncursos.entity.User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.gestioncursos.entity.User usuario = userRepository.findByUsername(username);
		
		UserBuilder builder = null;
		
		if(usuario!=null) {
			builder=User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRole()));
		}
		else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return builder.build();
	}

	public com.gestioncursos.entity.User findUsuario(String username) {
		return userRepository.findByUsername(username);
	}
}
