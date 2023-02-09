package com.gestioncursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class GestionCursosGrupo3SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCursosGrupo3SpringRestApplication.class, args);
	}

}
