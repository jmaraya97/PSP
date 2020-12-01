package paquete;

/**
 * 
 * @author Juan Martín Ayala
 *
 *         Clase Tarjeta que posee los datos de las tarjetas
 * 
 */

public class Tarjeta {
	/**
	 * Atributos numero--> Numero identificador de la tarjeta ocupada--> booleano
	 * que indica si la tarjeta esta en la mano de alguien poseedor--> indica la
	 * persona que tiene la tarjeta
	 */
	private int numero;
	private boolean ocupada;
	private Trabajador poseedor;

	/**
	 * Constuctor
	 * 
	 * @param numero
	 */
	public Tarjeta(int numero) {
		super();
		this.numero = numero;
		this.ocupada = false;
		this.poseedor = null;
	}

	/**
	 * Getters y Setters
	 */
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Trabajador getPoseedor() {
		return poseedor;
	}

	public void setPoseedor(Trabajador poseedor) {
		this.poseedor = poseedor;
	}

}
