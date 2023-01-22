package com.brauliovaz.vistas;

import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class Explorador extends VistaBase{
	private static final long serialVersionUID = 1L;
	private JTree directorio;
	private DefaultMutableTreeNode raiz;
	
	public Explorador() {
		raiz = new DefaultMutableTreeNode();
		directorio = new JTree(raiz);
		
		directorio.setRootVisible(false);
		this.add(directorio);
	}
	
	public void agregarCarpeta(Object supercarpeta, Object carpeta) {
		DefaultMutableTreeNode rama;
		
		if(carpeta == null) {
			return;
		}
		
		rama = new DefaultMutableTreeNode(carpeta);
		
		if(supercarpeta == null) {
			raiz.add(rama);
		}
		else {
			obtenerNodo(supercarpeta).add(rama);
		}
	}
	
	public void agregarLibro(Object carpeta, Object libro) {
		DefaultMutableTreeNode hoja;
		
		if(libro == null) {
			return;
		}
		
		hoja = new DefaultMutableTreeNode(libro, false);
		
		if(carpeta == null) {
			raiz.add(hoja);
		}
		else {
			obtenerNodo(carpeta).add(hoja);
		}
	}
	
	public DefaultMutableTreeNode obtenerNodo(Object nodo) {
		ArrayList<DefaultMutableTreeNode> nodos = new ArrayList<DefaultMutableTreeNode>();
		DefaultMutableTreeNode nodoActual = null;
		
		nodos.addAll(obtenerHijos(raiz));
		
		while(nodos.size() != 0) {
			nodoActual = nodos.get(0);
			
			if(nodoActual.getUserObject().equals(nodo)) {
				return nodoActual;
			}
			
			nodos.addAll(obtenerHijos(nodoActual));
			nodos.remove(nodoActual);
		}
		
		return null;
	}
	
	private List<DefaultMutableTreeNode> obtenerHijos(DefaultMutableTreeNode nodo){
		ArrayList<DefaultMutableTreeNode> hijos = new ArrayList<DefaultMutableTreeNode>();
		DefaultMutableTreeNode hijo = null;
		
		for(int i = 0; i < nodo.getChildCount(); i++) {
			hijo = (DefaultMutableTreeNode) nodo.getChildAt(i);
			hijos.add(hijo);
		}
		
		return hijos;
	}
}
