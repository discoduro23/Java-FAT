package main;

public class Entrada_Fat {
	boolean Disponible;
	boolean Fin; //Fin
	int siguiente;
	
	public Entrada_Fat() {
		Disponible = true;
	}


	@Override
	public String toString() {
		return "Entrada_Fat{" +
				"Disponible=" + Disponible +
				", Fin=" + Fin +
				", siguiente=" + siguiente +
				'}';
	}
}
