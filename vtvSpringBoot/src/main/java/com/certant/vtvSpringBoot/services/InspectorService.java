package com.certant.vtvSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certant.vtvSpringBoot.domain.Inspector;
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

	public Inspector findById(long id) {
		
		return inspectorDao.buscarPorId(id).orElse(null);
	}

	public Inspector Buscar(long dni){
		
		return inspectorDao.findById(dni).orElse(null);
	}

	public void save(Inspector insp) {
		inspectorDao.save(insp);
	}

	public void eliminar(long id) {
//		var insp= inspectorDao.buscarPorId(id);
		inspectorDao.deleteById(id);
		
		
	}

}
