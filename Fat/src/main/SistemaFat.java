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
	public void anadirArchivo(String nombre, String ruta, int tamArchivo)
	{
		int[] listaClusters  = buscarClustersVacios(tamArchivo);
		actualizarFatArchivos(listaClusters);
		//Hacer desde aqui /!\
		
	}
	
	
	public int[] buscarClustersVacios(int tam){
		int j = 0;
		
		if(tam <= 0) System.out.println("Estas buscando 0 o menos clusters vacios");
		
		int[] resultado = new int[tam];		
		for(int i = 0; j < tam && Fat.ListaEntradasFat.size() < i; i++)
		{
			if(Fat.ListaEntradasFat.get(i).Disponible)
			{
				resultado[j] = i;
				j++;
			}
		}
		return resultado;
	}
	
	public void actualizarFatArchivos(int[] clustersArchivos)
	{
		for(int i = 0; i < clustersArchivos.length; i++)
		{
			Entrada_Fat aux = Fat.ListaEntradasFat.get(clustersArchivos[i]);
			
			aux.Disponible = false;
			
			if(Fat.ListaEntradasFat.get(clustersArchivos[i + 1]) == null) aux.Fin = true;
			else aux.siguiente = clustersArchivos[i + 1];
		}
	}
}

