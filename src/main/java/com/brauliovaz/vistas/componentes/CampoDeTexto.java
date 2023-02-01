package com.brauliovaz.vistas.componentes;

import java.awt.*;
import javax.swing.*;

public class CampoDeTexto extends JTextField implements ComponenteDeEntrada{
	private static final long serialVersionUID = 1L;
	
	public CampoDeTexto() {
		super();
	}
	
	@Override
	public Component componente() {
		return this;
	}
	
	@Override
	public Object recuperarValor() {
		return this.getText();
	}
}
