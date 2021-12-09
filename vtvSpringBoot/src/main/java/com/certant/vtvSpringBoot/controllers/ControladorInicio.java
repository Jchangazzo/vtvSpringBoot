package com.certant.vtvSpringBoot.controllers;




//import java.util.Arrays;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


/*TODO
 * DIVIDIR EL CONTROLADOR EN CLASES CONTROLLER MAS PEQUEÑAS, USAR EL CONTROLADOR PRINCIPAL PARA 
 * MOSTAR EL MODELO DE LA PAGINA DE INICIO, ESTE INCIO VA A TENER UN LINK HACIA LAS LISTAS Y DESDE LAS LISTAS
 * SE PUEDEN HACER LAS OPERACIONES DE CREATE UPDATE Y DELETE
 * 
 * 
 * 
 * 	VER:
 * 	EDIT DE VEHICULOS
 * 	EDIT DE INSPECCIONES
 * */

@Controller
public class ControladorInicio {
	
	@GetMapping("/")
	public ModelAndView home(Model model) {
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}

		/*var personas=propietarioService.getAll();
		//var persona=new Persona();
		//persona.setDni((long) 388777);
		//persona.setApellido("EEEEEEEEEE");
		//persona.setNombre("DIEGO");
		//persona.setMail("eee@gmail.com");
		
		
		//System.out.println(personas.get(0).getMail());
		
		//personaService.save(persona);
		System.out.println("ejecutando el controlador mvc");
		
		model.addAttribute("personas", personas);
		return "indexPropietarios";*/
	

}
