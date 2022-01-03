package com.certant.vtvSpringBoot;


import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.certant.vtvSpringBoot.controllers.VehiculoController;
import com.certant.vtvSpringBoot.domain.Propietario;
import com.certant.vtvSpringBoot.domain.Vehiculo;
import com.certant.vtvSpringBoot.repositories.IPropietarioDao;
import com.certant.vtvSpringBoot.repositories.IVehiculoDao;
import com.certant.vtvSpringBoot.services.PropietarioService;
//la siguiente linea es para habilitar las anotaciones @Mock
@ExtendWith(MockitoExtension.class)
public class PropietarioTest {
	@Mock
	IPropietarioDao propietarioRepository;
	@Mock
	IVehiculoDao vehiculoRepository;
	
	//el service (que es lo que estoy testeando por ahora) usa el DAO, con injectMocks le indicamos que
	//el DAO que tiene que usar es el mockeado, no el DAO real
	@InjectMocks
	PropietarioService propietarioService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
//		propietarioRepository=mock(IPropietarioDao.class);
//		vehiculoRepository=mock(IVehiculoDao.class);
	}
	/**
	 * TODO
	 * juntar los dos tests, propietarioNombre y propietarioAgregarAuto en un solo metodo de Test y meterlos dentro de un assertAll
	 */
	@Test
	void PropietarioNombreTest(){
		Propietario prop = new Propietario();
		prop.setApellido("Changa");
		prop.setNombre("Joaquin");
		String esperado="Changa";
		String real=prop.getApellido();
		Assertions.assertEquals(esperado, real);
		
	}
	
	@Test
	void PropietarioAgregarAutoTest(){
		Propietario pro = new Propietario();
		Propietario prop=null;
		pro.setApellido("Changa");
		pro.setNombre("Joaquin");

		
		Vehiculo vehiculo=new Vehiculo();
		vehiculo.setDominio("912 RIP");
		/**
		 * TODO 
		 * llamar al vehiculoController guardarVehiculo (mockeado) y testear la validacion 
		 * 
		 *  
		 */
//		when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
		pro.addVehiculo(vehiculo);
//		Model modelo = null;
//		vehiculoController.agregar(vehiculo,modelo);
//		pro.getVehiculos().add(vehiculo);
		
		Assertions.assertEquals(pro, vehiculo.getPropietario(),()->"debe setearse el campo propietario del vehiculo al agregar un vehiculo a un propietario");
		Assertions.assertEquals(vehiculo, pro.getVehiculos().stream().filter(v->v.getPropietario().equals(pro)).findFirst().get());
//		Assertions.assertEquals(vehiculo, pro.getVehiculos().stream().filter(v->v.getPropietario().equals(pro)).findAny());
		
	}
	/**
	 * TODO
	 * esta bien testear esto acá o debería crear una clase aparte para testear el repositorio??
	 */
	@Test
	void PropietarioServiceTest(){
		//PRUEBA CON MOCKITO

		
		
		when(propietarioRepository.findAll()).thenReturn(DatosDePrueba.PROPIETARIOS);
		
//		List<Propietario> propietarios=propietarioService.getAll();
		
		Assertions.assertEquals("GallarDIOS", propietarioService.getAll().get(0).getApellido());
		Assertions.assertEquals("Palavechiino", propietarioService.getAll().get(1).getApellido());
		
		Assertions.assertNull(propietarioService.findById(55));
	}
	
//	@Test
//	void PropietarioRepositoryTestListaVacia(){
//		//PRUEBA CON MOCKITO
//		
//		
//		when(propietarioRepository.findAll()).thenReturn(DatosDePrueba.PROPIETARIOSEMPTY);
//		
//		Assertions.assertTrue(propietarioRepository.findAll().isEmpty());
//		Propietario prop=new Propietario((long) 12111222, "Joaquin", "Changazzo");
////		when(propietarioRepository.findById((long)1)).thenReturn(prop);
//	}
}
