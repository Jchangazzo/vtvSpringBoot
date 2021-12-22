package com.certant.vtvSpringBoot.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.certant.vtvSpringBoot.domain.Estado;
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
		/**NO ESTA IMPLEMENTADA NO PUDE HACERLA
		 * el path toma bien el Id de la inspeccion, parece almacenarse bien
		 * pero aca se rompe, la inspeccion que se pasa por parametro tiene Id null
		 * aun cuando en el path se muestra el Id correcto. Luego, al hacer FindById se rompe
		 */
//		System.out.println(inspeccion.toString());
		inspeccion=inspeccionService.Buscar(inspeccion.getId());
//		System.out.println(inspeccion.toString());
		modelo.addAttribute("inspeccion", inspeccion);
		return "modificarInspeccion";
	}
	

	
	@GetMapping("/eliminarInspeccion")
	public String eliminarInspeccion(Inspeccion inspeccion) {
		
		/*NO ESTA IMPLEMENTADO, no lo pude hacer, tira error nullptr al hacer el delete, de todas formas no es la idea
		 * borrar una inspeccion, sino que quede registrada, */
//		inspeccion.getVehiculo().getInspecciones().remove(inspeccion);
//		inspeccion.getInspector().getInspecciones().remove(inspeccion);
		inspeccionService.eliminar(inspeccion.getId());
		return "redirect:/listaInspecciones";
	}


	@GetMapping("/filtrarPorEstado")
	public String filtrarPorEstado(Estado estado, Model modelo) {
		modelo.addAttribute("estado", estado);
		return "insertarEstado";
	}

	@RequestMapping("/buscarPorEstado")
	public String  buscarPorEstado(@RequestParam("estado") Estado estado,Model model)  {
		
		var inspecciones=inspeccionService.buscarPorEstado(estado);
		model.addAttribute("inspecciones", inspecciones);
		return "indexInspecciones";
	}
	@GetMapping("/filtrarPorFecha")
	public String filtrarPorFecha(LocalDate desde, LocalDate hasta, Model modelo) {
		modelo.addAttribute("desde", desde);
		modelo.addAttribute("hasta", hasta);
		return "instertarFechas";
	}
	@PostMapping("/buscarPorFecha")
	public String buscarPorFecha(@RequestParam("desde") @DateTimeFormat(iso = ISO.DATE) LocalDate desde,
								  @RequestParam("hasta")@DateTimeFormat(iso = ISO.DATE) LocalDate hasta, Model modelo) {
		
		var inspecciones=inspeccionService.buscarPorFecha(desde, hasta);
		modelo.addAttribute("inspecciones", inspecciones);
		return "indexInspecciones";
	}
	
	
//	@GetMapping("/filtrarPorPatente")
//	@GetMapping("/filtrarPorInspector")
	@PostMapping("/guardarInspeccion")
	public String guardarInspeccion(@Valid @ModelAttribute Inspeccion inspeccion, BindingResult result, Model model) {
		/**
		 * TODO
		 * faltan las validaciones de los campos Estado y Exento
		 * 
		 * 
		 */
		System.out.println(inspeccion.getVehiculo().getDominio());
		var vehiculo=vehiculoService.BuscarPorPatente(inspeccion.getVehiculo().getDominio());
		if(vehiculoService.Buscar(vehiculo.getId())==null) {
			FieldError error = new FieldError("inspeccion", "vehiculo.id", "ingrese el id de un vehiculo que se encuentre en la base de datos");
			result.addError(error);			
		}
		
		
		if(inspectorService.findById(inspeccion.getInspector().getId())==null) {
			FieldError error = new FieldError("inspeccion", "inspector.dni", "ingrese el dni de un inspector que se encuentre en la base de datos");
			result.addError(error);			
		}
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Inspeccion");
			model.addAttribute("inspeccion", inspeccion);
			System.out.println("Hubo errores en la creacion de la inspeccion!");
			return "modificarInspeccion";
		}
		/*SE LE ASIGNA UN ID AL OBJETO INSPECCION AUTOMATICAMENTE CUANDO SE HACE EL SAVE
		 * 
		 * */
		System.out.println(inspeccion.toString());
		var inspector=inspectorService.findById(inspeccion.getInspector().getId());
//		vehiculo=vehiculoService.Buscar(inspeccion.getVehiculo().getId());

		inspector.getInspecciones().add(inspeccion);
		vehiculo.getInspecciones().add(inspeccion);
		
		inspeccion.setInspector(inspector);
		inspeccion.setVehiculo(vehiculo);	

		inspeccionService.save(inspeccion);
		inspectorService.save(inspector);
		vehiculoService.save(vehiculo);
		/*
		 *LA INSPECCION QUE SE GUARDA TIENE ID!= NULL PERO INSPECTOR Y VEHICULO TIENEN TODOS
		 *LOS CAMPOS EN NULL , SALVO EL ID POR ESTO ES QUE HAGO ESTE MAPEO
		 * */
		System.out.println(vehiculo.toString());
		System.out.println(inspector.toString());
		System.out.println(inspeccion.toString());
		return "redirect:/listaInspecciones";
		
	}
}
