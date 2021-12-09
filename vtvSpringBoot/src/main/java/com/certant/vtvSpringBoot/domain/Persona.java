package com.certant.vtvSpringBoot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;


@Entity
//lo sig es para que no haya problemas con la diferencia de nombres entre la clase y la tabla
/*
 *VER COMO FUNCIONAN LAS @:
 *		UNIQUE CONSTRAINT
 *		INHERITANCE	 
 *		PRIMARY KEY JOIN COLUMN
 * 
 * */
@Table(name="persona", uniqueConstraints = { @UniqueConstraint(columnNames = { "dni" }) })
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long dni;
	
	@Column(name = "nombre")
	@NotEmpty(message="Ingrese el nombre por favor!")
	private String nombre;
	
	@Column(name = "apellido")
	@NotEmpty(message="Ingrese el apellido por favor!")
	private String apellido;

	public Persona() {};
	
	public Persona(Long dni, @NotEmpty(message = "Ingrese el nombre por favor!") String nombre,
			@NotEmpty(message = "Ingrese el apellido por favor!") String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/*public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}*/
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}

}
