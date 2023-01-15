package com.brauliovaz.modelos.entidades;

public final class Libro extends Entidad{
	public int IdLibro;
	public String Titulo;
	public String Editorial;
	public int Edicion;
	public int Publicacion;
	public String archivo;
	public int IdAutor;
	
	public Libro() {
		super(Libro.class, "IdLibro");
	}
	
	@Override
	public String toString() {
		return Titulo + ", " + Editorial + " (" + Publicacion +")";
	}
	
	@Override
	public boolean esAutoincrementable() {
		return true;
	}
}
