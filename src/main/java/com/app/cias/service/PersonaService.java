package com.app.cias.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.app.cias.service.dtos.PersonaResponseDTO;
import org.springframework.http.ResponseEntity;

import com.app.cias.model.*;

public interface PersonaService {
	 List<PersonaResponseDTO> listAll();
	 Persona save(Persona persona);
	 ResponseEntity<PersonaResponseDTO>findById(Long id);
	 ResponseEntity<Persona>updatePersona(Long id, Persona detalles);
	 ResponseEntity<Map<String,Boolean>>  deletePersona(Long id);
	 Optional<Persona>  findByEmail (String email);
	
}
