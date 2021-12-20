package com.certant.vtvSpringBoot.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Marca {

	
//	VOLKSWAGEN(modeloVW.getModelostring()), FORD(modeloFord);
	VOLKSWAGEN, FORD;


    Marca() {
    	
    }
    public Set<Modelo> VW=Modelo.volkswagen;

    public Set<Modelo> Ford=Modelo.ford;
    
    public static List<Marca> getTodasMarcas(){
    	List<Marca> listMarcas=new ArrayList<>();
    	listMarcas.add(Marca.VOLKSWAGEN);
    	listMarcas.add(Marca.FORD);
    	return listMarcas;
    }
    
    public static boolean isVolkswagen(Modelo modelo){
    	List<Modelo> list = Modelo.getTodasModeloVW();
    	for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(modelo)) {
				return true;
			}
    	}
		return false;
    }
    
    
    public static boolean isMarcaModeloCorrecto(Modelo modelo,Marca marca){
    	switch(marca){
    		case VOLKSWAGEN : return isVolkswagen(modelo);
    		case FORD		: return isFord(modelo);
    	}
		return false;
    }

	public static boolean isFord(Modelo modelo) {
    	List<Modelo> list = Modelo.getTodasModeloFord();
    	for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(modelo)) {
				return true;
			}
    	}
		return false;
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

}
