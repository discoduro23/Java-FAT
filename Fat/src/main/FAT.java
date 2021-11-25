package main;

import java.util.ArrayList;

public class FAT {
	ArrayList<Entrada_Fat> ListaEntradasFat = new ArrayList<Entrada_Fat>();

	public FAT(int capacidad) {
		for(int i =0; i<capacidad; i++) {
			Entrada_Fat aux = new Entrada_Fat();
			ListaEntradasFat.add(aux);
		}
	}

	@Override
	public String toString() {
		return "FAT{" +
				"ListaEntradasFat=" + ListaEntradasFat +
				'}';
	}
}
