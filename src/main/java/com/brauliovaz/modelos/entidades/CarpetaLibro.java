package com.brauliovaz.modelos.entidades;

public final class CarpetaLibro extends Entidad {
	public int IdCarpeta;
	public int IdLibro;
	
	public CarpetaLibro() {
		super(CarpetaLibro.class, "IdCarpeta", "IdLibro");
	}
	
	@Override
	public String toString() {
		return "Id de carpeta: " + IdCarpeta + ", Id del libro: " + IdLibro;
	}

	@Override
	public boolean esAutoincrementable() {
		return false;
	}
}
