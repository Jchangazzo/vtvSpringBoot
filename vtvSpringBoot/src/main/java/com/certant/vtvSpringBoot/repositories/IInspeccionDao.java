package com.certant.vtvSpringBoot.repositories;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Estado;
import com.certant.vtvSpringBoot.domain.Inspeccion;

@Repository
public interface IInspeccionDao extends JpaRepository<Inspeccion, Long>{
	//cambiado de dni a id, probarlo
	@Query("SELECT i FROM Inspeccion i INNER JOIN FETCH i.inspector p WHERE p.id_inspector= (:id)")
	public abstract Set<Inspeccion> buscarInspeccionesPorId(@Param("id") Long id);
	
	@Query("SELECT i FROM Inspeccion i INNER JOIN FETCH i.vehiculo v WHERE v.id= (:id)")
	public abstract Set<Inspeccion> buscarInspeccionesPorAuto(Long id);

	@Query("SELECT i FROM Inspeccion i WHERE i.estado= (:estado)")
	public abstract Set<Inspeccion> buscarPorEstado(@Param("estado") Estado estado);

	@Query("SELECT i FROM Inspeccion i WHERE i.fecha>=(:desde) AND i.fecha<= (:hasta)")
	public abstract Set<Inspeccion> buscarPorFecha(@Param("desde") LocalDate desde,@Param("hasta") LocalDate hasta);
	
//	@Query("SELECT i FROM Inspeccion i WHERE i.estado= 'APTO'")
//	public abstract Set<Inspeccion> buscarAptos();
//	
//	@Query("SELECT i FROM Inspeccion i WHERE i.estado= 'CONDICIONAL'")
//	public abstract Set<Inspeccion> buscarCondicionales();
//	
//	@Query("SELECT i FROM Inspeccion i WHERE i.estado= 'RECHAZADO'")
//	public abstract Set<Inspeccion> buscarRechazados();

}
