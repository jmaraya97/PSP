package sisreservas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Principal {
	
	public static void main(String[] args) throws IOException {		
		ProcessBuilder builder = new ProcessBuilder(args); //Pasamos por argumentos el comando java -jar C:/Users/jmara/Documents/secundaria.jar que contiene la clase Secundaria y Pistas 
		builder.redirectErrorStream(true);
		Process process = builder.start();
		InputStream outHijo = process.getInputStream();//salida del proceso hijo en la entrada del padre
		OutputStream inHijo = process.getOutputStream(); //entrada del proceso hijo en la salida del padre
 
		byte[] buffer = new byte[4000];//buffer
		
		while (isAlive(process)) {
			int numOut = outHijo.available();
			if (numOut > 0) {
				int n = outHijo.read(buffer, 0, Math.min(numOut, buffer.length));
				System.out.println(new String(buffer, 0, n));
			}

			int numIn = System.in.available();
			if (numIn > 0) {
				int n = System.in.read(buffer, 0, Math.min(numIn, buffer.length));
				inHijo.write(buffer, 0, n);
				inHijo.flush();
			}
			
			try {
				//Retardo de tiempo de 10 milisegundos, suficiente para que no se pisen los procesos.
				Thread.sleep(10);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

		System.out.println(process.exitValue());
	}
	
	//Metodo que comprueba si el proceso hijo ha terminado. Devuelve falso en caso afirmativo
	//y en caso negativo devuelve true y lanza una excepcion.
	public static boolean isAlive(Process process) {
		try {
			process.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

}
