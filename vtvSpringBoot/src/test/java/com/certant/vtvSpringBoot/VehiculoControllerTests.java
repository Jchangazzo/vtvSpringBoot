package com.certant.vtvSpringBoot;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.certant.vtvSpringBoot.controllers.PropietarioController;
import com.certant.vtvSpringBoot.repositories.IPersonaDao;
import com.certant.vtvSpringBoot.repositories.IPropietarioDao;
import com.certant.vtvSpringBoot.repositories.IVehiculoDao;
import com.certant.vtvSpringBoot.services.PersonaService;
import com.certant.vtvSpringBoot.services.PropietarioService;
import com.certant.vtvSpringBoot.services.VehiculoService;

@WebMvcTest(PropietarioController.class) 
//@ContextConfiguration(locations = {"/applicationContext.xml", "/spring-servlet.xml"})
@ContextConfiguration(classes = {SwaggerConfig.class, })
@Import(PropietarioController.class)
public class VehiculoControllerTests {
	
	
	
	@Autowired
	private MockMvc mvc;	
	
	@MockBean
	private PropietarioService propietarioService;
	
	@MockBean
	private VehiculoService vehiculoService;
	
	@MockBean
	private PersonaService personaService;
	
//	@MockBean
//	private IPropietarioDao propietarioDao;
//	
//	@MockBean
//	private IVehiculoDao VehiculoDao;
//	
//	@MockBean
//	private IPersonaDao personaDao;
	
	@Test
	void PropietarioControllerTest() throws Exception {
		//Given
		when(propietarioService.getAll()).thenReturn(DatosDePrueba.PROPIETARIOS);
		
		//When
		
			mvc.perform(get("/listaPropietarios").contentType(MediaType.APPLICATION_JSON))
			//Then
			.andExpect(status().isOk());
			
			verify(propietarioService).getAll();
//			verify(propietarioService).findById(1L);
		
	}

}
