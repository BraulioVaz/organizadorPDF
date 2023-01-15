package com.brauliovaz.modelos.entidades;

public final class Autor extends Entidad {
	public int IdAutor;
	public String Nombre;
	public String Apellido;
	
	public Autor() {
		super(Autor.class, "IdAutor");
	}
	
	@Override
	public String toString() {
		return Nombre + " " + Apellido;
	}

	@Override
	public boolean esAutoincrementable() {
		return true;
	}
}
