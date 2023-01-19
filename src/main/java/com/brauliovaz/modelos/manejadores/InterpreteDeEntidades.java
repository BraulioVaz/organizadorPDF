package com.brauliovaz.modelos.manejadores;

import java.lang.reflect.*;
import java.util.*;
import com.brauliovaz.modelos.entidades.*;

public class InterpreteDeEntidades {
	
	public static List<Campo> obtenerLlavePrimaria(Entidad entidad){
		ArrayList<Campo> campos = new ArrayList<Campo>();
		ArrayList<Campo> llavePrimaria = new ArrayList<Campo>();
		Iterator<Campo> iterador = null;
		Campo campoActual = null;
		
		campos.addAll(InterpreteDeEntidades.obtenerCampos(entidad));
		iterador = campos.iterator();
		
		while(iterador.hasNext()) {
			campoActual = iterador.next();
			if(entidad.esLlavePrimaria(campoActual.getNombre())) {
				llavePrimaria.add(campoActual);
			}
		}
		
		return llavePrimaria;
	}
	
	public static List<Campo> obtenerCampos(Entidad entidad) {
		Class<?> clase = entidad.getClass();
		ArrayList<Campo> camposDeInstancia = new ArrayList<Campo>();
		String nombre;
		Object valor;
		
		try {
			for(Field campo : clase.getFields()) {
				nombre = campo.getName();
				valor = campo.get(entidad);
				camposDeInstancia.add(new Campo(nombre, valor));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return camposDeInstancia; 
	}
	
	public static List<Campo> obtenerCamposSinLlavePrimaria(Entidad entidad){
		ArrayList<Campo> campos = new ArrayList<Campo>();
		Iterator<Campo> iterador = null;
		Campo campoActual = null;
		
		campos.addAll(InterpreteDeEntidades.obtenerCampos(entidad));
		iterador = campos.iterator();
		
		while(iterador.hasNext()) {
			campoActual = iterador.next();
			if(entidad.esLlavePrimaria(campoActual.getNombre())){
				iterador.remove();
			}
		}
		
		return campos;
	}
}
