package main;

public class ReiniciarTMP extends Proceso implements Runnable {
	private SistemaFat fat;
	
	public ReiniciarTMP(SistemaFat fat,String nombre) {
		super(nombre);
		this.fat = fat;
	}

	@Override
	public void run() {
        while (running) {
            try {
                System.out.println("Tmp Eliminado");
            	Thread.sleep(5000); //5 Segundos
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fat.eliminarDirectorio("C:/tmp");
            fat.anadirDirectorio("tmp", "C:");
  
        }
    }
}
