package com.certant.vtvSpringBoot.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.certant.vtvSpringBoot.domain.Persona;
import com.certant.vtvSpringBoot.repositories.IPersonaDao;

@Service
//@Transactional(readOnly = true)
public class PersonaService {
	
	@Autowired
	//@Qualifier("personaDao")
	private IPersonaDao personaDao;

	
	//@Transactional(readOnly = true)
	public List<Persona> getAll() {
		
		return personaDao.findAll();
	}

	public Persona findByDni(long dni) {
		
		return null;
	}

	public Persona Buscar(long dni){
		
		return personaDao.findById(dni).orElse(null);
	}

	public void save(Persona persona) {
		
		personaDao.save(persona);
		
	}

	public void eliminar(long dni) {
		personaDao.deleteById(dni);
		
		
	}
	


}
