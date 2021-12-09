package com.certant.vtvSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certant.vtvSpringBoot.domain.Inspector;

@Repository
public interface IinspectorDao extends JpaRepository<Inspector, Long>{

}
