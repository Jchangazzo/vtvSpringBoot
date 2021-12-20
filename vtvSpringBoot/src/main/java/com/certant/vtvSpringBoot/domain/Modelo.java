package com.certant.vtvSpringBoot.domain;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum Modelo {
	
	POLO,
    SURAN,
    VENTO,
    FIESTA,
    FOCUS,
    ECOSPORT;

	public static Set<Modelo> volkswagen= EnumSet.range(POLO, VENTO);
    public static Set<Modelo> ford= EnumSet.range(FIESTA, ECOSPORT);

    private String marcaNombre;
    private String modeloNombre;

    private Modelo() {

    }

	public static List<Modelo> getTodasModelo(){
    	List<Modelo> listModelo=new ArrayList<>();
    	listModelo.add(Modelo.POLO);
    	listModelo.add(Modelo.SURAN);
    	listModelo.add(Modelo.VENTO);
    	listModelo.add(Modelo.FIESTA);
    	listModelo.add(Modelo.FOCUS);
    	listModelo.add(Modelo.ECOSPORT);
    	return listModelo;
    }

	public static List<Modelo> getTodasModeloVW(){
    	List<Modelo> listModelo=new ArrayList<>();
    	listModelo.add(Modelo.POLO);
    	listModelo.add(Modelo.SURAN);
    	listModelo.add(Modelo.VENTO);
//    	listModelo.add(Modelo.FIESTA);
//    	listModelo.add(Modelo.FOCUS);
//    	listModelo.add(Modelo.ECOSPORT);
    	return listModelo;
    }

	public static List<Modelo> getTodasModeloFord(){
    	List<Modelo> listModelo=new ArrayList<>();
//    	listModelo.add(Modelo.POLO);
//    	listModelo.add(Modelo.SURAN);
//    	listModelo.add(Modelo.VENTO);
    	listModelo.add(Modelo.FIESTA);
    	listModelo.add(Modelo.FOCUS);
    	listModelo.add(Modelo.ECOSPORT);
    	return listModelo;
    }
    
    public String getMarcaNombre() {
        return this.marcaNombre;
    }

    public String getModeloNombr() {
        return modeloNombre;
    }
}
