package main;

public class Parte_de_Archivo extends Cluster {
	
	String dato;

	public Parte_de_Archivo(String dato) {
		this.dato = dato;
	}

	@Override
	public String toString() {
		return "Parte_de_Archivo{" +
				"dato='" + dato + '\'' +
				'}';
	}
}
