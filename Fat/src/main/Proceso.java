package main;

public class Proceso implements Runnable {
	
	String nombre;
	boolean running;
	
	public Proceso(String nombre) {
		this.nombre = nombre + ".exe";
		running = true;
	}
	
	public void kill() {
		running = false;
	}

	@Override
	public void run() {}
	
}
