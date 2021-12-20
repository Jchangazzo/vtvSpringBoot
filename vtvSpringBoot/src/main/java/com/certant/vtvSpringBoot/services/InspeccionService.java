package com.certant.vtvSpringBoot.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certant.vtvSpringBoot.domain.Estado;
import com.certant.vtvSpringBoot.domain.Inspeccion;
import com.certant.vtvSpringBoot.repositories.IInspeccionDao;

@Service
public class InspeccionService {
	
	@Autowired
	private IInspeccionDao inspeccionDao;
	
	public List<Inspeccion> getAll() {

		return inspeccionDao.findAll();
	}



	public Inspeccion Buscar(Long id){
		
		return inspeccionDao.findById(id).orElse(null);
	}
	
	public Set<Inspeccion> buscarInspeccionesPorDni(Long dni){
		
		return inspeccionDao.buscarInspeccionesPorDni(dni);
	}
	
	public Set<Inspeccion> buscarInspeccionesPorAuto(Long id){
		
		return inspeccionDao.buscarInspeccionesPorAuto(id);
	}
	public Set<Inspeccion> buscarPorEstado(Estado estado){
		return inspeccionDao.buscarPorEstado(estado);
	}
//	public Set<Inspeccion> buscarAptos(){
//		
//		return inspeccionDao.buscarAptos();
//	}
//	public Set<Inspeccion> buscarCondicionales(){
//			
//		return inspeccionDao.buscarCondicionales();
//	}
//	public Set<Inspeccion> buscarRechazados(){
//		
//		return inspeccionDao.buscarRechazados();
//	}

	public void save(Inspeccion inspeccion) {
		inspeccionDao.save(inspeccion);
		
	}

	public void eliminar(Long id) {
		inspeccionDao.deleteById(id);
		
	}
	
	public void eliminarTodos(Set<Inspeccion> inspecciones) {
		inspeccionDao.deleteAll(inspecciones);
		
	}



	public Set<Inspeccion> buscarPorFecha(LocalDate desde, LocalDate hasta) {
		return inspeccionDao.buscarPorFecha(desde, hasta);
		
	}

}
