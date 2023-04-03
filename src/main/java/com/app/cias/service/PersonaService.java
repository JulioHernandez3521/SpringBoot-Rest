package com.app.cias.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.app.cias.model.*;

public interface PersonaService {
	public List<Persona> listAll();
	public Persona save(Persona persona);
	public ResponseEntity<Persona>getPersonaById(Long id);
	public ResponseEntity<Persona>updatePersona(Long id, Persona detalles);
	public ResponseEntity<Map<String,Boolean>>  deletePersona(Long id);
	
}
