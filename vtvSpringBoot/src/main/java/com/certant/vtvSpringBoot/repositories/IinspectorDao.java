package com.certant.vtvSpringBoot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Inspector;

@Repository
public interface IinspectorDao extends JpaRepository<Inspector, Long>{
	
	@Query("SELECT i FROM Inspector i WHERE i.id_inspector=(:id)")
	Optional<Inspector> buscarPorId(@Param("id") long id);

}
