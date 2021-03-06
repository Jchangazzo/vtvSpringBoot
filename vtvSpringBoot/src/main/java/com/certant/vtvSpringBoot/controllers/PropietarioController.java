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
import org.springframework.web.bind.annotation.RequestMapping;

import com.certant.vtvSpringBoot.domain.Propietario;
import com.certant.vtvSpringBoot.services.PersonaService;
import com.certant.vtvSpringBoot.services.PropietarioService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@Controller
public class PropietarioController {
	
	@Autowired 
	private PropietarioService propietarioService;
	@Autowired 
	private VehiculoService vehiculoService;
	@Autowired
	private PersonaService personaService;

	
	@GetMapping("/listaPropietarios")
	public String listProp(Model model) {
		var propietarios=propietarioService.getAll();
		model.addAttribute("propietarios", propietarios);
		return "indexPropietarios";
	}
	
	
	@GetMapping("/agregarPropietario")
	public String agregar(Propietario prop) {
		return "modificarPropietario";
	}
	
	
	@GetMapping("/editarPropietario/{id}")
	public String editar(Propietario propietario, Model modelo) {
		propietario=propietarioService.findById(propietario.getId());
		modelo.addAttribute("propietario", propietario);
		return "modificarPropietario";
	}
	/*en el delete se debe hacer un deelete de todos los autos del propietario primero y luego eliminar el propoetario?
	 * 
	 * 
	 * */
	@GetMapping("/eliminarPropietario")
	public String eliminar(Propietario prop) {
		var vehiculos=vehiculoService.BuscarVehiculosPorId(prop.getId());
		//esta bien borrar los vehiculos asi o tendrìa que hacerlo el repositorio automaticamente????
		vehiculoService.eliminarTodos(vehiculos);
		propietarioService.eliminar(prop.getId());
		
		return "redirect:/listaPropietarios";
	}
	
	@GetMapping("/vehiculosPropietario/{id}")
	public String vehiculos(Propietario propietario, Model modelo) {
		var vehiculos=vehiculoService.BuscarVehiculosPorId(propietario.getId());
		modelo.addAttribute("propietario", propietario);
		modelo.addAttribute("vehiculos", vehiculos);
		return "indexVehiculos";
	}
	
	
	@PostMapping("/guardarPropietario")
	public String guardarProp(@Valid @ModelAttribute Propietario propietario, BindingResult result, Model model) {
		
		/**
		 * la validacion funciona bien, pero la vista no, no se muestra el mensaje 
		 * 
		 */
		String dniPersona = String.valueOf(propietario.getDni());
		System.out.println(dniPersona);
		if(dniPersona.length()==0) {
			FieldError error = new FieldError("propietario", "dni", "El dni de la persona no puede ser 0");
			result.addError(error);
		}
		
		if(dniPersona.length()>8 || dniPersona.length()<7) {
			FieldError error = new FieldError("propietario", "dni", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo");
			result.addError(error);
		}
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Persona");
			model.addAttribute("propietario", propietario);
			System.out.println("Hubo errores en la creacion del formulario!");
			return "modificarPropietario";
		}
//		var persona=personaService.buscarPorDni(propietario.getDni());
//		propietario.setPersona(persona);
		
		propietarioService.save(propietario);
		return "redirect:/listaPropietarios";
	}


}
