package testPSP;

/**
 * IMPORTS
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
 * 
 * @author Juan Martín Ayala
 * 2º DAM
 * 
 * 
 * Clase Server Principal. Como su nombre indica, esta clase representa al servidor
 */
public class ServerPrincipal {

	public static void main(String[] args) {
		
		try {
			/**
			 * Declaracion de Map, ServerSocket y bind del serverSocket con la direccion indicada
			 */
			HashMap<String, String> buzon = new HashMap<String, String>(); //inicializacion de Map
			ServerSocket servSock = new ServerSocket(); //inicializacion de ServerSocket
			System.out.println("Iniciando Servidor");
			InetSocketAddress isa = new InetSocketAddress("localhost", 5000); //creacion de la addres compuesta por IP y el puerto
			servSock.bind(isa); //asignacion del socket a la addres

			//Creacion de varias entradas del buzon
			buzon.put("Juan", "mensaje para Juan");
			buzon.put("Paco", "mensaje para Paco");
			buzon.put("Carlos", "un saludo Carlos");

			System.out.println("Esperando clientes...");

			//bucle infinito que ejecuta el servidor continuamente
			while (true) {
				Socket s = null; //inicializamos un socket
				try {
					s = servSock.accept();//aceptamos la peticion del cliente y le asignamos un socket nuevo para el 
					DataInputStream dis = new DataInputStream(s.getInputStream()); //inicializamos la forma en que se van a comunicar el cliente y el hilo
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					System.out.println("Creando una nueva conexión para el cliente");
					ConexionHilo conexHilo = new ConexionHilo(s, dis, dos, buzon); //creamos el hilo pasandole los datos que acabamos de recoger
					conexHilo.start();//iniciamos el hilo de la conexion
					System.out.println("Conexión creada");
				} catch (Exception e) {
					s.close(); //en caso de error cerramos el socket
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
