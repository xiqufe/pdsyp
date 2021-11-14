package es.florida.ae3;

public class Minero implements Runnable {
	int bolsa;
	int tiempoExtraccion = 150;
	Mina mina;
	
	// Constructor: Minero
	// Descripcion: Se le pasa una mina por parametro para asignarle esa mina
	// Parametros de entrada: Mina
	// Parametros de salida: no.
	public Minero(Mina m) {
		this.bolsa = 0;
		this.mina = m;
	}

	// Metodo: extraerRecurso
	// Descripcion: Metodo que extrae un oro de la mina y se lo guarda en la bolsa del minero
	// Parametros de entrada: String
	// Parametros de salida: no.
	public void extraerRecurso(String nombre) {

		while (mina.stock > 0) {
			synchronized (mina) {
				bolsa++;
				mina.stock--;
				System.out.println(nombre + " ha sacado 1 de oro. Bolsa: " + bolsa);
			}
			
			try {
				Thread.sleep(tiempoExtraccion);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	// Metodo: run
	// Descripcion: Metodo que gestiona la funcion del hilo
	// Parametros de entrada: no.
	// Parametros de salida: no.
	@Override
	public void run() {
		String nombre = Thread.currentThread().getName();

		extraerRecurso(nombre);
	}
}
