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
	@Autowired
	private PersonaRepository repositorio;
	private PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);

	@Override
	public List<PersonaResponseDTO> listAll() {
		return repositorio.findAll().stream().map(p -> mapper.toResponseDTO(p) ).collect(Collectors.toList());
	}

	@Override
	public Persona save(Persona persona) {
		// TODO Auto-generated method stub
		return repositorio.save(persona);
	}

	@Override
	public ResponseEntity<Persona> getPersonaById(Long id) {
		Persona peson = repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+id)));
		return ResponseEntity.ok(peson);
	}

	@Override
	public ResponseEntity<Persona> updatePersona(Long id, Persona detalles) {
		Persona personaVieja = repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+id)));

		personaVieja.setEstatus(detalles.getEstatus());
		personaVieja.setNombre(detalles.getNombre());
		personaVieja.setPrimer_apellido(detalles.getPrimer_apellido());
		personaVieja.setSegundo_apellido(detalles.getSegundo_apellido());
		personaVieja.setTelefono(detalles.getTelefono());
		personaVieja.setFecha_upd(ZonedDateTime.now());

		Persona actualizada = repositorio.save(personaVieja);
		return ResponseEntity.ok(actualizada);
	}

    /*@Override
	public ResponseEntity<Map<String,Boolean>>  deletePersona(Long id) {
		Persona personaVieja = repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+ id)));
		
		repositorio.delete(personaVieja);
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}*///Lo comente porque si borra al usuario y lo que quiero es solo desactivarlo

	@Override
	public ResponseEntity<Map<String,Boolean>>  deletePersona(Long id) {
		Persona personaVieja = repositorio.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(("No existe una persona con el id "+ id)));

		personaVieja.setEstatus("I");
		repositorio.save(personaVieja);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

	@Override
	public Optional<Persona> findByEmail(String email) {
		return this.repositorio.findByEmail(email);
	}

}
