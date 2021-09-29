package es.florida.ae1;

import java.util.ArrayList;
import java.util.List;

public class ej2 {

	public static void main(String[] args) {
		String[] nombres = new String[6];
		nombres[0]="Ximo";
		nombres[1]="Sergio";
		nombres[2]="Pau";
		nombres[3]="Enrique";
		nombres[4]="Joaquin";
		nombres[5]="Alejandro";

		for(String s:nombres) {
			System.out.println(s);
		}
		
		//Amb llista
		List<String> nombresllista = new ArrayList<String>();
		nombresllista.add("Ximo");
		nombresllista.add("Sergio");
		nombresllista.add("Pau");
		nombresllista.add("Enrique");
		nombresllista.add("Joaquin");
		nombresllista.add("Alejandro");
		
		for(String s:nombresllista) {
			System.out.println(s);
		}
	}

}
