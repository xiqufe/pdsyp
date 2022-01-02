package es.florida.ae4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] arg) throws UnknownHostException, IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("CLIENTE >>> Arranca cliente");
		System.out.println("CLIENTE >>> Conexion al servidor");
		InetSocketAddress direccion = new InetSocketAddress("localhost", 1234);
		Socket socket = new Socket();
		socket.connect(direccion);
			
		ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
		Contraseña c = (Contraseña) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor objeto contraseña. ");
		
		System.out.print("Contraseña: ");
		String contrasenya = sc.nextLine();
		c.setPlano(contrasenya);
		ObjectOutputStream cMod = new ObjectOutputStream(socket.getOutputStream());
		cMod.writeObject(c);
		System.out.println("CLIENTE >> Envio al servidor contraseña: " + c.getPlano());
		
		System.out.println("CLIENTE >> Recibo tipos de encriptacion del servidor.");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		System.out.println(bfr.readLine());
		
		String encriptacion = sc.nextLine();
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print(encriptacion+"\n");
		pw.flush();
		System.out.println("CLIENTE >> Envio tipo de encriptacion al servidor.");
	
		inObjeto = new ObjectInputStream(socket.getInputStream());
		c = (Contraseña) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor objeto contraseña final. " + c.getPlano() + " - " +c.getEncriptado());
		inObjeto.close();
		cMod.close();
		socket.close();
	}
}
