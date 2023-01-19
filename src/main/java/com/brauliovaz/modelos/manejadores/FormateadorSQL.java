package com.brauliovaz.modelos.manejadores;

import java.util.*;
import com.brauliovaz.modelos.entidades.Entidad;

public class FormateadorSQL {
	
	public static String crearSelect(String tabla, List<Campo> condiciones) {
		String sql;
		String condicional = representarCondiciones(condiciones);
		
		sql = "SELECT * FROM " + tabla;
		
		if(condicional != null && !condicional.isEmpty()) {
			sql+= " WHERE " + condicional;
		}
		
		sql += " ; ";
		
		return sql;
	}
	
	private static String representarCondiciones(List<Campo> condiciones) {
		String sql = "";
		Object valorEsperado;
		
		if(condiciones == null || condiciones.isEmpty()) {
			return "";
		}
		
		for(Campo c : condiciones) {
			valorEsperado = c.getValor();
			sql += c.getNombre() + " = " + formatearDato(valorEsperado) + ",";
		}
		
		return sql.substring(0, sql.length() - 1);
	}
	
	public static String formatearDato(Object dato) {
		String tipoDeDato;
		
		if(dato == null) {
			return "NULL";
		}
		
		tipoDeDato = dato.getClass().getSimpleName();
		
		switch(tipoDeDato) {
			case "String":
				return "'" + dato + "'";
			default:
				return dato.toString();
		}
	}
	
	public static String crearInsert(String tabla, Entidad entidad) {
		String sql, campos, valores;
		ArrayList<Campo> camposDeInstancia = new ArrayList<Campo>();
		
		sql = "INSERT INTO " + tabla;
		campos = "( ";
		valores = "( ";
		
		camposDeInstancia.addAll(InterpreteDeEntidades.obtenerCampos(entidad));
		
		for(Campo c : camposDeInstancia) {
			if(entidad.esLlavePrimaria(c.getNombre()) && entidad.esAutoincrementable()) {
				continue;
			}
			
			campos += c.getNombre() + ",";
			valores += formatearDato(c.getValor()) + ",";
		}
		
		campos = campos.substring(0, campos.length() - 1) + ")";
		valores = valores.substring(0, valores.length() - 1) + ")";
		
		return sql + campos + " VALUES " + valores;
	}
	
	public static String crearUpdate(String tabla, Entidad entidad) {
		String sql = "UPDATE " + tabla + " SET ";
		ArrayList<Campo> campos = new ArrayList<Campo>();
		ArrayList<Campo> condicion = new ArrayList<Campo>();
		
		campos.addAll(InterpreteDeEntidades.obtenerCamposSinLlavePrimaria(entidad));
		condicion.addAll(InterpreteDeEntidades.obtenerLlavePrimaria(entidad));
		
		sql += representarCondiciones(campos) + " WHERE " + representarCondiciones(condicion) + ";";
		
		return sql;
	}
	
	public static String crearDelete(String tabla, Entidad entidad) {
		String sql = "DELETE FROM " + tabla + " WHERE ";
		ArrayList<Campo> llavePrimaria = new ArrayList<Campo>();
		
		llavePrimaria.addAll(InterpreteDeEntidades.obtenerLlavePrimaria(entidad));
		sql += representarCondiciones(llavePrimaria) + " ;";
		
		return sql;
	}
}
