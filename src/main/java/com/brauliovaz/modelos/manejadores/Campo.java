package com.brauliovaz.modelos.manejadores;

public class Campo {
	private String nombre;
	private Object valor;
	
	public Campo(String nombre, Object valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Object getValor() {
		return valor;
	}
}
