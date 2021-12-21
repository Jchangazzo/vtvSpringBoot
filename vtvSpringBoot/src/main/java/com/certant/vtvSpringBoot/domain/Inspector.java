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
@Table(name="inspector")
@PrimaryKeyJoinColumn(referencedColumnName = "dni")
public class Inspector extends Persona{

	/**TODO 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dni")
	private Persona persona;
	
	@OneToMany( mappedBy="inspector")
	//@JoinColumn(name="dni_prop")
	private Set<Inspeccion> inspecciones;
	
	@Column(name = "id_inspector")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_inspector;

	
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Long getId_inspector() {
		return id_inspector;
	}

	public void setId_inspector(Long id_inspector) {
		this.id_inspector = id_inspector;
	}

	public Set<Inspeccion> getInspecciones() {
		return inspecciones;
	}

	public void setInspecciones(Set<Inspeccion> inspecciones) {
		this.inspecciones = inspecciones;
	}

	public Inspector() {

	}


	public Inspector(Long dni, @NotEmpty(message = "Ingrese el nombre por favor!") String nombre,
			@NotEmpty(message = "Ingrese el apellido por favor!") String apellido) {
		
		super(dni, nombre, apellido);
	}

	@Override
	public String toString() {
		return "Inspector [persona=" + persona.getDni()+" "+ persona.getApellido()
				+" "+ persona.getNombre() + ", inspecciones=" + inspecciones + "]";
	}

	

}
