package testPSP;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * 
 * @author Juan Martin Ayala
 * Clase cliente. Esta clase será la que ejecutan los clientes para conectarse al servidor
 * 
 */
public class Cliente {

	public static void main(String[] args) {
		/**
		 * declaracion de atributos
		 */
		DataInputStream dis;
		DataOutputStream dos;
		Scanner scannerNombre = new Scanner(System.in);
		Scanner scannerOpcion = new Scanner(System.in);
		Scanner scannerEscritura = new Scanner(System.in);

		try {
			Socket socket = new Socket();  //creamos un socket
			InetSocketAddress addr = new InetSocketAddress("localhost", 5000); //establecemos los datos de conexion
			socket.connect(addr);//enviamos la peticion al servidor para conectarnos
			dis = new DataInputStream(socket.getInputStream()); // inicializamos lo que usaremos para comunicarnos
			dos = new DataOutputStream(socket.getOutputStream());
			
			String opc = "";
			String mensaje = dis.readUTF(); //recibimos el mensaje desde el hilo
			System.out.println("Servidor: " + mensaje); //mostramos por pantalla la respuesta del hilo
			String nombre = scannerNombre.nextLine(); // pedimos el nombre del cliente
			dos.writeUTF(nombre); //mandamos al hilo el nombre del cliente

			String mensaje2 = dis.readUTF(); //recibimos el mensaje del hilo
			System.out.println("Servidor: " + mensaje2); //lo mostramos
			if (mensaje2.equalsIgnoreCase("El usuario indicado no existe")) {//en caso de que el nombre del usuario no exista en el buzon, la opcion se marcará como Salir
				opc = "3";
			}

			while (!opc.equals("3")) { //mientras el usuario no pulse la opcion de salir se ejecutará esto
				opc = scannerOpcion.nextLine();//pedimos la opcion del menu
				switch (opc) {
				
				case "1":
					dos.writeUTF("1");//enviamos al hilo la opcion que ha indicado el usuario
					String msgs = dis.readUTF();//leemos el mensaje que recibimos
					if (msgs.equals("")) {
						System.out.println("No tiene mensajes");
					} else {
						System.out.println(msgs);
					}
					break;
					
				case "2":
					dos.writeUTF("2");//enviamos al hilo la opcion que ha indicado el usuario
					String mensaje3 = dis.readUTF();//recibimos el mensaje del hilo
					System.out.println(mensaje3);
					String nombreUsuario = scannerEscritura.nextLine();//pedimos el destinatario
					dos.writeUTF(nombreUsuario);//lo enviamos al hilo
					scannerEscritura = new Scanner(System.in);
					String mensaje4 = dis.readUTF();
					System.out.println(mensaje4);
					String mensajeEnviar = scannerEscritura.nextLine();
					dos.writeUTF(mensajeEnviar);
					System.out.println("Mensaje Enviado");
					break;
					
				case "3":
					dos.writeUTF("3");//enviamos al hilo la opcion que ha indicado el usuario
					break;
					
				default:
					System.out.println("Opcion no valida");
					break;
				}
			}

			socket.close();//una vez finalizada la ejecucion cerramos el socket para acabar con la conexion
			System.out.println("Desconectado");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
