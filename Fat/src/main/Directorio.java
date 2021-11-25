package main;

import java.util.ArrayList;

public class Directorio extends Cluster{
	ArrayList<Entrada_Directorio> ListaEntradasDirectorios = new ArrayList<Entrada_Directorio>();

	public Directorio() {
	}

	@Override
	public String toString() {
		return "Directorio{" +
				"ListaEntradasDirectorios=" + ListaEntradasDirectorios +
				'}';
	}
}
