package com.brauliovaz.modelos.manejadores;

import java.sql.*;

class ConexionBD {
	private static Connection conectar(){
		Connection conexion = null;
		String url = "jdbc:sqlite:base_datos.db";
		
		try {
			conexion = DriverManager.getConnection(url);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conexion;
	}
	
	public static boolean ejecutar(String sql) throws SQLException{
		Connection conexion = conectar();
		Statement sentencia = null;
		boolean resultado = false;
		
		sentencia = conexion.createStatement();
		resultado = sentencia.execute(sql);
		conexion.close();
		
		return resultado;
	}
	
	public static ResultSet ejecutarConsulta(String sql) throws SQLException{
		Connection conexion = conectar();
		Statement sentencia = null;
		ResultSet resultados = null;
		
		sentencia = conexion.createStatement();
		resultados = sentencia.executeQuery(sql);
		
		return resultados;
	}
	
}
