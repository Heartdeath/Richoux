/**
 * Classe Producteur
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */

class Producteur extends Thread {

	private AbstractFileBloquanteBornee<Integer> buffer;
	private int val = 0;

	public Producteur(AbstractFileBloquanteBornee<Integer> tampon) {
		this.buffer = tampon;
	}

	public void run() {
		while (true) {
			System.out.println("je depose " + val);
			try {
				buffer.deposer(val++);
				Thread.sleep((int) (Math.random() * 100)); // attend jusqu'a 100 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * Classe Consommateur
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */
class Consommateur extends Thread {

	private AbstractFileBloquanteBornee<Integer> buffer;

	/**
	 * Constructeur
	 * @param buffer nom de la file
	 */
	public Consommateur(AbstractFileBloquanteBornee<Integer> buffer) {
		this.buffer = buffer;
	}

	/**
     * Méthode run qui définit ce que le thread doit éxécuter
     * 
     */
	public void run() {
		while (true) {
			try {
				System.out.println("je preleve " + buffer.prendre());
				Thread.sleep((int) (Math.random() * 200)); // attends jusqu'a 200 ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * Classe TestBufferBasNiveau
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */
class TestBufferBasNiveau {

	public static void main(String args[]) {

		AbstractFileBloquanteBornee<Integer> buffer = new apiBasNiveau<>(5);
		Producteur prod = new Producteur(buffer);
		Consommateur cons = new Consommateur(buffer);

		prod.start();
		cons.start();
	}

}

/**
 * Classe TestBufferHautNiveau
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */
class TestBufferHautNiveau {

	public static void main(String args[]) {

		AbstractFileBloquanteBornee<Integer> buffer = new apiHautNiveau<>(5);
		Producteur prod = new Producteur(buffer);
		Consommateur cons = new Consommateur(buffer);

		prod.start();
		cons.start();
	}

}