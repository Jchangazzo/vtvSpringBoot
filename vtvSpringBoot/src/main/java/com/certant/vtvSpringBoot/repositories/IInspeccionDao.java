package com.certant.vtvSpringBoot.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Inspeccion;

@Repository
public interface IInspeccionDao extends JpaRepository<Inspeccion, Long>{
	
	@Query("SELECT i FROM Inspeccion i INNER JOIN FETCH i.inspector p WHERE p.dni= (:dni)")
	public abstract Set<Inspeccion> buscarInspeccionesPorDni(@Param("dni") Long dni);

}
