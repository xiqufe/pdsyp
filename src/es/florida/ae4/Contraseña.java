package es.florida.ae4;

import java.io.Serializable;
@SuppressWarnings ("serial")
public class Contrase�a implements Serializable {
	String plano;
	String encriptado;
	
	public Contrase�a() {
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
