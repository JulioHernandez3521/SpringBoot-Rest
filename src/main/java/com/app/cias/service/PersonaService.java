package com.app.cias.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.app.cias.model.*;

public interface PersonaService {
	 List<Persona> listAll();
	 Persona save(Persona persona);
	 ResponseEntity<Persona>getPersonaById(Long id);
	 ResponseEntity<Persona>updatePersona(Long id, Persona detalles);
	 ResponseEntity<Map<String,Boolean>>  deletePersona(Long id);
	 Optional<Persona>  findByEmail (String email);
	
}
