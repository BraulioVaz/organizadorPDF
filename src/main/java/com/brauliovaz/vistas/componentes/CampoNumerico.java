package com.brauliovaz.vistas.componentes;

import java.awt.*;
import javax.swing.*;

public class CampoNumerico extends JSpinner implements ComponenteDeEntrada{
	private static final long serialVersionUID = 1L;
	
	public CampoNumerico() {
		this.setModel(new SpinnerNumberModel());
	}
	
	public void aceptaNegativos(boolean sonAceptados) {
		SpinnerNumberModel modelo = (SpinnerNumberModel) this.getModel();
		
		if(sonAceptados) {
			modelo.setMinimum(Integer.MIN_VALUE);
		}
		else {
			modelo.setMinimum(new Integer(0));
		}
	}
	
	@Override
	public Component componente() {
		return this;
	}
	
	@Override
	public Object recuperarValor() {
		return this.getValue();
	}
}
