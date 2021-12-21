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
//	@NotEmpty(message="Ingrese el marca por favor!")
	private Marca marca;
	@Column(name = "modelo")
//	@NotEmpty(message="Ingrese el modelo por favor!")
	private Modelo modelo;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="id_prop", referencedColumnName = "id")
	//@NotNull(message="Ingrese un numero de propietario por favor!")
	private Propietario propietario;
	@OneToMany( mappedBy="vehiculo")
	//@JoinColumn(name="dni_prop")
	private Set<Inspeccion> inspecciones;
	



	

	public Vehiculo(Long id, @NotEmpty(message = "Ingrese un numero de patente por favor!") String dominio,
			 @NotEmpty(message = "Ingrese el modelo por favor!") Marca marca,
			 @NotEmpty(message = "Ingrese el modelo por favor!") Modelo modelo, Propietario propietario) {
		super();
		this.id = id;
		this.dominio = dominio;
		this.marca = marca;
		this.setModelo(modelo);
		this.propietario = propietario;
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
	public  Marca getMarca() {
		return marca;
	}


	public   Modelo getModelo() {
		return modelo;
	}



	public void setMarca(Marca marca) {
		this.marca = marca;
	}


	public void setModelo(Modelo modelo) {
		
		 if(Marca.isMarcaModeloCorrecto(modelo, this.marca)) this.modelo = modelo;
		 else System.out.print("////////////////////////////////ERROR MODELO NO MATCHEA CON MARCA////////////////////////////////////////////////");	 //tirar excepcion ERRROR NO EXISTE UN MODELO CON ESA MARCA
		 
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
		return "Vehiculo [id=" + id + ", dominio=" + dominio + ", marca=" + modelo + ", modelo=" + modelo
				+ ", propietario=" + propietario.getDni() + ", inspecciones=" + inspecciones + "]";
	}

	
	
}
