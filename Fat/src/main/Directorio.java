package main;

import java.util.ArrayList;

public class Directorio extends Cluster{
	String nombre;
	ArrayList<Entrada_Directorio> ListaEntradasDirectorios = new ArrayList<Entrada_Directorio>();

	public Directorio(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Directorio{" +
				"ListaEntradasDirectorios=" + ListaEntradasDirectorios +
				'}';
	}
}
