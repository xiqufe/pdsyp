package es.florida.ae4;

import java.io.Serializable;
@SuppressWarnings ("serial")
public class Contraseña implements Serializable {
	String plano;
	String encriptado;
	
	// Constructor: Contraseña
	// Descripcion: constructor para el objeto contraseña
	// Parametros de entrada: no.
	// Parametros de salida: no.
	public Contraseña() {
		this.plano="";
		this.encriptado="";
	}
	
	// Metodo: getPlano
	// Descripcion: devuelve el valor Plano del objeto
	// Parametros de entrada: no.
	// Parametros de salida: no.
	public String getPlano() {
		return plano;
	}

	// Metodo: setPlano
	// Descripcion: establece el valor Plano del objeto
	// Parametros de entrada: String
	// Parametros de salida: no.
	public void setPlano(String plano) {
		this.plano = plano;
	}

	// Metodo: getEncriptado
	// Descripcion: devuelve el valor Encriptado del objeto
	// Parametros de entrada: no.
	// Parametros de salida: no.
	public String getEncriptado() {
		return encriptado;
	}

	// Metodo: setEncriptado
	// Descripcion: establece el valor Encriptado del objeto
	// Parametros de entrada: String
	// Parametros de salida: no.
	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}


}
