package main;

public class SistemaFat {
	Cluster[] Cluster;
	FAT Fat;
	Directorio DirRaiz;
	
	
	public SistemaFat(int capacidad) {
		Cluster = new Cluster[capacidad];
		Fat = new FAT(capacidad);
		DirRaiz = new Directorio("C:/");
		formatear();
	}
	
	
	
	public void formatear() {
		
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			Entrada.Disponible = true;
		}
		DirRaiz.ListaEntradasDirectorios.clear();
	}
	
	
	
	public void mostrarFat() {
		System.out.println("Sistema Fat: ");
		System.out.println("Fat: ");
		int i = 0;
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			System.out.println("\tEntrada_" + i + ": ");
			i++;
			System.out.println("\t\tDisponible = " + Entrada.Disponible + " | Siguiente = "  + Entrada.siguiente + " | Fin = " + Entrada.Fin);
		}
	}
	public void mostrarDir(Directorio carpeta, int profundidad) {
		int i=0;
		for(int j=0; j<profundidad; j++)System.out.print("-----");
		System.out.println("Directorio "+ carpeta.nombre +": ");
		for (Entrada_Directorio Entrada: carpeta.ListaEntradasDirectorios) {
			for(int j=0; j<profundidad; j++)System.out.print("-----");
			System.out.println("Posicion_" + i + ": ");
			i++;
			if (!Entrada.esDir) {
				for(int j=0; j<profundidad; j++)System.out.print("-----");
				System.out.println("Nombre: " + Entrada.nombre + " | Tipo: Archivo | Cluster Inicio: " + Entrada.ClusterInicio);
			}
			else {
				for(int j=0; j<profundidad; j++)System.out.print("-----");
				System.out.println("Nombre: " + Entrada.nombre + " | Tipo: Directorio | Cluster Inicio: " + Entrada.ClusterInicio);
				int aux = profundidad + 1;
				Directorio nCarpeta = (Directorio) Cluster[Entrada.ClusterInicio];
				mostrarDir(nCarpeta, aux);
			}
			
			
		}
	}
	
	
	public void anadirArchivo(String nombre, String ruta, int tamArchivo) {
		int[] listaClusters  = buscarClustersVacios(tamArchivo);
		Entrada_Directorio aux = new Entrada_Directorio(listaClusters[0], false);
		Directorio Daux = buscarDir(ruta, DirRaiz);
		if(Daux != null) Daux.ListaEntradasDirectorios.add(aux);
		else System.out.println("Directorio no encontrado");
		/*for (int i = 0; i< listaClusters.length; i++) {
			System.out.println(listaClusters[i]);
		}*/
		actualizarFat(listaClusters);
		introducirEnCluster(listaClusters, nombre, false);
		Parte_de_Archivo au2 = new Parte_de_Archivo(nombre);
		aux.nombre = au2.dato;
		

	}
	public void anadirDirectorio(String nombre, String ruta) {
		int[] cluster  = buscarClustersVacios(1);
		Entrada_Directorio aux = new Entrada_Directorio(cluster[0], true);
		Directorio Daux = buscarDir(ruta, DirRaiz);
		if(Daux != null) Daux.ListaEntradasDirectorios.add(aux);
		else System.out.println("Directorio no encontrado");
		//System.out.println(cluster[0]);
		
		actualizarFat(cluster);
		introducirEnCluster(cluster, nombre, true);
		aux.nombre = nombre;
		

	}
	
	
	public int[] buscarClustersVacios(int tam){
		int j = 0;
		
		if(tam <= 0) System.out.println("Estas buscando 0 o menos clusters vacios");
		
		int[] resultado = new int[tam];		
		for(int i = 0; j < tam && i < Fat.ListaEntradasFat.size(); i++) {
			if(Fat.ListaEntradasFat.get(i).Disponible) {
				resultado[j] = i;
				j++;
			}
		}
		return resultado;
	}
	
	
	public void actualizarFat(int[] clustersArchivos) {
		for(int i = 0; i < clustersArchivos.length; i++) {
			Entrada_Fat aux = Fat.ListaEntradasFat.get(clustersArchivos[i]);
			aux.Disponible = false;
			
			if(i == clustersArchivos.length-1) aux.Fin = true;
			else aux.siguiente = clustersArchivos[i + 1];

			//System.out.println(clustersArchivos[i]);
		}
	}
	
	
	public void introducirEnCluster(int[] idClusDis, String nombre, boolean esDir) {
		for(int i=0; i<idClusDis.length; i++) {
			if(!esDir) Cluster[idClusDis[i]] = new Parte_de_Archivo(nombre + " " + i);
			else Cluster[idClusDis[i]] = new Directorio(nombre);
		}
	}
	

	/*public Directorio buscarDir(String ruta, Directorio carpeta) {
		if(ruta == carpeta.nombre) return carpeta;
		else
		{
			for (Entrada_Directorio Entrada: carpeta.ListaEntradasDirectorios) {
				if (Entrada.nombre == ruta && Entrada.esDir) {
					return (Directorio) Cluster[Entrada.ClusterInicio];
				}
				else if (Entrada.esDir) {
					Directorio aux = (Directorio) Cluster[Entrada.ClusterInicio];
					return buscarDir(ruta, aux);
				}
			}	
		}
		return null;
	}*/
	
	public Directorio buscarDir(String ruta, Directorio carpeta) {
		String[] subRutas = ruta.split("/");
		
		
		for (Entrada_Directorio Entrada: carpeta.ListaEntradasDirectorios) {
			if(Entrada.nombre == subRutas[i]);
			
		}
			
		
		return null;
	} 
	
}

