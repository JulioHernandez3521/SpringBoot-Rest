package com.app.cias.model;

import java.time.ZonedDateTime;

import jakarta.persistence.*;

@Entity()
@Table(name = "Personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="nombre", length = 100, nullable = false )
	private String nombre;
	
	@Column(name ="primer_apellido", length = 100, nullable = false )
	private String primer_apellido;

	@Column(name ="segundo_apellido", length = 100, nullable = false )
	private String segundo_apellido;

	@Column(name ="telefono", length = 10, nullable = false )
	private String telefono;

	@Column(name ="estatus", length = 1, nullable = false )
	private String estatus;
	@Column(name ="email", length = 100, nullable = false, unique = true)
	private String email;
	@Column(name ="password", nullable = false)
	private String password;
	@Column(name ="fecha_ins")
	private ZonedDateTime fecha_ins = ZonedDateTime.now();
	
	@Column(name ="fecha_upd", nullable = true )
	private ZonedDateTime fecha_upd;
	
	public Persona (){}

	public Persona(Long id, String nombre, String primer_apellido, String segundo_apellido, String telefono, String estatus, String email, String password, ZonedDateTime fecha_ins, ZonedDateTime fecha_upd) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.telefono = telefono;
		this.estatus = estatus;
		this.email = email;
		this.password = password;
		this.fecha_ins = fecha_ins;
		this.fecha_upd = fecha_upd;
	}

	public Persona(String nombre, String primer_apellido, String segundo_apellido, String telefono, String estatus, String email, String password, ZonedDateTime fecha_ins) {
		super();
		this.nombre = nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.telefono = telefono;
		this.estatus = estatus;
		this.email = email;
		this.password = password;
		this.fecha_ins = fecha_ins;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}
	

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public ZonedDateTime getFecha_ins() {
		return fecha_ins;
	}

	public void setFecha_ins(ZonedDateTime fecha_ins) {
		this.fecha_ins = fecha_ins;
	}

	public ZonedDateTime getFecha_upd() {
		return fecha_upd;
	}

	public void setFecha_upd(ZonedDateTime fecha_upd) {
		this.fecha_upd = fecha_upd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
