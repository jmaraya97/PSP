package sisreservas;

//Esta clase se ha creado para apartar todo el codigo de las Pistas de padel para que no ensucien la clase principal

public class Pistas {
	private String codigo;
	private boolean alquilada;
	private String horario;

	//Constructor
	public Pistas(String codigo) {
		this.codigo = codigo;
		this.alquilada = false;
		this.horario = "Ninguno";
	}

	//Metodo que muestra los datos de la Pista
	public void muestraDatos() {
		System.out.println("----Pista " + this.getCodigo() + "----");
		if (this.isAlquilada()) {
			System.out.println("Alquilada");
			System.out.println(this.getHorario());
		} else {
			System.out.println("Libre");
		}
		System.out.println("----------------");
	}

	//Getters y Setters
	public String getCodigo() {
		return codigo;
	}

	public boolean isAlquilada() {
		return alquilada;
	}

	public void setAlquilada(boolean alquilada) {
		this.alquilada = alquilada;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

}
