package com.certant.vtvSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certant.vtvSpringBoot.domain.Inspector;
import com.certant.vtvSpringBoot.domain.Persona;
import com.certant.vtvSpringBoot.repositories.IinspectorDao;
@Service
public class InspectorService {
	
	@Autowired
	//@Qualifier("personaDao")
	private IinspectorDao inspectorDao;

	
	//@Transactional(readOnly = true)
	public List<Inspector> getAll() {
		
		return inspectorDao.findAll();
	}

	public Persona findByDni(long dni) {
		
		return null;
	}

	public Inspector Buscar(long dni){
		
		return inspectorDao.findById(dni).orElse(null);
	}

	public void save(Inspector insp) {
		
		inspectorDao.save(insp);
		
	}

	public void eliminar(long dni) {
		inspectorDao.deleteById(dni);
		
		
	}

}
