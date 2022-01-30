package main;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuManager extends Proceso implements Runnable {
	
	SistemaFat Sistema;
	ListaProcesos procesos;
	
	public MenuManager() {
		super("MenuManager");
		Sistema = new SistemaFat(20);
		procesos = new ListaProcesos();
		Sistema.formatear();
		procesos.crearProceso(this);
	}
	
	public void initMenu() {
	}
	public void mostrarMenu() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while(!salir){
            System.out.println("\n=========Escoja una opción del menú=========");
            System.out.println("1. Crear Archivo");
            System.out.println("2. Crear Directorio");
            System.out.println("3. Mostrar Metadatos"); //DANIEL
            System.out.println("4. Eliminar Archivo"); //GERMAN
            System.out.println("5. Eliminar Directorio"); //GERMAN
            System.out.println("6. Mover Archivo");//GER
            System.out.println("7. Mostrar directorio");
            System.out.println("8. Matar Proceso");
            System.out.println("9. Crear Proceso");
            System.out.println("10. Listar procesos");
            System.out.println("11. Empezar borraTMP");
            System.out.println("12. Salir\n\n");
            System.out.print("Escoja su opción: ");


            try {
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                    	clearConsole();
                    	System.out.println("\nHas seleccionado la opcion 1 \"Crear Archivo\"");
                    	anadirArchivo();
                        break;
                    case 2:
                    	clearConsole();
                        System.out.println("\nHas seleccionado la opcion 2 \"Crear Directorio\"");
                    	crearDir();
                        break;
                    case 3:
                    	clearConsole();
                        System.out.println("\nHas seleccionado la opcion \"Mostrar Metadatos\"");
                        mostrarMetaDatos();
                        break;
                    case 4:
                    	clearConsole();
                        System.out.println("\nHas seleccionado la opcion 4 \"Eliminar Archivo\"");
                        eliminarArchivo();
                        break;
                    case 5:
                    	clearConsole();
                        System.out.println("\nHas seleccionado la opcion 5 \"Eliminar Directorio\"");
                        eliminarDir();
                        break;
                    case 6:
                    	clearConsole();
                        System.out.println("\nHas seleccionado la opcion 6 \"Mover Archivo\"");
                        moverArchivo();
                        break;
                    case 7:
                    	clearConsole();
                    	System.out.println("\nHas seleccionado la opcion 7 \"Mostrar directorio\"");
                    	mostrarDirectorio();
                        break;
                    case 8:
                    	clearConsole();
                    	System.out.println("\nHas seleccionado la opcion 8 \"Matar Proceso\"");
                        matarProceso();
                        break;
                    case 9:
                    	clearConsole();
                    	System.out.println("\nHas seleccionado la opcion 9 \"Crear Proceso\"");
                    	crearProceso();
                    	break;
                    case 10:
                    	clearConsole();
                    	System.out.println("\nHas seleccionado la opcion 10 \"Listar procesos\"");
                    	procesos.imprimirProcesos();
                        break;
                    case 11:
                    	clearConsole();
                    	System.out.println("\nHas seleccionado la opcion 11 \"Empezar borraTMP\"");
                    	borrarTmp();
                        break;
                    case 12:
                    	clearConsole();
                        System.out.println("\nGracias por usar nuestro sistema, hasta luego :)");
                        salir = true;
                        break;
                    default:
                    	clearConsole();
                        System.out.println("\nSolo números entre 1 y 7");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Debes insertar un número");
                sn.nextLine();
            }
        }
        sn.close();
    }
	
	public void anadirArchivo() {
		Scanner adda = new Scanner(System.in);
        System.out.println("Introduce un nombre para el archivo\n");
        String nombrearchivo = adda.nextLine();
        System.out.println("Introduce una ruta para el archivo\n");
        String arcruta = adda.nextLine();
        System.out.println("Introduce un tamaño para el archivo\n");
        int size = adda.nextInt();
        Sistema.anadirArchivo(nombrearchivo, arcruta, size);
        
	}
	public void crearDir() {
		Scanner adda = new Scanner(System.in);
        System.out.println("Introduce un nombre para el directorio\n");
        String nombrearchivo = adda.nextLine();
        System.out.println("Introduce una ruta para el directorio\n");
        String arcruta = adda.nextLine();
        Sistema.anadirDirectorio(nombrearchivo, arcruta);
        
	}
	public void mostrarMetaDatos() {
		Sistema.mostrarFat();
	}
	
	public void mostrarDirectorio() {
		Scanner showd = new Scanner(System.in);
		System.out.println("Introduce la ruta del directorio\n");
		String directo = showd.nextLine();
		Sistema.mostrarDir(directo, 0);
	}
	public void eliminarArchivo() {
		Scanner showd = new Scanner(System.in);
		System.out.println("Introduce la ruta del archivo para eliminarlo\n");
		String archiv = showd.nextLine();
		Sistema.eliminarArchivo(archiv);
	}
	public void eliminarDir() {
		Scanner showd = new Scanner(System.in);
		System.out.println("Introduce la ruta del directorio para eliminarlo\n");
		String direc = showd.nextLine();
		Sistema.eliminarDirectorio(direc);
	}

	public void moverArchivo() {
		Scanner showd = new Scanner(System.in);
		System.out.println("Introduce la ruta Fuente\n");
		String rutafuente = showd.nextLine();
		System.out.println("Introduce la ruta Destino\n");
		String rustdestino = showd.nextLine();
		Sistema.moverArchivo(rutafuente, rustdestino);
	}
	
	public void matarProceso() {
		Scanner showd = new Scanner(System.in);
		System.out.println("Introduce el nombre del proceso para eliminar\n");
		String archiproc = showd.nextLine();
		procesos.eliminarProceso(archiproc+".exe");
	}
	
	void borrarTmp() {
		Scanner showd = new Scanner(System.in);
		ReiniciarTMP ptmp = new ReiniciarTMP(Sistema, "borrarTMP");
		procesos.crearProceso(ptmp);
	}
	
	void crearProceso() {
		Scanner showd = new Scanner(System.in);
		System.out.println("Introduce el nombre del proceso para crearlo\n");
		String archiproc = showd.nextLine();
		
		Proceso nuevo;
		
		if(archiproc == "BorrarTMP" || archiproc == "BorraTMP5Seg") nuevo = new ReiniciarTMP(Sistema, "BorraTMP5Seg");
		else nuevo = new Proceso(archiproc);
		
		procesos.crearProceso(nuevo);
	}
	public void clearConsole() {
        for(int i = 0; i < 60; i++) {
        	System.out.println("\n");
        }
	}
	public void run() {
		while(running) {
			mostrarMenu();
		}
	}
}
