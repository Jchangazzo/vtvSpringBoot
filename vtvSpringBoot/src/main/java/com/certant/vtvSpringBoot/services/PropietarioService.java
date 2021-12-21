package com.certant.vtvSpringBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certant.vtvSpringBoot.domain.Propietario;
import com.certant.vtvSpringBoot.repositories.IPropietarioDao;

@Service
public class PropietarioService {
	/**
	 * SOBRECARGA DE METODOS
	 * 
	 */
	@Autowired
	private IPropietarioDao propietarioDao;
	
	/*@Autowired
	private IVehiculoDao vehiculoDao;*/
	
	public List<Propietario> getAll() {

		return propietarioDao.findAll();
	}

	public Propietario findById(long id) {

		return propietarioDao.findById(id).orElse(null);
	}

//	public Propietario Buscar(long dni){
//		
//		return propietarioDao.findByDni(dni).orElse(null);
//	}

	public void save(Propietario prop) {
		propietarioDao.save(prop);
		
	}
	
	/*public List<Vehiculo> getVehiculos(Long dni) {

		return propietarioDao.vehiculos(dni);
	}*/

	public void eliminar(Long id) {
		/*List<Vehiculo> vehiculos=getVehiculos(dni);
		vehiculoDao.deleteAll(vehiculos);*/
		propietarioDao.deleteById(id);
		
	}
	
}
