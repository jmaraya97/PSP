package paquete;

/**
 * 
 * @author Juan Martín Ayala
 *
 *         Clase que contiene los datos y utilidades de los trabajadores. Los
 *         trabajadores son HILOS que extienden de la clase THREAD
 */

public class Trabajador extends Thread {
	/**
	 * idpersona--> numero que identifica a la persona 
	 * 
	 * tarjetaIzquierda--> tarjeta
	 * que se encuentra a la izquierda del trabajador, está sin asignar para que,
	 * una vez el trabajador haya cogido la tarjeta, guarde su informacion en este
	 * atributo para trabajar con mas facilidad en la clase
	 * 
	 * tarjetaDerecha--> tarjeta que se encuentra a la derecha del trabajador. Idem
	 * del atributo anterior
	 * 
	 * idTarjetaIzquierda y idTarjetaDerecha--> numero de las tarjetas a su izquierda y su derecha respectivamente
	 * 
	 * ordenador--> recibe por su constuctor el ordenador
	 * 
	 * utiles-->recibe por su constructor la clase utilidades 
	 */
	private int idPersona;
	private Tarjeta tarjetaIzquierda = null;
	private Tarjeta tarjetaDerecha = null;
	private int idTarjetaIzquierda;
	private int idTarjetaDerecha;
	private Ordenador ordenador;
	private UtilidadesTarjeta utiles;

	/**
	 * Constructor para los trabajadores 0, 1, 2, 3
	 * @param idPersona
	 * @param ordenador
	 * @param utiles
	 */
	public Trabajador(int idPersona, Ordenador ordenador, UtilidadesTarjeta utiles) {
		this.idPersona = idPersona;
		this.idTarjetaIzquierda = idPersona;
		this.idTarjetaDerecha = idPersona + 1;
		this.ordenador = ordenador;
		this.utiles = utiles;
	}

	/**
	 * Constructor para el trabajador 4
	 * @param idPersona
	 * @param idTarjetaDerecha
	 * @param ordenador
	 * @param utiles
	 */
	public Trabajador(int idPersona, int idTarjetaDerecha, Ordenador ordenador, UtilidadesTarjeta utiles) {
		this.idPersona = idPersona;
		this.idTarjetaIzquierda = idPersona;
		this.idTarjetaDerecha = idTarjetaDerecha;
		this.ordenador = ordenador;
		this.utiles = utiles;
	}

	/**
	 * Metodo sobreescrito de la clase thread que indica lo que realizará el hilo
	 */
	@Override
	public void run() {
		while (true) {
			try {
				// esperar
				System.out.println("El trabajador " + this.idPersona + " está esperando");
				Thread.sleep(1000);

				// intentarCogerTarjetas
				System.out.println("F" + idPersona + " cogiendo tarjetas");

				while (tarjetaDerecha == null || tarjetaIzquierda == null) {
					if (tarjetaIzquierda == null) {
						tarjetaIzquierda = utiles.cogerTarjeta(idTarjetaIzquierda, this);
					}
					if (tarjetaDerecha == null) {
						tarjetaDerecha = utiles.cogerTarjeta(idTarjetaDerecha, this);
					}
				}
				System.out.println("F" + idPersona + " ha cogido ambas tarjetas");

				// usarOrdenador
				System.out.println("Trabajador " + idPersona + " está intentando entrar al pc");
				ordenador.ordenadorEnUso(idPersona);
				Thread.sleep(1000);

				// dejarOrdenador
				ordenador.dejarDeUsar(idPersona);

				// devolerTarjetas
				utiles.soltarTarjeta(tarjetaIzquierda);
				this.tarjetaIzquierda = null;
				utiles.soltarTarjeta(tarjetaDerecha);
				this.tarjetaDerecha = null;

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * metodo para soltar la tarjeta que tenga en ese momento.
	 * si la derecha esta vacia, intenta vaciar la derecha
	 * 
	 * Esto lo hace vaciando el atributo indicado anteriormente para ello y usando la utilidad
	 */
	public void soltarTarjeta() {
		if (tarjetaDerecha != null) {
			utiles.soltarTarjeta(tarjetaDerecha);
			this.tarjetaDerecha = null;
		} else if (tarjetaIzquierda != null) {
			utiles.soltarTarjeta(tarjetaIzquierda);
			this.tarjetaIzquierda = null;
		}
	}

	/**
	 * Getters y Setters
	 */
	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

}
