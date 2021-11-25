package main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaFat Sistema = new SistemaFat(10);


		Sistema.formatear();
		System.out.println("funciona??");
		Sistema.anadirArchivo("peli","hola/temp",3);
		//Sistema.anadirArchivo("Gay el que lo lea","hola/temp",3);
		//Sistema.buscarDir("hola/temp");
		Sistema.mostrar();
		
	}

}
