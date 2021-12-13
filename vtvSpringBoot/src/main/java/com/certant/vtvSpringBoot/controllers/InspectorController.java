package com.certant.vtvSpringBoot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.vtvSpringBoot.domain.Inspector;
import com.certant.vtvSpringBoot.services.InspeccionService;
import com.certant.vtvSpringBoot.services.InspectorService;

@Controller
public class InspectorController {
	
	@Autowired 
	private InspectorService inspectorService;
	@Autowired 
	private InspeccionService inspeccionService;
	
	@GetMapping("/listaInspectores")
	public String listProp(Model model) {
		var inspectores=inspectorService.getAll();
		model.addAttribute("inspectores", inspectores);
		return "indexInspectores";
	}
	
	
	@GetMapping("/agregarInspector")
	public String agregar(Inspector insp) {
		return "modificarInspector";
	}
	
	
	@GetMapping("/editarInspector/{dni}")
	public String editar(Inspector inspector, Model modelo) {
		inspector=inspectorService.Buscar(inspector.getDni());
		modelo.addAttribute("inspector", inspector);
		return "modificarInspector";
	}
	
	@GetMapping("/eliminarInspector")
	public String eliminar(Inspector inspector) {
		
		inspectorService.eliminar(inspector.getDni());
		return "redirect:/listaInspectores";
	}
	
	@GetMapping("/inspeccionesInspector/{dni}")
	public String inspecciones(Inspector inspector, Model modelo) {

		var inspecciones=inspeccionService.buscarInspeccionesPorDni(inspector.getDni());
		modelo.addAttribute("inspecciones", inspecciones);

		return "indexInspecciones";
	}
	
	@PostMapping("/guardarInspector")
	public String guardarProp(@Valid @ModelAttribute Inspector inspector, BindingResult result, Model model) {
		
		
		String dniPersona = String.valueOf(inspector.getDni());
		if(dniPersona.length()==0) {
			FieldError error = new FieldError("propietario", "dni", "El dni de la persona no puede ser 0");
			result.addError(error);
		}		
		if(dniPersona.length()>8 || dniPersona.length()<7) {
			FieldError error = new FieldError("inspector", "dni", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo");
			result.addError(error);
		}
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Inspector");
			model.addAttribute("inspector", inspector);
			System.out.println("Hubo errores en la creacion del inspector!");
			return "modificarInspector";
		}

		inspectorService.save(inspector);
		return "redirect:/listaInspectores";
	}

}
