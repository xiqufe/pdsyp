package es.florida.ae4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	// Metodo: main
	// Descripcion: metodo que gestion la parte servidor de la aplicacion
	// Parametros de entrada: String[]
	// Parametros de salida: no.
	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket socketEscucha = null;
		try {
		socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
		System.err.println("SERVIDOR >>> Error");
		return;
		}
		while (true) {
		Socket conexion = socketEscucha.accept();
		System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo clase Peticion");
		Peticion p = new Peticion(conexion);
		Thread hilo = new Thread(p);
		hilo.start();
		}
		}


}
