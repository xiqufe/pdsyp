package es.florida.ae3;

public class Ventilador {
	boolean funcionamiento = true;
	int tiempoVentilador = 1000;

	// Metodo: encenderVentilador
	// Descripcion: Metodo que enciende el ventilador
	// Parametros de entrada: no.
	// Parametros de salida: no.
	public void encenderVentilador() throws InterruptedException {
		while (true) {
			synchronized (this) {
				while (funcionamiento == true)
					wait();
				funcionamiento = true;
				System.out.println("Ventilador: ON");
				notify();
				// Pausa para ver el proceso paso a paso
				Thread.sleep(tiempoVentilador);
			}
		}
	}
	
	// Metodo: apagarVentilador
	// Descripcion: Metodo que apaga el ventilador
	// Parametros de entrada: no.
	// Parametros de salida: no.
	public void apagarVentilador() throws InterruptedException {
		while (true) {
			synchronized (this) {
				while (funcionamiento == false)
					wait();
				funcionamiento = false;
				System.err.println("Ventilador: OFF");
				notify();
				// Pausa para ver el proceso paso a paso
				Thread.sleep(tiempoVentilador);
			}
		}
	}

}
