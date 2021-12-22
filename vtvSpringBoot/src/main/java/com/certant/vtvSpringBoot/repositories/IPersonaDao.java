package com.certant.vtvSpringBoot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.certant.vtvSpringBoot.domain.Persona;

@Repository//("personaDao")
public interface IPersonaDao extends JpaRepository<Persona, Long>{
//	@Modifying
//	@Query(value="INSERT INTO Persona (dni,apellido,nombre) VALUES (:dni,:nombre,:apellido) ON DUPLICATE KEY UPDATE",nativeQuery = true)
//	void guardarOActualizar(@Param("dni") Long dni,@Param("nombre") String apellido,@Param("apellido") String nombre);
	@Query("SELECT p FROM Persona p WHERE p.dni=(:dni)")
	Optional<Persona> buscarPorDni(long dni);
	
}
