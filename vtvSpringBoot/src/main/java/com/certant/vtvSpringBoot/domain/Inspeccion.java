package com.certant.vtvSpringBoot.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="inspeccion")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Inspeccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="fecha")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Ingrese el número por favor, el mismo consta de 7 a 8 dígitos")
	private LocalDate fecha;
	
	@Column(name="estado")
	@NotNull(message = "Ingrese el estado por favor")
	private Estado estado;
	
	@Column(name="exento")
	@NotNull(message = "Ingrese el si es exento el vehiculo por favor")
	private boolean exento;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="inspector_dni", referencedColumnName="dni")
	//@NotEmpty(message="Ingrese un numero de inspector por favor!")
	private Inspector inspector;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="vehiculo_id", referencedColumnName="id")
	//@NotEmpty(message="Ingrese un numero de vehiculo por favor!")
	private Vehiculo vehiculo;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isExento() {
		return exento;
	}

	public void setExento(boolean exento) {
		this.exento = exento;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Inspeccion [id=" + id + ", fecha=" + fecha + ", estado=" + estado + ", exento=" + exento
				+ ", inspector=" + inspector.getDni() + ", vehiculo=" + vehiculo.getId() + "]";
	}
}
