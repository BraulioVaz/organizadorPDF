package com.brauliovaz.vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.brauliovaz.config.*;

public abstract class CRUD extends VistaBase{
	private static final long serialVersionUID = 1L;
	protected static final int SEPARACION_ENTRE_BOTONES = 15;
	public static final int EVENTO_AGREGAR = 1;
	public static final int EVENTO_MODIFICAR = 5;
	public static final int EVENTO_ELIMINAR = 10;
	
	private JPanel contenedorDeBotones;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnBorrarDatos;
	protected JPanel cabecera;
	protected JLabel lblElementoActivo;
	protected JTable tablaDeInstancias;
	
	public CRUD() {
		cabecera = new JPanel();
		contenedorDeBotones = new JPanel();
		btnAgregar = crearBoton("Nuevo", "agregar.png");
		btnEliminar = crearBoton("Eliminar", null);
		btnModificar = crearBoton("Modificar", null);
		btnBorrarDatos = crearBoton("BorrarDatos", null);
		
		contenedorDeBotones.setLayout(new BoxLayout(contenedorDeBotones, BoxLayout.X_AXIS));
		agregarBoton(btnAgregar);
		agregarBoton(btnEliminar);
		agregarBoton(btnModificar);
		agregarBoton(btnBorrarDatos);
		
		Box contenedor = Box.createHorizontalBox();
		contenedor.add(this.crearEtiqueta("Elemento Seleccionado:", 15f));
		contenedor.add(Box.createHorizontalStrut(20));
		contenedor.add(lblElementoActivo);
		
		cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
		cabecera.add(contenedorDeBotones);
		cabecera.add(contenedor);
	}
	
	protected JButton crearBoton(String mensaje, String url) {
		JButton btn = new JButton(mensaje);
		ImageIcon icono = new ImageIcon(ConfiguracionGlobal.UBICACION_ICONOS + url);

		if(icono.getImageLoadStatus() == MediaTracker.COMPLETE) {
			btn.setIcon(icono);
		}
		
		return btn;
	}
	
	protected void agregarBoton(JButton btn) {
		contenedorDeBotones.add(btn);
		contenedorDeBotones.add(Box.createHorizontalStrut(SEPARACION_ENTRE_BOTONES));
	}
	
	public void definirEvento(int evento, ActionListener al) {
		switch(evento) {
			case CRUD.EVENTO_AGREGAR:
				btnAgregar.addActionListener(al);
				break;
			case CRUD.EVENTO_MODIFICAR:
				btnModificar.addActionListener(al);
				break;
			case CRUD.EVENTO_ELIMINAR:
				btnEliminar.addActionListener(al);
				break;
		}
	}
	
}
