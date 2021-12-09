package com.certant.vtvSpringBoot.services;

import java.util.List;

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

	public Propietario findByDni(long dni) {

		return null;
	}

	public Propietario Buscar(long dni){
		
		return propietarioDao.findById(dni).orElse(null);
	}

	public void save(Propietario prop) {
		propietarioDao.save(prop);
		
	}
	
	/*public List<Vehiculo> getVehiculos(Long dni) {

		return propietarioDao.vehiculos(dni);
	}*/

	public void eliminar(Long dni) {
		/*List<Vehiculo> vehiculos=getVehiculos(dni);
		vehiculoDao.deleteAll(vehiculos);*/
		propietarioDao.deleteById(dni);
		
	}
	
}
