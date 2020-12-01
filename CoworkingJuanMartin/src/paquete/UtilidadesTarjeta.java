package paquete;

/**
 * 
 * @author Juan Martín Ayala
 *
 *         Clase que contiene las utilidades de uso de la tarjeta.
 */
public class UtilidadesTarjeta {

	/**
	 * Metodo con sentencia sincronizada permite especificar el objeto que
	 * proporciona el monitor en vez de ser el objeto por defecto que se está
	 * ejecutando como ocurre en métodos sincronizados. Lo que se consigue es
	 * bloquear a los hilos únicamente en las secciones de código especificadas. De
	 * esta forma, los hilos pueden seguir usando este objeto.
	 * 
	 * Lo que hace este metodo es usar la tarjeta como monitor y establecer su
	 * boolean Ocupado a false y el poseedor a nulo y luego notifica a todos los
	 * hilos que este codigo esta libre de usarse de nuevo.
	 * 
	 * Se usa para soltar las tarjetas
	 * 
	 * @param tarjeta
	 */
	public void soltarTarjeta(Tarjeta tarjeta) {
		Tarjeta t = tarjeta;
		synchronized (t) {
			t.setOcupada(false);
			t.setPoseedor(null);
			System.out.println("Se ha soltado la tarjeta " + t.getNumero());
			t.notifyAll();
		}
	}

	/**
	 * Metodo para coger una tarjeta por un trabajador. De nuevo se usa la tarjeta
	 * como monitor de la sentencia sincronizada Mientras la tarjeta este ocupada,
	 * el hilo ocupara este metodo durante 0.1 segundo, simulando que esta esperando
	 * a ver si su tarjeta se queda libre porque algun trabajador la suelte justo en
	 * ese momento. Si al cabo del tiempo no se queda libre, dejara de intentar
	 * cogerla En caso de que esté libre, se pondrá ocupada y sera asignada al
	 * trabajador
	 * 
	 * @param idTarjeta
	 * @param trabajador
	 * @return
	 * @throws InterruptedException
	 */
	public Tarjeta cogerTarjeta(int idTarjeta, Trabajador trabajador) throws InterruptedException {
		Tarjeta t = Principal.tarjetas[idTarjeta];
		synchronized (t) {
			while (t.isOcupada()) {
				t.wait(100);
				if (t.isOcupada()) {
					trabajador.soltarTarjeta();
				}
			}
			t.setOcupada(true);
			t.setPoseedor(trabajador);
			System.out.println("Persona " + trabajador.getIdPersona() + ": cogiendo tarjeta " + t.getNumero());
			return t;
		}
	}

}
