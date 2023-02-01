package com.brauliovaz.vistas.componentes;

import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

public class CreadorDeObjetos<T> extends JPanel{
	private static final long serialVersionUID = 1L;
	private Class<T> clase;
	private ArrayList<Registro> registros;
	
	public CreadorDeObjetos(Class<T> clase) {
		this.clase = clase;
		registros = new ArrayList<Registro>();
		
		crearRegistros();
		agregarRegistros();
	}
	
	private void crearRegistros() {
		for(Field f : clase.getFields()) {
			registros.add(new Registro(f));
		}
	}
	
	private void agregarRegistros() {
		for(Registro r : registros) {
			this.add(r);
		}
	}
	
	public T generarObjeto() {
		T instancia = null;
		Registro registro = null;
		
		try {
			instancia = clase.newInstance();
			
			for(Field f : clase.getFields()) {
				registro = buscarPorCampo(f);
				f.set(instancia, registro.obtenerValor());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return instancia;
	}
	
	private Registro buscarPorCampo(Field f) {
		for(Registro r : registros) {
			if(r.campo.equals(f)) {
				return r;
			}
		}
		
		return null;
	}
	
	public void ocultarCampo(Field f) {
		Registro r = buscarPorCampo(f);
		this.remove(r);
	}
	
	public void cambiarEtiqueta(Field f, String mensaje) {
		Registro r = buscarPorCampo(f);
		r.definirEtiqueta(mensaje);
	}
	
	private class Registro extends Box{
		static final long serialVersionUID = 1L;
		Field campo;
		JLabel etiqueta;
		ComponenteDeEntrada entrada;
		
		public Registro(Field campo) {
			super(BoxLayout.X_AXIS);
			this.campo = campo;
			this.etiqueta = new JLabel();
			this.entrada = crearComponenteDeEntrada();
			
			this.add(etiqueta);
			this.add(Box.createHorizontalStrut(15));
			this.add(entrada.componente());
			
			definirEtiqueta(campo.getName());
		}
		
		private ComponenteDeEntrada crearComponenteDeEntrada() {
			switch(campo.getType().getCanonicalName()) {
				case "java.lang.String":
					return new CampoDeTexto();
				case "int":
					return new CampoNumerico();
			}
			
			return null;
		}
		
		public void definirEtiqueta(String s) {
			etiqueta.setText(s);
		}
		
		public Object obtenerValor() {
			return entrada.recuperarValor();
		}
	}
}
