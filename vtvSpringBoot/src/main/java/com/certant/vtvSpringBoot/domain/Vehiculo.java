package com.certant.vtvSpringBoot.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="vehiculo")//, uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Vehiculo {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "dominio")
	@NotEmpty(message="Ingrese un numero de patente por favor!")
	private String dominio;
	@Column(name = "marca")
	@NotEmpty(message="Ingrese el marca por favor!")
	private String marca;
	@Column(name = "modelo")
	@NotEmpty(message="Ingrese el modelo por favor!")
	private String modelo;


	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="dni_prop", referencedColumnName = "dni")
	//@NotEmpty(message="Ingrese un numero de propietario por favor!")
	private Propietario propietario;
	
	@OneToMany( mappedBy="vehiculo")
	//@JoinColumn(name="dni_prop")
	private Set<Inspeccion> inspecciones;
	


	

	

	public Vehiculo(Long id, @NotEmpty(message = "Ingrese un numero de patente por favor!") String dominio,
			@NotEmpty(message = "Ingrese el marca por favor!") String marca,
			@NotEmpty(message = "Ingrese el modelo por favor!") String modelo, Propietario propietario) {
		super();
		this.id = id;
		this.dominio = dominio;
		this.marca = marca;
		this.modelo = modelo;
		//this.propietario = propietario;
	}


	public Propietario getPropietario() {
		return propietario;
	}
	
	
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}


	/**
	 * 
	 * 
	 */
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String domino) {
		this.dominio = domino;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Set<Inspeccion> getInspecciones() {
		return inspecciones;
	}


	public void setInspecciones(Set<Inspeccion> inspecciones) {
		this.inspecciones = inspecciones;
	}


	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", dominio=" + dominio + ", marca=" + marca + ", modelo=" + modelo
				+ ", propietario=" + propietario + ", inspecciones=" + inspecciones + "]";
	}

	
	
}
