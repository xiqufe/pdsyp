package es.florida.ae4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Peticion implements Runnable {
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public Peticion(Socket socket) {
		this.socket = socket;
	}
	
	public static boolean isAsciiPrintable(char ch) {
	      return ch >= 32 && ch < 127;
	  }
	
	public String encriptarascii(String plano) {
		String[] planoarr = plano.split("");
		String encriptado = "";
		for(String s:planoarr) {
			int sascii = (int) s.charAt(0);
			if(sascii >= 32 && sascii < 127) {
				encriptado+=sascii+1;
			} else {
				encriptado+="*";
			}
		}
		return encriptado;
	}
	
	public static String getMd5(String input)
    {
        try {
  
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            byte[] messageDigest = md.digest(input.getBytes());
  
            BigInteger no = new BigInteger(1, messageDigest);
  
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
  
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	

	public void run() {
		try {
			
			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			Contraseña c = new Contraseña();
			outObjeto.writeObject(c);
			System.err.println("SERVIDOR >> Envio a cliente objeto contraseña ");
			
			
			ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
			Contraseña cMod = (Contraseña) inObjeto.readObject();
			System.err.println("SERVIDOR >> Recibo de cliente: " + cMod.getPlano());
			
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("Encriptacion (MD5/ASCII):" + "\n");
			pw.flush();
			System.err.println("SERVIDOR >> Envio tipos de encriptacion al cliente.");
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bfr = new BufferedReader(isr);
			String linea = bfr.readLine();
			System.err.println("SERVIDOR >> Leo tipo de encriptacion");
			if(linea.equals("ascii")) {
				cMod.setEncriptado(encriptarascii(cMod.plano));
			} else {
				cMod.setEncriptado(getMd5(cMod.plano));
			}
		
			outObjeto = new ObjectOutputStream(socket.getOutputStream());
			outObjeto.writeObject(cMod);
			System.err.println("SERVIDOR >> Envio a cliente objeto contraseña con encriptacion");
			outObjeto.close();
			inObjeto.close ();
//			cliente.close ();
//			servidor.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("SERVIDOR >>> Error.");
		}
	}
}
