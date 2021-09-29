package es.florida.ae1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ej6 {
	
	public static int nums() {
		Scanner sc = new Scanner(System.in);
		
		int resultado = 0;
		
		List<Integer> lista = new ArrayList<Integer>();
		
		for(int i = 0;i<5;i++) {
			System.out.print("Dime un numero: ");
			int num = sc.nextInt();
			lista.add(num);
		}
		
		for(int i=0; i<lista.size();i++) {
			System.out.println("Numero: "+lista.toArray()[lista.size()-i-1]);
			resultado+=Integer.parseInt(lista.toArray()[lista.size()-i-1].toString());
		}
		
		return resultado;
		
	}

	public static void main(String[] args) {
		
		System.out.println("Resultado: "+nums());
	}

}
