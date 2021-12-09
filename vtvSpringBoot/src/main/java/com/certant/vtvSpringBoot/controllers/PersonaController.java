package com.certant.vtvSpringBoot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.vtvSpringBoot.domain.Persona;
import com.certant.vtvSpringBoot.services.PersonaService;

@Controller
public class PersonaController {
	/**
	 * TODO funciona CRUD de todas las entidades SALVO LA CLASE PERSONA en la clase persona
	 * FUNCIONABA todo el CRUD pero cuando agregue Propietario se rompiò algo en el EDIT
	 */
	@Autowired
	private PersonaService personaService;
	
	
	@GetMapping("/listaPersonas")
	public String listPersonas(Model model) {
		var personas=personaService.getAll();
		model.addAttribute("personas", personas);
		return "indexPersonas";
	}
	

	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "modificar";
	}

	
	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errores) {
		if(errores.hasErrors()) {
			return "modificar";
		}
		personaService.save(persona);
		return "redirect:/listaPersonas";
		
	}
	
	@GetMapping("/editar/{dni}")
	public String editar(Persona persona, Model modelo) {
		persona=personaService.Buscar(persona.getDni());
		modelo.addAttribute("persona", persona);
		return "modificar";
	}
	

	
	@GetMapping("/eliminar")
	public String eliminar(Persona persona) {
		personaService.eliminar(persona.getDni());
		return "redirect:/listaPersonas";
	}
}
