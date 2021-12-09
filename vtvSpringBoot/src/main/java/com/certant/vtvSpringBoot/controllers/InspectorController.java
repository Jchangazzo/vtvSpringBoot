package com.certant.vtvSpringBoot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.vtvSpringBoot.domain.Inspector;
import com.certant.vtvSpringBoot.services.InspectorService;

@Controller
public class InspectorController {
	
	@Autowired 
	private InspectorService inspectorService;
	
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
	
	@PostMapping("/guardarInspector")
	public String guardarProp(@Valid Inspector inspector, Errors errores) {
		if(errores.hasErrors()) {
			return "modificarInspector";
		}
		inspectorService.save(inspector);
		return "redirect:/listaInspectores";
	}

}
