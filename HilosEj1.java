public class test {

	public static void main(String[] args) throws InterruptedException {
		hilo1 h1 = new hilo1(10);
		
		h1.start();
			

	}

}


public class hilo1 extends Thread{

	public int tamano, num1 = 1, num2 = 1, suma = 1;
	
	
	public hilo1(int tamano) {
		this.tamano = tamano;
	}

	
	@Override
	public void run() {
		for (int i = 1; i < tamano; i++) {
            System.out.println(suma);
            suma = num1 + num2;
            num1 = num2;
            num2 = suma;         
        }
	}
}
