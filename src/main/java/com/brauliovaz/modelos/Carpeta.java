package com.brauliovaz.modelos;

public class Carpeta implements Entidad {
	public int IdCarpeta;
	public String Nombre;
	
	@Override
	public String toString() {
		return Nombre;
	}
}
