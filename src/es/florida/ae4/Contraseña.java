package es.florida.ae4;

import java.io.Serializable;
@SuppressWarnings ("serial")
public class Contraseņa implements Serializable {
	String plano;
	String encriptado;
	
	public Contraseņa() {
		this.plano="";
		this.encriptado="";
	}
	
	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getEncriptado() {
		return encriptado;
	}

	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}


}
