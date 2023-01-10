package com.brauliovaz.modelos.manejadores;

import java.lang.reflect.*;
import java.util.*;

public class InterpreteDeEntidades {
	public static HashMap<String, Object> obtenerCampos(Object entidad) {
		Class<?> clase = entidad.getClass();
		HashMap<String, Object> camposDeInstancia = new HashMap<String, Object>();
		String nombre;
		Object valor;
		
		try {
			for(Field campo : clase.getFields()) {
				nombre = campo.getName();
				valor = campo.get(entidad);
				camposDeInstancia.put(nombre, valor);
			}
		}
		catch(Exception e) {
			System.out.println();
		}
		
		return camposDeInstancia; 
	}
}
