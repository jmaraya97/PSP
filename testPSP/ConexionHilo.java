package testPSP;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * 
 * @author Juan Martín Ayala
 * 2º DAM
 * 
 * 
 * Clase Conexion. Esta clase es la que se va a comunicar con el cliente
 */
public class ConexionHilo extends Thread {
	//declaramos los atributos
	DataInputStream dis;
	DataOutputStream dos;
	Socket socket;
	HashMap<String, String> listaMensajes;

	//los inicializamos en el constructor
	public ConexionHilo(Socket s, DataInputStream in, DataOutputStream out, HashMap<String, String> hm) {
		this.socket = s;
		this.dis = in;
		this.dos = out;
		this.listaMensajes = hm;
	}

	//programamos el metodo run() del hilo
	@Override
	public void run() {
		boolean salir = true; //booleano para finalizarlos bucles
		try {
			System.out.println("Conexión realizada con exito");

			while (salir) {
				System.out.println("Cliente conectado");
				dos.writeUTF("Indica tu nombre: ");//envia el mensaje al cliente
				String mensaje = dis.readUTF();//lee lo que recibe del cliente
				System.out.println("Nombre: " + mensaje);//indica por pantalla quien se ha conectado
				String nombreUsuario = mensaje;

				if (!listaMensajes.containsKey(mensaje)) {//en caso de que el usuario no exista se acabara la ejecucion
					System.out.println("El usuario indicado no existe");
					dos.writeUTF("El usuario indicado no existe");
					nombreUsuario = "";
					salir = false;

				}
				mensaje = "Elige una opcion indicando el numero: \n 1.Leer mensaje \n 2.Escribir mensaje \n 3.Salir ";//menu
				dos.writeUTF(mensaje);//envia el menu al cliente
				
				
				while (salir) {//en este bucle analizamos la opcion que nos manda el cliente y hacemos lo que corresponda en cada una
					String opcionSwitch = dis.readUTF();
					switch (opcionSwitch) {
					case "1":
						System.out.println(nombreUsuario + " leyendo...");
						if (listaMensajes.containsKey(nombreUsuario)) {//busca dentro del map si el Key pasado por parametros existe
							String msg = "Mensajes no leidos: \n" + listaMensajes.get(nombreUsuario);//en caso afirmativo obtenemos sus mensajes
							if (listaMensajes.get(nombreUsuario) != null) {
								dos.writeUTF(msg);//mandamos los mensajes que tiene el cliente en el buzon
								listaMensajes.put(nombreUsuario, "");//borramos los mensajes ya que el cliente los ha leido
								dos.flush(); // vaciamos el buffer
							}
						} else {
							System.out.println("No hay nada");
							dos.writeUTF("");
						}
						break;
						
					case "2":
						dos.writeUTF("Redactando mensaje: \n  Indica el nombre del destinatario:");
						String usuarioEnviar = dis.readUTF(); //leemos el nombre del destinatario
						dos.writeUTF("Indica el mensaje que deseas enviar a "+usuarioEnviar+":");
						String mensajeEnviar = dis.readUTF();//leemos el mensaje que ha escrito
						listaMensajes.put(usuarioEnviar, listaMensajes.get(usuarioEnviar) + "\n" + mensajeEnviar);//añadimos los mensajes al Map del destinatario
						break;
						
					case "3":
						System.out.println("Cerrando conexion");	
						salir = false;
						System.out.println("Conexion cerrada");
						break;
					default:
						System.out.println("Opcion no valida");
					}
				}
				//cerramos el socket y las utilidades
				socket.close();
				dis.close();
				dos.close();
				System.out.println("Cliente " + nombreUsuario + " desconectado");
			}

		} catch (IOException e) {
			try {
				//en caso de que salte alguun error cerramos las utilidades
				socket.close();
				dis.close();
				dos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
