package com.certant.vtvSpringBoot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.certant.vtvSpringBoot.domain.Vehiculo;
import com.certant.vtvSpringBoot.services.PropietarioService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@Controller
public class VehiculoController {
	
	
	@Autowired 
	private VehiculoService vehiculoService;
//	@Autowired 
//	private PropietarioService propietarioService;
	
	@GetMapping("/listaVehiculos")
	public String listVehiculos(Model model) {
		var vehiculos=vehiculoService.getAll();
		//System.out.println(vehiculos.get(0).getMarca());
		model.addAttribute("vehiculos", vehiculos);
		return "indexVehiculos";
	}
	
	
	@GetMapping("/agregarVehiculos")
	public String agregar(Vehiculo vehiculo) {
		return "modificarVehiculo";
	}
	
	
	@GetMapping("/editarVehiculo/{id}")
	public String editar(Vehiculo vehiculo, Model modelo) {
		vehiculo=vehiculoService.Buscar(vehiculo.getId());
//		System.out.println(vehiculo.getId()+" "+vehiculo.getMarca()+" "+vehiculo.getModelo());
		modelo.addAttribute("vehiculo", vehiculo);
		return "modificarVehiculo";
	}
	
	@GetMapping("/eliminarVehiculo")
	public String eliminar(Vehiculo vehiculo) {
//		System.out.println(vehiculo.getId());
		vehiculoService.eliminar(vehiculo.getId());
		return "redirect:/listaVehiculos";
	}
	
	@PostMapping("/guardarVehiculo")
	public String guardarVehiculo(@Valid Vehiculo vehiculo, Errors errores) {
		/**
		 * VER COMO SE GUARDA EL PROPIETARIO DEL VEHICULO
		 */
		if(errores.hasErrors()) {
			return "modificarVehiculo";
		}
		System.out.println(vehiculo.getPropietario());

		vehiculoService.save(vehiculo);
		return "redirect:/listaVehiculos";
	}

}
