package sisreservas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Principal {
	
	public static void main(String[] args) throws IOException {		
		ProcessBuilder builder = new ProcessBuilder(args); //Pasamos por argumentos el comando java -jar C:/Users/jmara/Documents/secundaria.jar que contiene la clase Secundaria y Pistas 
		builder.redirectErrorStream(true);
		Process process = builder.start();
		InputStream out = process.getInputStream();//salida del proceso hijo en la entrada del padre
		OutputStream in = process.getOutputStream(); //entrada del proceso hijo en la salida del padre
 
		byte[] buffer = new byte[4000];//buffer
		
		while (isAlive(process)) {
			int no = out.available();
			if (no > 0) {
				int n = out.read(buffer, 0, Math.min(no, buffer.length));
				System.out.println(new String(buffer, 0, n));
			}

			int ni = System.in.available();
			if (ni > 0) {
				int n = System.in.read(buffer, 0, Math.min(ni, buffer.length));
				in.write(buffer, 0, n);
				in.flush();
			}
			
			try {
				//Retardo de tiempo de 10 milisegundos, suficiente para que no se pisen los procesos.
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

		System.out.println(process.exitValue());
	}
	
	//Metodo que comprueba si el proceso hijo ha terminado. Devuelve falso en caso afirmativo
	//y en caso negativo devuelve true y lanza una excepcion.
	public static boolean isAlive(Process p) {
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

}
