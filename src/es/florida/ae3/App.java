package es.florida.ae3;

import java.util.ArrayList;

public class App {

	// Metodo: main
	// Descripcion: metodo que crea todos los hilos
	// Parametros de entrada: String[]
	// Parametros de salida: no.
	public static void main(String[] args) throws InterruptedException {
		// Mineros y mina
		int suma = 0;
		Mina mina = new Mina(100);
		ArrayList<Minero> mineros = new ArrayList<Minero>();
		Thread t;
		for (int i = 0; i < 10; i++) {
			Minero minero = new Minero(mina);
			mineros.add(minero);
			t = new Thread(minero);
			t.setName("Minero " + i);
			t.start();
		}

		// Ventilador
		Ventilador v = new Ventilador();

		// Hilo productor
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					v.encenderVentilador();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// Hilo consumidor
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					v.apagarVentilador();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// Iniciar los hilos
		t1.start();
		t2.start();
		// Finalizar hilos, t1 antes que t2
//		t1.join();
//		t2.join();

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Minero m : mineros) {
			suma += m.bolsa;
		}
		System.out.println("Stock de la mina: " + mina.stock);
		System.out.println("Sacado por los mineros: " + suma);
	}

}
