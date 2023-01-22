package com.brauliovaz.vistas;

import java.awt.*;
import javax.swing.*;

public abstract class VistaBase extends JPanel{
	private static final long serialVersionUID = 1L;

	public void mostrarAlerta(String titulo,String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE, null);
	}
	
	public JLabel crearEtiqueta(String texto, float tam) {
		JLabel etiqueta = new JLabel(texto);
		Font fuente = etiqueta.getFont();
		
		etiqueta.setFont(fuente.deriveFont(tam));
		
		return etiqueta;
	}
}
