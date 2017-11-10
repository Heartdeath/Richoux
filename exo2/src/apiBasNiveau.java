/**
 * Classe BufferCirculaireBasNiveau
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */
public class apiBasNiveau<T> extends AbstractFileBloquanteBornee<T> {

	/**
	 * Constructeur 
	 * @param n nombre d'objets accept�s
	 */
	public apiBasNiveau(int n) throws IllegalArgumentException {
		super(n);
	}

	/**
	 * M�thode d�poser qui permet d'ajouter un objet dans la file. 
	 * @param T objet � d�poser
	 */
	public synchronized void deposer(T obj) {
		int taille = tableau.length;

		while (nombre == taille) { // si plein
			try {
				wait(); // attends non-plein
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		tableau[tete] = obj;
		nombre++;
		tete = (tete + 1) % taille;
		notify(); // envoie un signal non-vide
	}

	/**
	 * M�thode d�poser qui permet de prendre un objet dans la file. 
	 * @param T objet � prendre
	 */
	public synchronized T prendre() {
		int taille = tableau.length;
		while (nombre == 0) { // si vide
			try {
				wait(); // attends non-vide
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T obj = tableau[queue];
		tableau[queue] = null; // supprime la ref a l'objet
		nombre--;
		queue = (queue + 1) % taille;
		notify(); // envoie un signal non-plein
		return obj;
	}

}