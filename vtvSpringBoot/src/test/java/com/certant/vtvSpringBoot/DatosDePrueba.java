package com.certant.vtvSpringBoot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.certant.vtvSpringBoot.domain.Marca;
import com.certant.vtvSpringBoot.domain.Modelo;
import com.certant.vtvSpringBoot.domain.Propietario;
import com.certant.vtvSpringBoot.domain.Vehiculo;

public class DatosDePrueba {
	////PROPIETARIOS
	public static final List<Propietario> PROPIETARIOS=Arrays.asList(new Propietario((long) 39313562,"Marcelo","GallarDIOS"),
			new Propietario((long) 11888999,"Agustin","Palavechiino"));
	public static final List<Propietario> PROPIETARIOSEMPTY =Collections.emptyList();
	/////VEHICULOS
	public static final List<Vehiculo> VEHICULOS=Arrays.asList( new Vehiculo(1L, "912 RIP", Marca.FORD, Modelo.ECOSPORT, PROPIETARIOS.get(0)),
			new Vehiculo(2L, "777 AAA", Marca.VOLKSWAGEN, Modelo.POLO, PROPIETARIOS.get(1)));
	
	

}
