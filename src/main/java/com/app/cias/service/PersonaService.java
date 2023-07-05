package com.app.cias.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.app.cias.service.dtos.PersonaResponseDTO;
import org.springframework.http.ResponseEntity;

import com.app.cias.model.*;

public interface PersonaService {
	 ResponseEntity<?> listAll();
	 ResponseEntity<?> save(Persona persona);
	 ResponseEntity<?>findById(Long id);
	 ResponseEntity<?> updatePersona(Long id, Persona detalles);
	 ResponseEntity<?>  deletePersona(Long id);
	 Optional<?>  findByEmail (String email);
	
}
