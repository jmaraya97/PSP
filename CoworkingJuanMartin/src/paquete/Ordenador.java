package paquete;

/**
 * 
 * @author Juan Martín Ayala
 *
 *         Clase Ordenar que tiene los datos del Ordenador y su utilidad
 *
 */
public class Ordenador {
	/**
	 * Atributos enUso--> booleano que indica si el ordenador está ocupado o no
	 */
	private boolean enUso;

	/**
	 * Constructor Inicializa la variable a falso ya que nadie empieza usandolo
	 */
	public Ordenador() {
		this.enUso = false;
	}

	/**
	 * **REGION CRITICA** Metodo que, cuando el ordenador pasa a ser usado por una
	 * persona, hace que el resto de trabajadores no pueda entrar al ordenador y
	 * establece el boolean a TRUE, con esto, el actual hilo se paraliza hasta que
	 * se haga un notify() o notifyAll(), y al ser un metodo sincronizado, impide
	 * que otros hilos usen el objeto mientras ya haya un hilo dentro.
	 * 
	 * @param persona
	 * @throws InterruptedException
	 */
	public synchronized void ordenadorEnUso(int persona) throws InterruptedException {
		while (enUso) {
			wait();
		}
		System.out.println("***** PC EN USO POR TRABAJADOR " + persona + " *****");
		this.setEnUso(true);
	}

	/**
	 * **REGION CRITICA** Metodo que realiza el notifyAll() y vuelve a establecer el
	 * booleano enUso a false para avisar al resto de hilos de que este objeto
	 * vuelve a poder ser usado.
	 * 
	 * @param persona
	 */
	public synchronized void dejarDeUsar(int persona) {
		this.setEnUso(false);
		System.out.println("****** TRABAJADOR " + persona + " ABANDONA EL PC ******");
		notifyAll();
	}

	/**
	 * Getters y Setters
	 */
	public boolean isEnUso() {
		return enUso;
	}

	public void setEnUso(boolean enUso) {
		this.enUso = enUso;
	}

}
