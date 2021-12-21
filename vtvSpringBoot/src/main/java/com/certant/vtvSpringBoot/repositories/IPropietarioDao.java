package com.certant.vtvSpringBoot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Propietario;
@Repository
public interface IPropietarioDao extends JpaRepository<Propietario, Long>{
//	
//	@Query("SELECT a FROM Propietario a INNER JOIN FETCH a.persona b WHERE b.dni=(:dni)")
//	public abstract Optional<Propietario> findByDni(@Param("dni") long dni);

	
}
