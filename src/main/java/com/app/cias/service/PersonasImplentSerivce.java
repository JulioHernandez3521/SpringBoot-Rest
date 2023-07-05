package com.app.cias.service;

import com.app.cias.model.Persona;
import com.app.cias.repository.PersonaRepository;
import com.app.cias.service.dtos.PersonaResponseDTO;
import com.app.cias.service.mappers.PersonaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.app.cias.excepciones.BadALertRequest.BadALertRequest;
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
	public ResponseEntity<List<PersonaResponseDTO>> listAll() {
		return new ResponseEntity<>(this.repositorio.findAll().stream().map(this.mapper::toResponseDTO).collect(Collectors.toList()),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> save(Persona persona) {
		// TODO Auto-generated method stub
		Map<String, Object> response = new HashMap<>();
		Persona creada = null;
		try {
			persona.setPassword(bCryptPasswordEncoder.encode(persona.getPassword()));
			creada = this.repositorio.save(persona);
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			log.error(e.getMessage());
			log.error(e.getCause().getMessage());
			return BadALertRequest("Ocurrio un error al guardar a la persona",
					PersonasImplentSerivce.class.getName(),
					e.getCause().getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message","Persona creada");
		response.put("persona",creada);
		return new ResponseEntity<>(response,HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> findById(Long id) {
		Map<String, Object> response = new HashMap<>();
		Persona peson = this.repositorio.findById(id).orElse(null);
		if(peson == null) return BadALertRequest("No existe una persona con el id "+id, Persona.class.getName(),"NotFound",HttpStatus.NOT_FOUND);
		response.put("message","Success");
		response.put("persona", this.mapper.toResponseDTO(peson));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?>  updatePersona(Long id, Persona detalles) {

		Persona actualizada = null;
		Map<String, Object> response = new HashMap<>();

		Persona personaVieja = this.repositorio.findById(id)
				.orElse(null);

		if(personaVieja == null) return BadALertRequest("No existe una persona con el id "+id,
				Persona.class.getName(),"NotFound",HttpStatus.NOT_FOUND);

		try {
			personaVieja.setEstatus(detalles.getEstatus());
			personaVieja.setNombre(detalles.getNombre());
			personaVieja.setPrimer_apellido(detalles.getPrimer_apellido());
			personaVieja.setSegundo_apellido(detalles.getSegundo_apellido());
			personaVieja.setTelefono(detalles.getTelefono());
			personaVieja.setFecha_upd(ZonedDateTime.now());
			personaVieja.setEmail(detalles.getEmail());

			if(detalles.getPassword() != null &&  !detalles.getPassword().isEmpty())
				personaVieja.setPassword(bCryptPasswordEncoder.encode(detalles.getPassword()));

			actualizada = this.repositorio.save(personaVieja);

		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			log.error(e.getMessage());
			log.error(e.getCause().getMessage());
			return BadALertRequest("Ocurrio un error al actualziar a la persona",
					PersonasImplentSerivce.class.getName(),
					e.getCause().getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message","Persona actualizada");
		response.put("persona",actualizada);
		return ResponseEntity.ok(response);
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
	public ResponseEntity<?>  deletePersona(Long id) {

		Map<String, Object> response = new HashMap<>();
		Persona personaVieja = this.repositorio.findById(id)
				.orElse(null);

		if(personaVieja == null) return BadALertRequest("No existe una persona con el id "+id,
				Persona.class.getName(),"NotFound",HttpStatus.NOT_FOUND);

		try {
			personaVieja.setEstatus("I");
			this.repositorio.save(personaVieja);
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			log.error(e.getMessage());
			log.error(e.getCause().getMessage());
			return BadALertRequest("Ocurrio un error al actualziar a la persona",
					PersonasImplentSerivce.class.getName(),
					e.getCause().getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
			response.put("message","Persona desactivada correctamente");
			return ResponseEntity.ok(response);
		}

		@Override
		public Optional<Persona> findByEmail(String email) {
			return this.repositorio.findByEmail(email);
		}

}
