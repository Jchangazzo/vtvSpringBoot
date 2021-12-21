package com.certant.vtvSpringBoot.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certant.vtvSpringBoot.domain.Vehiculo;
import com.certant.vtvSpringBoot.repositories.IVehiculoDao;

@Service
public class VehiculoService {
	
	@Autowired
	private IVehiculoDao VehiculoDao;
	public List<Vehiculo> getAll() {

		return VehiculoDao.findAll();
	}



	public Vehiculo Buscar(Long id){
		
		return VehiculoDao.findById(id).orElse(null);
	}
	
	public Vehiculo BuscarPorPatente(String dominio){
		
		return VehiculoDao.findByPatente(dominio).orElse(null);
	}
	
public Set<Vehiculo> BuscarVehiculosPorId(Long id){
		
		return VehiculoDao.buscarVehiculosPorId(id);
	}

	public void save(Vehiculo vehiculo) {
		VehiculoDao.save(vehiculo);
		
	}

	public void eliminar(Long id) {
		VehiculoDao.deleteById(id);
		
	}
	
	public void eliminarTodos(Set<Vehiculo> vehiculos) {
		VehiculoDao.deleteAll(vehiculos);
		
	}

}
