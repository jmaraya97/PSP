package paquete;

/**
 * 
 * @author Juan Martín Ayala
 *
 *         Clase que ejecuta el codigo
 */
public class Principal {
	/**
	 * Se declara el array de tarjetas estatico para poder usarse en otras clases
	 * Esto es a causa de que durante la creacion del codigo tuve bastantes
	 * problemas tontos y decidi ponerlo asi como solucion rapida
	 */
	static Tarjeta[] tarjetas = new Tarjeta[5];

	public static void main(String[] args) {
		/**
		 * listaTrabajadores--> array de trabajadores con 5 huecos utilTar--> objeto
		 * utilidades necesarios para el trabajador ordenador--> objeto ordenador
		 */
		Trabajador[] listaTrabajadores = new Trabajador[5];
		UtilidadesTarjeta utilTar = new UtilidadesTarjeta();
		Ordenador ordenador = new Ordenador();

		/**
		 * bucle que crea las tarjetas
		 */
		for (int i = 0; i < tarjetas.length; i++) {
			tarjetas[i] = new Tarjeta(i);
		}

		/**
		 * bucle que crea los 4 primeros trabajadores
		 */
		for (int i = 0; i < 4; i++) {
			listaTrabajadores[i] = new Trabajador(i, ordenador, utilTar);
			listaTrabajadores[i].start();
		}
		/**
		 * codigo que crea al ultimo trabajador Este codigo va a parte ya que al
		 * trabajador se le pasa por parametros el numero de su tarjeta izquierda y su
		 * tarjeta derecha
		 */
		listaTrabajadores[listaTrabajadores.length - 1] = new Trabajador(4, 0, ordenador, utilTar);
		listaTrabajadores[listaTrabajadores.length - 1].start();

	}

}
