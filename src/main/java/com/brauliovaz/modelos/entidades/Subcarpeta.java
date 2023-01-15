package com.brauliovaz.modelos.entidades;

public final class Subcarpeta extends Entidad {
	public int IdSubcarpeta;
	public int Carpeta;
	public int Subcarpeta;
	
	public Subcarpeta() {
		super(Subcarpeta.class, "IdSubcarpeta");
	}
	
	@Override
	public String toString() {
		return "Id de carpeta: " + Carpeta + ", Id de la subcarpeta: " + Subcarpeta;
	}

	@Override
	public boolean esAutoincrementable() {
		return true;
	}
}
