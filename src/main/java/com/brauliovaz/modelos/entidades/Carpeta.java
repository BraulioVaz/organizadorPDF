package com.brauliovaz.modelos.entidades;

public final class Carpeta extends Entidad {
	public int IdCarpeta;
	public String Nombre;
	
	public Carpeta() {
		super(Carpeta.class, "IdCarpeta");
	}
	
	@Override
	public String toString() {
		return Nombre;
	}

	@Override
	public boolean esAutoincrementable() {
		return true;
	}
}
