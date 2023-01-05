package com.brauliovaz.modelos.manejadores;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import com.brauliovaz.modelos.entidades.*;

public abstract class ManejadorBase<T extends Entidad>{
	private Class<T> claseEntidad;
	private String tabla;
	private Field[] campos;
	
	public ManejadorBase(Class<T> entidad) {
		claseEntidad = entidad;
		tabla = entidad.getSimpleName();
		campos = entidad.getFields();
	}
	
	public List<T> obtenerTodos(){
		String sql = crearConsulta(null);
		ResultSet registros = null;
				
		try {
			registros = ConexionBD.ejecutarConsulta(sql);
			return convertirRegistrosEnEntidades(registros);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Collections.emptyList();
	}
	
	private String crearConsulta(HashMap<String, Object> condiciones) {
		String sql = "SELECT * FROM " + tabla + " ";
		
		if(condiciones != null) {
			sql += "WHERE ";
			
			for(String llave : condiciones.keySet()) {
				sql += llave + " = " + valorEnSQL(condiciones.get(llave)) + ","; 
			}
			
			sql = sql.substring(0, sql.length() - 1);
		}
		
		return sql + ";";
	}
	
	private String valorEnSQL(Object dato) {
		switch(dato.getClass().getSimpleName()) {
			case "String":
				return "'" + dato + "'";
			case "int":
				return dato.toString();
		}
		
		return dato.toString();
	}
	
	private List<T> convertirRegistrosEnEntidades(ResultSet rs) throws SQLException{
		ArrayList<T> instanciasDeEntidad = new ArrayList<T>();
		T instancia = crearInstanciaDeEntidad();
		
		while(rs.next()) {
			llenarEntidad(instancia, rs);
			instanciasDeEntidad.add(instancia);
		}
		
		return instanciasDeEntidad;
	}
	
	private T crearInstanciaDeEntidad() {
		try {
			return claseEntidad.newInstance();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null; 
	}
	
	private void llenarEntidad(T entidad, ResultSet registro){
		String nombreColumna = "";
		
		try {
			for(Field campo : campos) {
				nombreColumna = campo.getName();
				campo.set(entidad, registro.getObject(nombreColumna));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<T> select(HashMap<String, Object> condiciones){
		String sql = crearConsulta(condiciones);
		ResultSet registros = null;
		
		try {
			registros = ConexionBD.ejecutarConsulta(sql);
			return convertirRegistrosEnEntidades(registros);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Collections.emptyList();
	}
}
