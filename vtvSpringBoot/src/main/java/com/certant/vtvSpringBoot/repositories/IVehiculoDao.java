package com.certant.vtvSpringBoot.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Vehiculo;

@Repository
public interface IVehiculoDao extends JpaRepository<Vehiculo, Long>{
	/*QUE HACE ESTO POR ABAJO?*/
	@Query("SELECT v FROM Vehiculo v INNER JOIN FETCH v.propietario p WHERE p.dni= (:dni)")
	public abstract Set<Vehiculo> buscarVehiculosPorDni(@Param("dni") Long dni);

}
