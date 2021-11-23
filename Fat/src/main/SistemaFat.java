package main;

public class SistemaFat {
	Cluster[] Cluster;
	FAT Fat;
	Directorio DirRaiz;
	
	public SistemaFat(int capacidad) {
		Cluster = new Cluster[capacidad];
		Fat = new FAT(capacidad);
		DirRaiz = new Directorio();
	}
	public void formatear() {
		
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			Entrada.Disponible = true;
		}
		DirRaiz.ListaEntradasDirectorios.clear();
	}
	
	public void mostrar() {
		System.out.println("Sistema Fat: ");
		System.out.println("Fat: ");
		int i = 0, j = 0;
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			System.out.println("Entrada_" + i + ": ");
			i++;
			System.out.println("Disponible: " + Entrada.Disponible + " /Siguiente: "  + Entrada.siguiente + " /Fin: " + Entrada.Fin);
		}
		System.out.println("Directorio Raiz: ");
		for (Entrada_Directorio Entrada: DirRaiz.ListaEntradasDirectorios) {
			System.out.println("Posicion_" + j + ": ");
			j++;
			String aux;
			if (Entrada.esDir) aux = "Directorio";
			else aux = "Archivo";
			System.out.println("Nombre: " + Entrada.nombre + " /Tipo: " + aux + " /Cluster Inicio: " + Entrada.ClusterInicio);
		}
	}
}

