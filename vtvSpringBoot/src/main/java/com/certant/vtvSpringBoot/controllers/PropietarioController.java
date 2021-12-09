package com.certant.vtvSpringBoot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.vtvSpringBoot.domain.Propietario;
import com.certant.vtvSpringBoot.services.PropietarioService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@Controller
public class PropietarioController {
	
	@Autowired 
	private PropietarioService propietarioService;
	@Autowired 
	private VehiculoService vehiculoService;
	
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
	
	
	@GetMapping("/editarPropietario/{dni}")
	public String editar(Propietario propietario, Model modelo) {
		propietario=propietarioService.Buscar(propietario.getDni());
		modelo.addAttribute("propietario", propietario);
		return "modificarPropietario";
	}
	/*en el delete se debe hacer un deelete de todos los autos del propietario primero y luego eliminar el propoetario?
	 * 
	 * 
	 * */
	@GetMapping("/eliminarPropietario")
	public String eliminar(Propietario prop) {
		var vehiculos=vehiculoService.BuscarVehiculosPorDni(prop.getDni());
		//esta bien borrar los vehiculos asi o tendrìa que hacerlo el repositorio automaticamente????
		//si se supone que estan joined las tablas??
		vehiculoService.eliminarTodos(vehiculos);
		propietarioService.eliminar(prop.getDni());
		
		return "redirect:/listaPropietarios";
	}
	
	@GetMapping("/vehiculosPropietario/{dni}")
	public String vehiculos(Propietario propietario, Model modelo) {
		//propietario=propietarioService.Buscar(propietario.getDni());
		//esta bien hacerlo asi o tengo que obtenerlo con el service????
		var vehiculos=vehiculoService.BuscarVehiculosPorDni(propietario.getDni());
		modelo.addAttribute("vehiculos", vehiculos);
		//modelo.addAttribute("propietario", propietario);
		return "indexVehiculos";
	}
	
	
	@PostMapping("/guardarPropietario")
	public String guardarProp(@Valid Propietario propietario, Errors errores) {
		if(errores.hasErrors()) {
			return "modificarPropietario";
		}
		propietarioService.save(propietario);
		return "redirect:/listaPropietarios";
	}


}
