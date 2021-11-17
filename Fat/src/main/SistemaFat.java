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
	public void Formatear() {
		
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			Entrada.Disponible = true;
		}
		DirRaiz.ListaEntradasDirectorios.clear();
	}
}

