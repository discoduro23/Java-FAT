package main;

public class SistemaFat {
	Cluster[] Cluster;
	FAT Fat;
	Directorio DirRaiz;
	
	
	public SistemaFat(int capacidad) {
		Cluster = new Cluster[capacidad];
		Fat = new FAT(capacidad);
		DirRaiz = new Directorio("C:");
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
	
	public void mostrarDir(String ruta, int profundidad) {
		String auxRuta;
		Directorio Daux = buscarDir(ruta, DirRaiz);
		if(Daux != null) {
			int i=0;
			for(int j=0; j<profundidad; j++)System.out.print("-----");
			System.out.println("Directorio "+ Daux.nombre +":\n");
			for (Entrada_Directorio Entrada: Daux.ListaEntradasDirectorios) {
				for(int j=0; j<profundidad; j++)System.out.print("-----");
				System.out.println("Posicion_" + i + ": ");
				i++;
				if (!Entrada.esDir) {
					for(int j=0; j<profundidad; j++)System.out.print("-----");
					System.out.println("Nombre: " + Entrada.nombre + " | Tipo: Archivo | Cluster Inicio: " + Entrada.ClusterInicio + "\n");
				}
				else {
					for(int j=0; j<profundidad; j++)System.out.print("-----");
					System.out.println("Nombre: " + Entrada.nombre + " | Tipo: Directorio | Cluster Inicio: " + Entrada.ClusterInicio);
					int aux = profundidad + 1;
					auxRuta = ruta;
					auxRuta += "/";
					auxRuta += Entrada.nombre; 
					
					mostrarDir(auxRuta, aux);
				}
				
				
			}
		}
	}
	
	
	public void anadirArchivo(String nombre, String ruta, int tamArchivo) {
		int[] listaClusters  = buscarClustersVacios(tamArchivo);
		Entrada_Directorio aux = new Entrada_Directorio(listaClusters[0], false);
		Directorio Daux = buscarDir(ruta, DirRaiz);
		if(Daux != null) Daux.ListaEntradasDirectorios.add(aux);
		else System.out.println("Directorio no encontrado");
		actualizarFat(listaClusters);
		introducirEnCluster(listaClusters, nombre, false);
		Parte_de_Archivo au2 = new Parte_de_Archivo(nombre);
		aux.nombre = au2.dato;
		

	}
	public void anadirDirectorio(String nombre, String ruta) {
		int[] cluster  = buscarClustersVacios(1);
		Entrada_Directorio aux = new Entrada_Directorio(cluster[0], true);
		Directorio Daux = buscarDir(ruta, DirRaiz);
		aux.nombre = nombre;
		if(Daux != null) Daux.ListaEntradasDirectorios.add(aux);
		else System.out.println("Directorio no encontrado");
		actualizarFat(cluster);
		introducirEnCluster(cluster, nombre, true);
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
		}
	}
	
	
	public void introducirEnCluster(int[] idClusDis, String nombre, boolean esDir) {
		for(int i=0; i<idClusDis.length; i++) {
			if(!esDir) Cluster[idClusDis[i]] = new Parte_de_Archivo(nombre + " " + i);
			else Cluster[idClusDis[i]] = new Directorio(nombre);
		}
	}
	
	
	public Directorio buscarDir(String ruta, Directorio carpeta) {
		String[] subRutas = ruta.split("/");
		if(subRutas[0] == subRutas[subRutas.length-1]) return carpeta;
		 
		for(int i = 0; i < carpeta.ListaEntradasDirectorios.size(); i++)
		{
			
			if(carpeta.ListaEntradasDirectorios.get(i).esDir && carpeta.ListaEntradasDirectorios.get(i).nombre.equals(subRutas[1]))
			{					
				String auxRuta="";
				for(int j=1; j<(subRutas.length); j++) 
				{
					
					auxRuta +=subRutas[j]; 
					auxRuta += "/";
				}
				Directorio aux = (Directorio) Cluster[carpeta.ListaEntradasDirectorios.get(i).ClusterInicio];
				return buscarDir(auxRuta, aux);
			}
			}
		
		return null;
	}
	
	public void eliminarArchivo(String ruta) {
		int ClusterInicio = 0;
		String[] subRutas = ruta.split("/");
		String auxRuta="";
		for(int j=0; j<(subRutas.length-1); j++) 
		{
			
			auxRuta +=subRutas[j]; 
			auxRuta += "/";
		}
		String nombre = subRutas[subRutas.length-1];
		Directorio Dir = buscarDir(auxRuta, DirRaiz);
		if(Dir != null) {
			for(int i = 0; i < Dir.ListaEntradasDirectorios.size(); i++)
			{
				if(!Dir.ListaEntradasDirectorios.get(i).esDir && Dir.ListaEntradasDirectorios.get(i).nombre.equals(nombre))
				{
					ClusterInicio = Dir.ListaEntradasDirectorios.get(i).ClusterInicio;
					Dir.ListaEntradasDirectorios.remove(i);
					eliminarDeFat(Fat.ListaEntradasFat.get(ClusterInicio));
				}
			}
		}
		else System.out.println("Directorio no encontrado");
	}

	public void eliminarDeFat(Entrada_Fat Entrada) {
		if (!Entrada.Fin) {
			eliminarDeFat(Fat.ListaEntradasFat.get(Entrada.siguiente));
		}
		Entrada.Disponible = true;
	}
	
	public void eliminarDirectorio(String ruta) {
		int ClusterInicio = 0;
		String[] subRutas = ruta.split("/");
		String auxRuta="";
		Directorio Dir = buscarDir(ruta, DirRaiz);
		
		if(Dir != null) {
			for(int i = Dir.ListaEntradasDirectorios.size()-1; i >= 0; i--)
			{
				if(!Dir.ListaEntradasDirectorios.get(i).esDir)
				{
					eliminarArchivo(ruta + "/" + Dir.ListaEntradasDirectorios.get(i).nombre);
				}
				else {
					eliminarDirectorio(ruta + "/" + Dir.ListaEntradasDirectorios.get(i).nombre);
				}
			}
			
			for(int j=0; j<(subRutas.length-1); j++) 
			{
				
				auxRuta +=subRutas[j]; 
				auxRuta += "/";
			}
			Directorio DirAnt = buscarDir(auxRuta, DirRaiz);
			
			for(int i = DirAnt.ListaEntradasDirectorios.size()-1; i >= 0; i--)
			{
				
				if(DirAnt.ListaEntradasDirectorios.get(i).esDir && DirAnt.ListaEntradasDirectorios.get(i).nombre.equals(subRutas[subRutas.length-1]))
				{
					ClusterInicio = DirAnt.ListaEntradasDirectorios.get(i).ClusterInicio;
					DirAnt.ListaEntradasDirectorios.remove(i);
					eliminarDeFat(Fat.ListaEntradasFat.get(ClusterInicio));
					
				}
			}
		}
		
		
	}
	
	public void moverArchivo(String rutaFuente, String rutaDestino) {
		
		String[] subRutaFuente = rutaFuente.split("/");
		String auxRuta="";
		int ClusterInicio = 0;
		for(int j=0; j<(subRutaFuente.length-1); j++) 
		{
			
			auxRuta +=subRutaFuente[j]; 
			auxRuta += "/";
		}
		String nombre = subRutaFuente[subRutaFuente.length-1];
		Directorio DirFuente = buscarDir(auxRuta, DirRaiz);
		
		Directorio DirDestino = buscarDir(rutaDestino, DirRaiz);
		
		for(int i = 0; i < DirFuente.ListaEntradasDirectorios.size(); i++)
		{
			if(!DirFuente.ListaEntradasDirectorios.get(i).esDir && DirFuente.ListaEntradasDirectorios.get(i).nombre.equals(nombre))
			{
				DirDestino.ListaEntradasDirectorios.add(DirFuente.ListaEntradasDirectorios.get(i));
				
				ClusterInicio = DirFuente.ListaEntradasDirectorios.get(i).ClusterInicio;
				DirFuente.ListaEntradasDirectorios.remove(i);
			}
		}
	}
	
	
}




















