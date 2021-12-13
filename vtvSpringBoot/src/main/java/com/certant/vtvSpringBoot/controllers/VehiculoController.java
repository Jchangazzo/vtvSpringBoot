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

import com.certant.vtvSpringBoot.domain.Vehiculo;
import com.certant.vtvSpringBoot.services.PropietarioService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@Controller
public class VehiculoController {
	
	
	@Autowired 
	private VehiculoService vehiculoService;
	@Autowired 
	private PropietarioService propietarioService;
	
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
		//System.out.println(vehiculo.getPropietario().getDni());
		vehiculoService.eliminar(vehiculo.getId());
		return "redirect:/listaVehiculos";
	}
	
	@PostMapping("/guardarVehiculo")
	public String guardarVehiculo(@Valid @ModelAttribute Vehiculo vehiculo, BindingResult result, Model model) {
		
		//List<Propietario> propietarios=propietarioService.getAll();
		/**
		 * TODO
		 * 	VALIDACION DE PATENTE
		 */
		String patente=vehiculo.getDominio();
		/*if(patente.length()!=7) {
			FieldError error = new FieldError("vehiculo", "dominio", "la patente debe tener 7 caracteres contando el guion medio");
			result.addError(error);	
		}else {
			try {
				Integer.parseInt(patente.substring(0, 4));
				
			}catch(NumberFormatException e){
				
				System.out.println("Hubo errores en la creacion del vehiculo!");
				model.addAttribute("titulo", "Formulario: Nuevo Vehiculo");
				model.addAttribute("vehiculo", vehiculo);
				return "modificarVehiculo";
				
			}
			
			if(patente.charAt(4)!='-') {
				FieldError error = new FieldError("vehiculo", "dominio", "la patente lleva un guion en el medio");
				result.addError(error);		
				
			}
		}*/
		/*TODO
		 * VER COMO SE ESCRIBE EL GUION MEDIO 
		 * */
		if(!patente.matches("[0-9]{3}"+"[-]{1}"+"[A-Z]{3}")) {
		
			FieldError error = new FieldError("vehiculo", "dominio", "la patente debe tener 7 caracteres contando el guion medio");
			result.addError(error);	
			
		}
		
		

		if(vehiculoService.BuscarPorPatente(patente)!=null) {
			FieldError error = new FieldError("vehiculo", "dominio", "la patente corresponde a un Vehiculo que ya se encuentra en la base de datos");
			result.addError(error);		
		}
		//boolean patenteValida=false;

		
		
		
		if(propietarioService.Buscar(vehiculo.getPropietario().getDni())==null) {
			FieldError error = new FieldError("vehiculo", "propietario.dni", "ingrese el dni de un propietario que se encuentre en la base de datos");
			result.addError(error);			
		}
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Vehiculo");
			model.addAttribute("vehiculo", vehiculo);
			System.out.println("Hubo errores en la creacion del vehiculo!");
			return "modificarVehiculo";
		}
		var prop=propietarioService.Buscar(vehiculo.getPropietario().getDni());
		vehiculo.setPropietario(prop);
		prop.getVehiculos().add(vehiculo);
		System.out.println(vehiculo.getPropietario());

		vehiculoService.save(vehiculo);
		propietarioService.save(prop);
		System.out.println(propietarioService.Buscar(prop.getDni()).toString());
		return "redirect:/listaVehiculos";
	}

}
