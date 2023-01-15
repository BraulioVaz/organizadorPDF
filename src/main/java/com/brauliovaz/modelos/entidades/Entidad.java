package com.brauliovaz.modelos.entidades;

import java.lang.reflect.*;

public abstract class Entidad {
	private Field[] llavePrimaria;
	
	public Entidad(Class<?> clase, String... campos) {
		llavePrimaria = new Field[campos.length];
		
		try {
			for(int i = 0; i < campos.length; i++) {
				llavePrimaria[i] = clase.getField(campos[i]);
			}
		}
		catch(NoSuchFieldException e) {
			System.out.println(e.getMessage());
			throw new IllegalArgumentException("Nombre de campo incorrecto.");
		}
	}
	
	public boolean esLlavePrimaria(String campo) {
		
		for(Field f : llavePrimaria) {
			if(f.getName().equals(campo)) {
				return true;
			}
		}
		
		return false;
	}
	
	public abstract boolean esAutoincrementable();
}
