package com.app.cias.controllers;

import com.app.cias.model.Persona;
import com.app.cias.service.PersonaService;
import com.app.cias.service.PersonasImplentSerivce;
import com.app.cias.service.dtos.PersonaResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1")
public class PersonasController {
	private final Logger log = LoggerFactory.getLogger(PersonasImplentSerivce.class);
	@Autowired
	private PersonaService servicio;
	
	@GetMapping("/personas")
	public ResponseEntity<?> ListaAll(){
		log.info("Get All Request");
		return servicio.listAll();
	}
	
	@PostMapping("/personas")
	public ResponseEntity<?> save(@RequestBody Persona persona) {
		return servicio.save(persona);
	}
	
	@GetMapping("/personas/{id}")
	public ResponseEntity<?> getById(@PathVariable long id){
		return servicio.findById(id);
	}
	
	@PutMapping("/personas/{id}")
	public ResponseEntity<?> pdatePersona(@PathVariable long id,@RequestBody Persona persona ){
		return servicio.updatePersona(id, persona);
	}
	
	
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable long id){
		return servicio.deletePersona(id);
	}
	
	
	
}
