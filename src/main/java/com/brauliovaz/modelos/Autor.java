package com.brauliovaz.modelos;

public class Autor implements Entidad {
	public int IdAutor;
	public String Nombre;
	public String Apellido;
	
	@Override
	public String toString() {
		return Nombre + " " + Apellido;
	}
}
