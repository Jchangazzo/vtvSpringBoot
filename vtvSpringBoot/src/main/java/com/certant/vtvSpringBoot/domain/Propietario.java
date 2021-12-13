package com.certant.vtvSpringBoot.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="propietario")
@PrimaryKeyJoinColumn(referencedColumnName = "dni")
public class Propietario extends Persona{


	private static final long serialVersionUID = 1L;
	//TODO
	//HACE FALTA DEFINIR UN ATRIBUTO PRIVADO PERSONA? O YA EL HECHO DE QUE LO EXTIENDA ES SUFICIENTE???
	//COMO HAGO PARA HACER EL JOIN SIN DEFINIR EL ATRIBUTO ENTONCES????? SI HACE FALTA
	//VER COMO FUNCIONA ONE TO ONE ONE TO MANY ETC, USE ONETOONE PORQUE EL ERROR QUE TIRABA DECIA QUE 
	//SOLO LO PODIA USAR SI PERSONA ERA UNA COLECCION DE COSAS PUES UN PROPIETARIO ESTA ASOCIADO SOLO A UNA PERSONA
	//TEMA FECHAS, TEMA RELACIONES DE AUTOS E INSPECCIONES, 
	//private static final long serialVersionUID = 1L;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dni")
	private Persona persona;
	


	@OneToMany( mappedBy="propietario")
	//@JoinColumn(name="dni_prop")
	private Set<Vehiculo> vehiculos;

	
	@Column(name = "mail")
	private String mail;



	public Propietario() {
		// TODO Auto-generated constructor stub
	}

	public Propietario(Long dni, @NotEmpty(message = "Ingrese el nombre por favor!") String nombre,
			@NotEmpty(message = "Ingrese el apellido por favor!") String apellido) {
		super(dni, nombre, apellido);
		// TODO Auto-generated constructor stub
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Persona getPersona() {
		return persona;
	}
	
	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
}
