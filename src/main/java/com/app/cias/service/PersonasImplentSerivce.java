package com.app.cias.service;

import com.app.cias.excepciones.ResourceNotFoundException;
import com.app.cias.model.Persona;
import com.app.cias.repository.PersonaRepository;
import com.app.cias.service.dtos.PersonaResponseDTO;
import com.app.cias.service.mappers.PersonaMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service()
public class PersonasImplentSerivce implements PersonaService{
	private final Logger log = LoggerFactory.getLogger(PersonasImplentSerivce.class);
	private final PersonaRepository repositorio;
	private final PersonaMapper mapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public PersonasImplentSerivce(PersonaRepository repositorio, PersonaMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repositorio = repositorio;
		this.mapper = mapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public List<PersonaResponseDTO> listAll() {
		return this.repositorio.findAll().stream().map(this.mapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public Persona save(Persona persona) {
		// TODO Auto-generated method stub
		persona.setPassword(bCryptPasswordEncoder.encode(persona.getPassword()));
		return this.repositorio.save(persona);
	}

	@Override
	public ResponseEntity<PersonaResponseDTO> findById(Long id) {
		Persona peson = this.repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+id)));
		return ResponseEntity.ok(this.mapper.toResponseDTO(peson));
	}

	@Override
	public ResponseEntity<Persona> updatePersona(Long id, Persona detalles) {
		Persona personaVieja = this.repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+id)));

		personaVieja.setEstatus(detalles.getEstatus());
		personaVieja.setNombre(detalles.getNombre());
		personaVieja.setPrimer_apellido(detalles.getPrimer_apellido());
		personaVieja.setSegundo_apellido(detalles.getSegundo_apellido());
		personaVieja.setTelefono(detalles.getTelefono());
		personaVieja.setFecha_upd(ZonedDateTime.now());

		Persona actualizada = this.repositorio.save(personaVieja);
		return ResponseEntity.ok(actualizada);
	}

    /*@Override
	public ResponseEntity<Map<String,Boolean>>  deletePersona(Long id) {
		Persona personaVieja = this.repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+ id)));
		
		this.repositorio.delete(personaVieja);
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}*///Lo comente porque si borra al usuario y lo que quiero es solo desactivarlo

	@Override
	public ResponseEntity<Map<String,Boolean>>  deletePersona(Long id) {
		Persona personaVieja = this.repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+ id)));

		personaVieja.setEstatus("I");
		this.repositorio.save(personaVieja);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

	@Override
	public Optional<Persona> findByEmail(String email) {
		return this.repositorio.findByEmail(email);
	}

}
