package es.florida.ae5;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import com.sun.net.httpserver.HttpServer;

public class Servidor {
	// Metodo: main
	// Descripcion: metodo que gestion la parte servidor
	// Parametros de entrada: String[]
	// Parametros de salida: no.
	public static void main(String[] args) throws Exception {
		String host = "localhost"; // 127.0.0.1
		int puerto = 7777;
		InetSocketAddress direccionTCPIP = new InetSocketAddress(host, puerto);
		int backlog = 0;
		HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);
		GestorHTTP gestorHTTP = new GestorHTTP();
		String rutaRespuesta = "/estufa";
		servidor.createContext(rutaRespuesta, gestorHTTP);

		// Opcion 2 de ejecucion: multihilo con ThreadPoolExecutor
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		servidor.setExecutor(threadPoolExecutor);
		servidor.start();
		System.out.println("Servidor HTTP arranca en el puerto " + puerto);
	}
}
