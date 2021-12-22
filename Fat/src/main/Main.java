package main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaFat Sistema = new SistemaFat(20);


		Sistema.formatear();
		Sistema.anadirArchivo("peli","C:",3);
		Sistema.anadirDirectorio("pelis", "C:");
		Sistema.anadirArchivo("otraPeli", "C:/pelis",2);
		Sistema.anadirDirectorio("mascosas", "C:");
		Sistema.anadirArchivo("otraPelimas", "C:/mascosas",2);
		Sistema.anadirArchivo("foto","C:",1);
		
		
		
		
		//Sistema.mostrarFat();
		Sistema.mostrarDir(Sistema.DirRaiz, 0);
		
	}

}
