package com.certant.vtvSpringBoot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.vtvSpringBoot.domain.Inspeccion;
import com.certant.vtvSpringBoot.services.InspeccionService;
import com.certant.vtvSpringBoot.services.InspectorService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@Controller
public class InspeccionController {

	
	@Autowired
	private InspeccionService inspeccionService;
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private InspectorService inspectorService;
	
	@GetMapping("/listaInspecciones")
	public String listInspecciones(Model model) {
		var inspecciones=inspeccionService.getAll();
		model.addAttribute("inspecciones", inspecciones);
		return "indexInspecciones";
	}
	

	@GetMapping("/agregarInspeccion")
	public String agregarInspeccion(Inspeccion inspeccion) {
		return "modificarInspeccion";
	}

	

	
	@GetMapping("/editarInspeccion/{id}")
	public String editar(Inspeccion inspeccion, Model modelo) {
//		System.out.println(inspeccion.getId()+inspeccion.getFecha().toString());
		/**
		 * el path toma bien el Id de la inspeccion, parece almacenarse bien
		 * pero aca se rompe, la inspeccion que se pasa por parametro tiene Id null
		 * aun cuando en el path se muestra el Id correcto. Luego, al hacer FindById se rompe
		 */
		System.out.println(inspeccion.toString());
		inspeccion=inspeccionService.Buscar(inspeccion.getId());
		System.out.println(inspeccion.toString());
		modelo.addAttribute("inspeccion", inspeccion);
		return "modificarInspeccion";
	}
	

	
	@GetMapping("/eliminarInspeccion")
	public String eliminarInspeccion(Inspeccion insp) {
		inspeccionService.eliminar(insp.getId());
		return "redirect:/listaInspecciones";
	}
	
	@PostMapping("/guardarInspeccion")
	public String guardarInspeccion(@Valid Inspeccion inspeccion, Errors errores) {
		if(errores.hasErrors()) {
			return "modificarInspeccion";
		}
		/*SE LE ASIGNA UN ID AL OBJETO INSPECCION AUTOMATICAMENTE CUANDO SE HACE EL SAVE
		 * 
		 * */
		System.out.println(inspeccion.toString());
//		var inspector=inspectorService.Buscar(inspeccion.getInspector().getDni());
//		var vehiculo=vehiculoService.Buscar(inspeccion.getVehiculo().getId());
//
//		inspector.getInspecciones().add(inspeccion);
//		vehiculo.getInspecciones().add(inspeccion);
		
//		inspeccion.setInspector(inspector);
//		inspeccion.setVehiculo(vehiculo);	

		inspeccionService.save(inspeccion);
		


//		inspectorService.save(inspector);
//		vehiculoService.save(vehiculo);
		/*
		 *LA INSPECCION QUE SE GUARDA TIENE ID!= NULL PERO INSPECTOR Y VEHICULO TIENEN TODOS
		 *LOS CAMPOS EN NULL , SALVO EL ID 
		 * */
		System.out.println(inspeccion.toString());
		return "redirect:/listaInspecciones";
		
	}
}
