package com.certant.vtvSpringBoot.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.certant.vtvSpringBoot.domain.Estado;
import com.certant.vtvSpringBoot.domain.Inspeccion;
import com.certant.vtvSpringBoot.domain.Marca;
import com.certant.vtvSpringBoot.domain.Modelo;
import com.certant.vtvSpringBoot.domain.Persona;
import com.certant.vtvSpringBoot.domain.Propietario;
import com.certant.vtvSpringBoot.domain.Vehiculo;
import com.certant.vtvSpringBoot.services.InspeccionService;
import com.certant.vtvSpringBoot.services.PersonaService;
import com.certant.vtvSpringBoot.services.PropietarioService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@Controller
public class VehiculoController {
	List<Modelo> modelosVW;
	List<Modelo> modelosFord;
	List<Marca> marcas;
	@ModelAttribute
	public void cargarModelos(Model modelo) {
		marcas=Marca.getTodasMarcas();
		modelosVW=Modelo.getTodasModeloVW();
		modelosFord=Modelo.getTodasModeloFord();
	}
	@Autowired 
	private PersonaService personaService;
	@Autowired 
	private VehiculoService vehiculoService;
	@Autowired 
	private PropietarioService propietarioService;
	@Autowired 
	private InspeccionService inspeccionService;
	
	@GetMapping("/listaVehiculos")
	public String listVehiculos(Model model) {
		var vehiculos=vehiculoService.getAll();
//		System.out.println(vehiculos.get(0).getPropietario().toString());
		model.addAttribute("vehiculos", vehiculos);
		return "indexVehiculos";
	}
	
	
	@GetMapping("/agregarVehiculos")
	public String agregar(Vehiculo vehiculo, Model model) {
		model.addAttribute("modelosVW", modelosVW);
		model.addAttribute("modelosFord", modelosFord);
		model.addAttribute("marcas", marcas);
//		Vehiculo vehicolo=new Vehiculo();
//		Marca marca=Marca.VOLKSWAGEN;
//		Modelo modelo=Marca.getTodasModeloFord().get(0);
//		vehicolo.setPropietario(propietarioService.Buscar(1222333));
//		vehicolo.setMarca(marca);
//		vehicolo.setModelo(modelo);
//		vehicolo.setDominio("333-DDD");
//		vehiculoService.save(vehicolo);
////		var vehiculos=vehiculoService.getAll();
////		model.addAttribute("vehiculos", vehiculos);
//		return "indexVehiculos";
		return "modificarVehiculo";
	}
	
	
	@GetMapping("/agregarVehiculos/{id}")
	public String agregarEnProp(Vehiculo vehiculo, Propietario prop, Model modelo) {
		var propietario=propietarioService.findById(prop.getId());
		System.out.println(propietario.toString());
		vehiculo.setPropietario(propietario);
//		modelo.addAttribute("modelosVW", modelosVW);
//		modelo.addAttribute("modelosFord", modelosFord);
		modelo.addAttribute("marcas", marcas);
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("propietario", propietario);
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
	@GetMapping("/inspeccionesVehiculo/{id}")
	public String inspecciones(Vehiculo vehiculo, Model modelo){
		var inspecciones=inspeccionService.buscarInspeccionesPorAuto(vehiculo.getId());
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("inspecciones", inspecciones);
		return "indexInspecciones";
	}
	@GetMapping("/filtrarPorEstado/{id}")
	public String filtrarPorEstado(Vehiculo vehiculo, Model modelo) {
		
		modelo.addAttribute("vehiculo", vehiculo);
		return "insertarEstado";
	}
	@PostMapping("/buscarPorEstado/{id}")
	public String buscarPorEstado(@RequestParam Estado estado,@ModelAttribute Vehiculo vehiculo, Model modelo) {
		var inspecciones=inspeccionService.buscarInspeccionesPorAuto(vehiculo.getId());
		List<Inspeccion> inspeccionesABorrar=new ArrayList<>();
		/**
		 * TODO
		 * POR AHORA LO DEJO IMPLEMENTADO ASI PERO MAS ADELANTE HACERLO CON UNA QUERY DESDE EL REPOSITORIO
		 */
		
		if (inspecciones!=null){
			for(Inspeccion i:inspecciones) {
				if(i.getEstado()!=estado) {
//					inspecciones.remove(i);
					inspeccionesABorrar.add(i);
				}
			}	
			inspecciones.removeAll(inspeccionesABorrar);
		}
		modelo.addAttribute("inspecciones", inspecciones);
		return "indexInspecciones";
	}
	
	@GetMapping("/filtrarPorFecha/{id}")
	public String filtrarPorFecha(LocalDate desde, LocalDate hasta,Vehiculo vehiculo, Model modelo) {
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("desde", desde);
		modelo.addAttribute("hasta", hasta);
		return "instertarFechas";
	}
	
	@PostMapping("/buscarPorFecha/{id}")
	public String buscarPorFecha(@RequestParam("desde") @DateTimeFormat(iso = ISO.DATE) LocalDate desde,
								  @RequestParam("hasta")@DateTimeFormat(iso = ISO.DATE) LocalDate hasta,
								  Vehiculo vehiculo, Model modelo) {
		
		var inspecciones=inspeccionService.buscarPorFecha(desde, hasta);
		
		List<Inspeccion> inspeccionesABorrar=new ArrayList<>();
		/**
		 * TODO
		 * POR AHORA LO DEJO IMPLEMENTADO ASI PERO MAS ADELANTE  HACERLO CON UNA QUERY DESDE EL REPOSITORIO
		 * 
		 * no es de urgencia esto
		 */
		
		if (inspecciones!=null){
			for(Inspeccion i:inspecciones) {
				if(i.getVehiculo().getId()!=vehiculo.getId()) {
//					inspecciones.remove(i);
					inspeccionesABorrar.add(i);
				}
			}	
			inspecciones.removeAll(inspeccionesABorrar);
		}
		modelo.addAttribute("inspecciones", inspecciones);
		return "indexInspecciones";
	}
	
	@PostMapping("/siguiente")
	public String agregarModelo(@Valid @ModelAttribute Vehiculo vehiculo, BindingResult result, Model model) {
		System.out.println(vehiculo.getDominio()+vehiculo.getPropietario().getDni()+vehiculo.getMarca());
		
		model.addAttribute("vehiculo", vehiculo);
		model.addAttribute("modelos", Marca.getModelosDeMarca(vehiculo.getMarca()));
		model.addAttribute("marca", vehiculo.getMarca());
		System.out.println(vehiculo.getMarca()+" //////AGREGAR///// "+vehiculo.getModelo());
		return "agregarModelo";
	}
	
	@PostMapping("/guardarVehiculo")
	public String guardarVehiculo(@Valid @ModelAttribute Vehiculo vehiculo, BindingResult result, Model model) {
		
		/**TODO
		 * falta agregar el otro formato de patente y sacarle el guion
		 *
		 * 
		 * 	VALIDACION DE PATENTE
		 */
		
		String patente=vehiculo.getDominio();
		System.out.println(vehiculo.getMarca()+" //////GUARDARD////// "+vehiculo.getModelo());
		if(!patente.matches("[0-9]{3}"+"[ ]{1}"+"[A-Z]{3}")) {
		
			FieldError error = new FieldError("vehiculo", "dominio", "la patente debe tener 7 caracteres contando el espacio");
			result.addError(error);	
			
		}
		

		if(vehiculoService.BuscarPorPatente(patente)!=null) {
			FieldError error = new FieldError("vehiculo", "dominio", "la patente corresponde a un Vehiculo que ya se encuentra en la base de datos");
			result.addError(error);		
		}
		
		Persona persona=personaService.buscarPorDni(vehiculo.getPropietario().getDni());
		Propietario prop=vehiculo.getPropietario();
		prop.setId(persona.getId());
		vehiculo.setPropietario(prop);
		if(propietarioService.findById(vehiculo.getPropietario().getId())==null) {
			FieldError error = new FieldError("vehiculo", "propietario.dni", "ingrese el dni de un propietario que se encuentre en la base de datos");
			result.addError(error);			
		}
		if(!Marca.isMarcaModeloCorrecto(vehiculo.getModelo(), vehiculo.getMarca())) {
			
			FieldError error = new FieldError("vehiculo", "Modelo", "ingrese un modelo que corresponda a esa marca");
			result.addError(error);		
		}
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Vehiculo");
			model.addAttribute("vehiculo", vehiculo);
			System.out.println("Hubo errores en la creacion del vehiculo!");
			return "modificarVehiculo";
		}
		
		
		/**
		 * creo q no hace falta hacer este mapeo, creo que ya lo tenìa implementado sin y lo agregue despues
		 * de hacer lo mismo en guardarInspeccion en InspeccionController
		 */
		var propietario=propietarioService.findById(vehiculo.getPropietario().getId());
		vehiculo.setPropietario(propietario);
		propietario.getVehiculos().add(vehiculo);
		System.out.println(vehiculo.getPropietario());

		vehiculoService.save(vehiculo);
//		propietarioService.save(prop);
		System.out.println(propietarioService.findById(propietario.getId()).toString());
		return "redirect:/listaVehiculos";
	}

}
