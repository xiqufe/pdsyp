package es.florida.ae1;

import java.util.Scanner;

public class ej7 {
	public static void experiencia() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Dime un nombre: ");
		String nombre = sc.nextLine();
		
		System.out.print("Dime años de experiencia: ");
		int anyos = sc.nextInt();
		
		System.out.println("Nombre: "+nombre);
		if(anyos<1) {
			System.out.println("Desarrollador Junior L1 – 15000-18000");
		} else if(anyos<3) {
			System.out.println("Desarrollador Junior L2 – 18000-22000");
		}else if(anyos<5) {
			System.out.println("Desarrollador Senior L1 – 22000-28000");
		}else if(anyos<8) {
			System.out.println("Desarrollador Senior L2 – 28000-36000");
		} else {
			System.out.println("Analista / Arquitecto. Salario a convenir en base a rol");
		}
	}

	public static void main(String[] args) {
		experiencia();

	}

}
