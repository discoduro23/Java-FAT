package main;

import java.util.ArrayList;



public class ListaProcesos {
	
	ArrayList<Proceso> arrayListProcesos;
	
	public ListaProcesos() {
		arrayListProcesos = new ArrayList<Proceso>();
		arrayListProcesos.add(new Proceso("init"));
	}
	
	public void crearProceso(Proceso nuevoProceso) {
		arrayListProcesos.add(nuevoProceso);
		Thread nuevoHilo = new Thread(nuevoProceso);
		nuevoHilo.start();
	}
	
	private boolean procesoExiste(String nombreEstudiar) {
		for(Proceso p : arrayListProcesos) {
			if(p.nombre == nombreEstudiar)
				return true;
		}
		return false;
	}
	
	
	public void pausarProceso(String nombrePausar) {
		if(procesoExiste(nombrePausar)) {
			for(Proceso p : arrayListProcesos) {
				if((p.nombre == nombrePausar) && p.running)
					p.running = false;				
			}
			
		} else
			System.out.println("Error al suspender el proceso " + nombrePausar + ", no existe.");
	}
	
	
	
	public void eliminarProceso(String nombreEliminar) {
		if(procesoExiste(nombreEliminar)) {
			for(Proceso p : arrayListProcesos) {
				if(p.nombre == nombreEliminar) {
					p.kill();
					arrayListProcesos.remove(p);
				}
			}
		} else
			System.out.println("Error al eliminar el proceso " + nombreEliminar + ", no existe.");
	}
	
	public void imprimirProcesos() {
		System.out.println("Procesos: ");
		byte countPID = 0;
		for(Proceso p : arrayListProcesos) {
			System.out.print("PID: " + countPID + " = " + p.nombre + " | ");
			if(p.running)
				System.out.println("En ejecución");
			else
				System.out.println("Suspendido");
			
			countPID++;
		}
	}
	
}
