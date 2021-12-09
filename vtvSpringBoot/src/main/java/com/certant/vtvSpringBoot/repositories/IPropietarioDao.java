package com.certant.vtvSpringBoot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Propietario;
@Repository
public interface IPropietarioDao extends JpaRepository<Propietario, Long>{
	/*VER COMO HACER CUSTOMS QUERIES, VER COMO PASAR LOS PARAMETROS*/
	/*@Query("SELECT v FROM vehiculo v WHERE v.dni_prop=:dni")
	public abstract List<Vehiculo> vehiculos(@Param("dni") Long dni);*/
	
}
