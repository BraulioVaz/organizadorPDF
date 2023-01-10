package com.brauliovaz.modelos.manejadores;

import java.util.*;

public class FormateadorSQL {
	
	public static String crearSelect(String tabla, HashMap<String, Object> condiciones) {
		String sql;
		
		sql = "SELECT * FROM " + tabla;
		sql = aplicarCondiciones(condiciones) + ";";
		
		return sql;
	}
	
	private static String aplicarCondiciones(HashMap<String, Object> condiciones) {
		String sql = "";
		Object datoEsperado;
		
		if(condiciones == null) {
			return "";
		}
		
		for(String campo : condiciones.keySet()) {
			datoEsperado = condiciones.get(campo);
			sql += campo + " = " + formatearDato(datoEsperado) + ",";
		}
		
		if(sql.length() > 0) {
			sql = " WHERE " + sql;
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
	
	public static String crearInsert(String tabla, Object entidad) {
		String sql, campos, valores;
		HashMap<String, Object> camposDeInstancia = InterpreteDeEntidades.obtenerCampos(entidad);
		
		sql = "INSERT INTO " + tabla;
		campos = "( ";
		valores = "( ";
		
		for(String campo : camposDeInstancia.keySet()) {
			campos += campo + ",";
			valores += formatearDato(camposDeInstancia.get(campo));
		}
		
		campos = campos.substring(0, campos.length() - 1) + ")";
		valores = valores.substring(0, valores.length() - 1) + ")";
		
		return sql + campos + " VALUES " + valores;
	}
}
