package es.florida.ae2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	//Metodo: lanzarProbabilidad
	//Descripcion: Es el metodo que se encarga de crear el proceso
	//Parametros de entrada: Un String nombre, un String posicion y un String velocidad que se le pasa del main al leer un fichero.
	//Parametros de salida: no.
	public void lanzarProbabilidad(String nombre, String posicion, String velocidad) {
		String clase = "es.florida.ae2.Probabilidad";
		String classpath = System.getProperty("java.class.path");

		try {
		ProcessBuilder builder = new ProcessBuilder("java", "-cp", classpath, clase, nombre, posicion, velocidad);
		Process process = builder.inheritIO().start();
		process.waitFor();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Metodo: main
	//Descripcion: Al ejecutar el programa se lee el archivo NEOs.txt y por bloques de nucleos se calcula la probabilidad de cada NEO
	//Parametros de entrada: no
	//Parametros de salida: no
	public static void main(String[] args) {
		long tiempoInicio = System.nanoTime();
		long tiempoNEO;
		String dir = "NEOs.txt";
		List<String> neos = new ArrayList<String>();
		Lanzador l = new Lanzador();
			try {
			File fitxer = new File(dir);
			FileReader fr = new FileReader(fitxer);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			
			while (linea != null) {
				neos.add(linea);
				linea = br.readLine();
			}
			br.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			//Ejecucion por bloques segun tus cores
			int cores = Runtime.getRuntime().availableProcessors();
			int contador=0;
			int contadorNeos=0;
			int ejecutar=(int)(Math.ceil((float) neos.size()/cores));
			
			for(int x=0;x<ejecutar;x++) {


				
				for(int i=0;i<cores;i++) {
					if(contadorNeos==neos.size()) {
						break;
					}
					String[] lineas = neos.get(i+contador).split(",");
					l.lanzarProbabilidad(lineas[0], lineas[1], lineas[2]);
					contadorNeos+=1;
				}
				contador+=cores;
			}
			
			//tiempo
			long tiempoFinal = System.nanoTime();
			long tiempoApp = (tiempoFinal-tiempoInicio)/1000000000;
			long tiempoNeo = tiempoApp/neos.size();
			
			System.out.println("Tiempo Medio NEO: "+tiempoNeo+"s");
			System.out.println("Tiempo Aplicacion: "+tiempoApp+"s");
	}

}
