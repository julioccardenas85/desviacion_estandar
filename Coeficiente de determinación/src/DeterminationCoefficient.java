/*Coeficiente de determinación
Descripción: se calcula valor de coeficiente de determinación con base en dos arreglos
ingresados por usuario, así como los valores de regresión simple a y b
Desarrollador: Julio César Cárdenas Alvarado
Institución: CUCEA MTI
Realización: 09/11/2021*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeterminationCoefficient {
	public static void main (String args []) {
		Scanner dataIn = new Scanner(System.in);
		boolean repeat = false;
		boolean repeatB = false;
		char option;
		int projects = 0;
		double eMedia = 0;
		double e_e = 0;
		double e_e2 = 0;
		double regressionA = 0;
		double regressionB = 0;
		double r2 = 0;
		
		do {
			System.out.print("Coeficiente de determinación\n"
					+ "\n¿Cuántos proyectos se evaluarán?: ");
			projects = dataIn.nextInt();
			System.out.println();
			int ldc [] = new int [projects];
			int y [] = new int [projects];
			double ei_e [] = new double [projects];
			double ei_e2 [] = new double [projects];
			double y2 [] = new double [projects];
			double y_y [] = new double [projects];
			double y_y2 [] = new double [projects];
			
			System.out.println("Ingrese valores de tamaño de líneas de código para todos los proyectos:");
			getVector(dataIn, ldc, repeat);
			System.out.println("Ingrese valores de esfuerzo para codificar cada proyecto:");
			getVector(dataIn, y, repeat);
			System.out.print("Ingrese el valor de regresión lineal simple a:");
			regressionA = dataIn.nextDouble();
			System.out.print("Ingrese el valor de regresión lineal simple b:");
			regressionB = dataIn.nextDouble();
			eMedia = calcEMedia(y, eMedia);
			calcEi_e(y, eMedia, ei_e);
			e_e = calcEi_e2(ei_e, ei_e2, e_e);
			calcY2(regressionA, regressionB, ldc, y2);
			calcY_Y(y, y2, y_y);
			e_e2 = calcY_Y2(y_y, y_y2, e_e2);
			r2 = calcR2(e_e, e_e2, r2);
			System.out.println("El coeficiente de determinación es: " + r2 + "\n");
			
			do {
				System.out.println("¿Desea hacer un nuevo cálculo? s/n");
				option = dataIn.next().charAt(0);
				if (option == 's') {
					System.out.println();
					repeat = true;
					repeatB = false;
					break;
				}
				else if (option == 'n') {
					System.out.println("Programa terminado. ¡Hasta luego!");
					repeat = false;
					repeatB = false;
					break;
				}
				
				else {
					System.out.println("Opción incorrecta");
					repeatB = true;
					break;
				}
			}
			while (repeatB == true);
		}
		
		while (repeat == true);
	}
	
	public static void printVector(double data []) {
		System.out.print("Los datos ingresados son: ");
		
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		
		System.out.println();
	}
	
	public static void getVector (Scanner dataIn, int vector[], boolean repeat) {
		for (int i = 0; i < vector.length; i++) {
			do {
				try {
					System.out.print("Ingrese el valor del proyecto " + (i+1) + ":");
					vector[i] = dataIn.nextInt();
					repeat = false;
				}
				catch (InputMismatchException e) {
					System.err.println("Valor incorrecto. Introduzca un número.");
					dataIn = new Scanner( System.in );
					repeat = true;
				}
			} while (repeat);
		}
		System.out.println();
	}
	
	public static void calcY2 (double regressionA, double regressionB, int ldc [], double y2 []) {
		for (int i = 0; i < ldc.length; i++) {
			y2[i] = regressionA + (regressionB * ldc[i]);
		}
	}
	
	public static void calcY_Y (int y[], double y2[], double y_y[]) {
		for (int i = 0; i < y.length; i++) {
			y_y[i] = y[i] - y2[i];
		}
	}
	
	public static double calcY_Y2 (double y_y[], double y_y2[], double e_e2) {
		for (int i = 0; i < y_y.length; i++) {
			y_y2[i] = y_y[i] * y_y[i];
			e_e2 += y_y2[i];
		}
		return e_e2;
	}
	
	public static double calcEMedia (int y[], double eMedia) {
		int sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += y[i];
		}
		eMedia = sum/y.length;
		return eMedia;
	}
	
	public static void calcEi_e (int y[], double eMedia, double ei_e[]) {
		for (int i = 0; i < y.length; i++) {
			ei_e[i] = y[i] - eMedia;
		}
	}
	
	public static double calcEi_e2 (double ei_e[], double ei_e2[], double e_e) {
		for (int i = 0; i < ei_e.length; i++) {
			ei_e2[i] = ei_e[i] * ei_e[i];
			e_e += ei_e2[i];
		}
		return e_e;
	}
	
	public static double calcR2 (double e_e, double e_e2, double r2) {
		r2 = (e_e - e_e2)/e_e;
		return r2;
	}
}

