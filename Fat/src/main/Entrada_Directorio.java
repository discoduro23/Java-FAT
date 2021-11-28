package main;

public class Entrada_Directorio {
	String nombre;
	boolean esDir;
	int ClusterInicio;
	
	public Entrada_Directorio(int clusterInicio, boolean esDir) {
		ClusterInicio = clusterInicio;
		this.esDir = esDir;
	}


	@Override
	public String toString() {
		return "Entrada_Directorio{" +
				"nombre='" + nombre + '\'' +
				", esDir=" + esDir +
				", ClusterInicio=" + ClusterInicio +
				'}';
	}
}
