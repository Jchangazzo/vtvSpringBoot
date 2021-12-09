package com.certant.vtvSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.certant.vtvSpringBoot.domain.Persona;

@Repository//("personaDao")
public interface IPersonaDao extends JpaRepository<Persona, Long>{
	
}
