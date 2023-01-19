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
		String sql = FormateadorSQL.crearSelect(tabla, null);
		ResultSet registros = null;
		List<T> resultado = Collections.emptyList();
		
		try {
			registros = ConexionBD.ejecutarConsulta(sql);
			resultado = convertirRegistrosEnEntidades(registros);
			registros.close();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
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
	
	public List<T> select(List<Campo> condiciones){
		String sql = FormateadorSQL.crearSelect(tabla, condiciones);
		ResultSet registros = null;
		List<T> resultado = Collections.emptyList();
		
		try {
			registros = ConexionBD.ejecutarConsulta(sql);
			resultado = convertirRegistrosEnEntidades(registros);
			registros.close();
		}
		catch(SQLException e) {
			System.out.println("Error en consulta: " + e.getMessage());
		}
		
		return resultado;
	}
	
	public boolean insert(T entidad) {
		String sql = FormateadorSQL.crearInsert(tabla, entidad);
		boolean operacionExitosa = false;
		
		try {
			operacionExitosa = ConexionBD.ejecutar(sql);
		}
		catch(SQLException e) {
			System.out.println("Error en insercion : " + e.getMessage());
		}
		
		return operacionExitosa;
	}
	
	public boolean update(T entidad) {
		String sql = FormateadorSQL.crearUpdate(tabla, entidad);
		boolean operacionExitosa = false;
		
		try {
			operacionExitosa = ConexionBD.ejecutar(sql);
		}
		catch(SQLException e) {
			System.out.println("Error en actualizacion : " + e.getMessage());
		}
		
		return operacionExitosa;
	}
	
	public boolean delete(T entidad) {
		String sql = FormateadorSQL.crearDelete(tabla, entidad);
		boolean operacionExitosa = false;
		
		try {
			operacionExitosa = ConexionBD.ejecutar(sql);
		}
		catch(SQLException e) {
			System.out.println("Error en eliminacion : " + e.getMessage());
		}
		
		return operacionExitosa;
	}
}
