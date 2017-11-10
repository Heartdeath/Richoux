

import java.util.ArrayList;

/**
 * Une file de communication bornee bloquante.
 * 
 * Les threads peuvent d�poser (resp. prendre) des objets dans une telle file.
 * Cette op�rations peut �tre bloquante si la file est pleine (resp. vide).
 */

/**
 * Classe AbstractFileBloquanteBornee
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */
public abstract class AbstractFileBloquanteBornee<E>
{

    E[] tableau;
    int tete;
    int queue;
    boolean estVide;
    boolean estPleine;
    int nombre;
/**
* Cr�er une file de capacit� maximale n.
* 
* param n - la capacit� maximale de la file. n devrait �tre sup�rieur ou
* �gal � 1.
*/
    @SuppressWarnings({"unchecked"})
    public AbstractFileBloquanteBornee (int n) throws IllegalArgumentException 
    {
        if(n<1) 
            throw new 
                IllegalArgumentException(
                        "AbstractFileBloquanteBornee : la capacité de la file doit être > 0");
        else {
            /* Création d'un tableau pseudo-générique.
             * Fonctionne tant que le tableau reste "interne" à la classe.
             * Cf. http://stackoverflow.com/questions/529085/how-to-generic-array-creation
             */
            tableau = (E[]) new Object[n];
            tete = queue = 0;
            estVide = true;
            estPleine = false;
        }
    }

/**
* D�poser une r�f�rence dans la file.
* 
* Le d�p�t est fait en fin de file. L'objet r�f�renc� n'est pas copi� au
* moment du d�p�t. Le d�p�t est bloquant lorsque la file est pleine
* 
* param e - l'�l�ment � ajouter � la file
*/
     abstract public void deposer (E e) throws InterruptedException;

/**
* Prendre une r�f�rence dans la file.
* 
* La prise est faite en t�te de file. L'objet r�f�renc� n'est pas copi� au
* moment du d�p�t. La prise est bloquante lorsque la file est vide.
* 
* returns la r�f�rence de t�te de la file
*/
    abstract public E prendre () throws InterruptedException;

}
