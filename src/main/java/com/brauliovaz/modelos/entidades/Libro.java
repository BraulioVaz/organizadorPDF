package com.brauliovaz.modelos.entidades;

public class Libro implements Entidad{
	public int IdLibro;
	public String Titulo;
	public String Editorial;
	public int Edicion;
	public int Publicacion;
	public String archivo;
	public int IdAutor;
	
	@Override
	public String toString() {
		return Titulo + ", " + Editorial + " (" + Publicacion +")";
	}
}
