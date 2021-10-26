package es.florida.ae2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;

public class Probabilidad {

	//Metodo: main
	//Descripcion: Este metodo main es el que calcula la probabilidad de cada NEO y crea un archivo con el nombre del NEO y su probabilidad
	//Parametros de entrada: Se le pasa como parametros el nombre del NEO, la posicion y la velocidad
	//Parametros de salida: no
	public static void main(String[] args) {
		String nombreNEO = args[0];
		Double posicionNEO = Double.parseDouble(args[1]);
		Double velocidadNEO = Double.parseDouble(args[2]);
		
		//Calculo probabilidad
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicionNEO = posicionNEO + velocidadNEO * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		String resultadodecimal = df.format(resultado);
	
		//Mensajes
		if(resultado > 10) {
		System.err.println("=== ALERTA MUNDIAL === ");
		System.err.println(nombreNEO+": "+resultadodecimal+"%");
		} else {
			System.out.println("=== TRANQUILIDAD === ");
			System.out.println(nombreNEO+": "+resultadodecimal+"%");
		}
		
		//Fichero
		try {
			File neo = new File(nombreNEO+".txt");
			FileWriter fw = new FileWriter(neo);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(resultadodecimal);
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
