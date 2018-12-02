package com.company;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;




public class Intermediario {
	Scanner teclado;
	AccesoJson acceso;

	public Intermediario() {
		this.teclado = new Scanner(System.in); // Para leer las opciones de										// teclado
		this.acceso = new AccesoJson();
	}
	
	public void ejecucion() {
		int op = 0; // Opcion
		boolean salir = false;

		while (!salir) { // Estructura que repite el algoritmo del menu
							// principal hasta que se la condicion sea falsa
			// Se muestra el menu principal
			System.out.println();
			System.out.println("........ MENU ........... \n" + ".  0 Salir \n" + ".  1 Leer series  \n"
					+ ".  2 Añadir serie \n"+ ".  3. Leer protagonistas  \n" + ".  4. Añadir protagonistas  \n"
					+ "..........................");
			try {
				op = teclado.nextInt();
				teclado.nextLine();
				System.out.println("OPCION SELECCIONADA:" + op);
				switch (op) {
				case 0:
					salir = true;
					break;
				case 1:
					HashMap<Integer, Series> hm = leeSeries();
					pintaSeries(hm);
					break;
				case 2:
					Series auxSerie = this.crearSerie();
					acceso.anadirSerieJSON(auxSerie);
				case 3:
					HashMap<Integer, Protagonistas> hm1 = lee2();
					pintaProtagonistas(hm1);
				case 4:
					Protagonistas auxProta = this.crearProtagonistas();
					acceso.anadirProtagonistasJSON(auxProta);
				default:
					System.out.println("Opcion invalida: marque un numero de 0 a 2");
					break;
				}

				// System.exit(1);

			} catch (InputMismatchException e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 0 a 1");
				teclado.next();
			} catch (Exception e) {
				System.out.println(
						"Excepcion desconocida. Traza de error comentada en el método 'ejecucion' de la clase intermediario");
				// e.printStackTrace();
				System.out.println("Fin ejecución");
				System.exit(-1);
			}
		}

		// teclado.close();

	}

	private HashMap<Integer, Series> leeSeries() {

		HashMap<Integer, Series> hmAux = acceso.lee();

		return hmAux;

	}
	private HashMap<Integer, Protagonistas> lee2() {

		HashMap<Integer, Protagonistas> hmAux2 = acceso.leeProtagonistas();

		return hmAux2;

	}
	private void pintaSeries(HashMap<Integer, Series> map) {

		for (Map.Entry<Integer, Series> entry : map.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
	private void pintaProtagonistas(HashMap<Integer, Protagonistas> map) {

		for (Map.Entry<Integer, Protagonistas> entry : map.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
	private Series crearSerie() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca un nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduzca una descripcion: ");
		String descripcion = teclado.nextLine();
		System.out.println("Introduzca una productora: ");
		String productora = teclado.nextLine();
		System.out.println("Introduzca las temporadas: ");
		int temporadas = teclado.nextInt();
		System.out.println("Introduzca el id del protagonista: ");
		int id_protagonistas = teclado.nextInt();
		
		Series jAux = new Series(nombre,descripcion, productora,temporadas,id_protagonistas);
		
		return jAux;

	}
	private Protagonistas crearProtagonistas() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca un nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduzca una representante: ");
		String representante = teclado.nextLine();
		System.out.println("Introduzca una edad: ");
		int edad = teclado.nextInt();
		
		
		Protagonistas jAux = new Protagonistas(nombre,edad, representante);
		
		return jAux;

	}
	
}
