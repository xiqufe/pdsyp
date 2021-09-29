package es.florida.ae1;

import java.util.Scanner;

public class ej8 {
	
	public static void intervalo() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Dime un numero: ");
		int num1 = sc.nextInt();
		
		System.out.print("Dime un numero: ");
		int num2 = sc.nextInt();
		
		for(int i = num1; i<=num2; i++) {
		    boolean primo = true;
		    //Si es menor que 2, no es primo
		    if(i<2)
		    {
		        primo = false;
		    }
		    else
		    {
		    	//Si es divisible entre otro numero que no sea 1 o el mismo, entonces no es primo
		        for(int x=2; x*x<=i; x++)
		        {
		            if (i%x==0) {
		            	primo = false;
		            	break;
		            }
		        }
		    }
		    
		    if(primo) {
		    	System.out.println(i+" -> "+" Es Primo");
		    }else {
		    	System.out.println(i+" -> "+" No Es Primo");
		    }

		}
	}

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		
		intervalo();
		
		long fin = System.currentTimeMillis();
		float tiempo = ((float)(fin-inicio)/1000);
		System.out.println("Tiempo de ejecucion: "+tiempo+" segundos");

	}

}
