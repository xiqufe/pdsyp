package es.florida.ae1;

public class ej3 {
	
	public static int numerosPares(int num) {
		int resultado=0;
		for(int i=0;i<num;i++) {
			if(i % 2 == 0) {
				resultado+=i;
			}
		}
		
		return resultado;
		
	}

	public static void main(String[] args) {
		int numero = Integer.parseInt(args[0]);
		System.out.println(numerosPares(numero));

	}

}
