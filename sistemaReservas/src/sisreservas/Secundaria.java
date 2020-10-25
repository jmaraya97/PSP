package sisreservas;

//imports
import java.util.ArrayList;
import java.util.Scanner;


//Clase secundaria/hijo que tiene todo el codigo relevante al proceso de alquiler de las pistas

public class Secundaria {

	public static void main(String[] args) {
		ArrayList<Pistas> lista = new ArrayList<>();
		lista.add(new Pistas("Uno"));
		lista.add(new Pistas("Dos"));

		Scanner sc = new Scanner(System.in);
		String opc = "";
		while (!opc.equalsIgnoreCase("Salir")) {
			muestraMenu();
			opc = sc.next();
			switch (opc.toLowerCase()) {

			case "alquilar":
				alquilar(lista);
				break;

			case "consulta":
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).muestraDatos();
				}
				break;

			case "salir":
				System.out.println("Saliendo...");
				break;

			default:
				System.out.println("No entiendo el mensaje");
				break;

			}
		}
	}

	//Metodo que muestra el menu por consola
	public static void muestraMenu() {
		System.out.println("****************************");
		System.out.println("*Aplicación de reserva de pistas de padel.");
		System.out.println("**********Opciones**********");
		System.out.println("*Alquilar");
		System.out.println("*Consulta");
		System.out.println("*Salir");
		System.out.println("****************************");
	}

	//Metodo usado para la comprobación y alquiler de la pista
	public static void alquilar(ArrayList<Pistas> lista) {
		System.out.println("Introduzca la pista Uno o Dos");
		Scanner sc1 = new Scanner(System.in);
		String pista = sc1.next();
		String horario = "";
		boolean enc = false;

		for (int i = 0; i < lista.size() && !enc; i++) {
			if (lista.get(i).getCodigo().equalsIgnoreCase(pista)) {
				enc = true;
				if (enc && !lista.get(i).isAlquilada()) {
					System.out.println("¿Querria alquilar noche o tarde?");
					Scanner sc2 = new Scanner(System.in);
					horario = sc2.next();
					if (horario.equalsIgnoreCase("noche") || horario.equalsIgnoreCase("tarde")) {
						lista.get(i).setAlquilada(true);
						lista.get(i).setHorario(horario);
						System.out.println("Pista " + pista + " alquilada por la " + horario);
					} else {
						System.out.println("El horario indicado no es valido");
					}

				} else {
					System.out.println("La pista está alquilada");
				}
			}
		}
		if (!enc) {
			System.out.println("La pista indicada no existe");
		}

	}

}
