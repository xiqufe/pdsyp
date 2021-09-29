package es.florida.ae1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ej5 {

	public static void main(String[] args) {
		List<Integer> array = new ArrayList<Integer>();
		array.add(1);
		array.add(5);
		array.add(9);
		array.add(2);
		
		//Collections pertenece a utiles de java como se puede observar en el import.
		//Este tiene un metodo que devuelve en forma de Integer el maximo de una lista que le proporciones.
		int max = Collections.max(array);
		
		System.out.println(max);
		
		

	}

}
