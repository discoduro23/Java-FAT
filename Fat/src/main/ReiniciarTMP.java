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
                //System.out.println("TMP BORRADO");
            	Thread.sleep(5000); //5 Segundos
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Eliminar directorio TMP
            fat.eliminarDirectorio("tmp");
            // System.out.println("Eliminado directorio TMP");
  
        }
    }
}
