/**
 * Classe Baguette
 * 
 * @author Beillevaire Thibault & Biermann Pierre
 */	
class Baguette
{
  private boolean _prise = false;

  final synchronized void prendre() 
  {
    try 
    {
      while( _prise ) 
      {
	wait();
      }
    } 
    catch( InterruptedException e ) 
    {
      e.printStackTrace();
      System.exit( -1 );
    }
    _prise = true;
  }
  public boolean estPrise() {
		return _prise;
	}
  final synchronized void relacher() 
  {
    _prise = false;
    notifyAll();
  }
}
/**
 * Classe Philosophe
 * 
 * @return un philosophe
 * @author Beillevaire Thibault & Biermann Pierre
 */		
public class Philosophe implements Runnable
{
  private String _nom;
  private Baguette _bGauche, _bDroite;
  /**
   * Constructeur de la classe philosophe
   * 
   * @param n nom
   * @param g Baguette gauche
   * @param d Baguette droite
   */
  public Philosophe( String n, Baguette g, Baguette d )
  {
    _nom = n;
    _bGauche = g;
    _bDroite = d;
  }
/**
* Méthode run qui définit ce que le thread doit éxécuter
* 
*/
  public void run() {
		while (true) {
			penser();
			if (!_bGauche.estPrise() && !_bDroite.estPrise()) {
				_bGauche.prendre();
				_bDroite.prendre();

				manger();
				_bDroite.relacher();
				_bGauche.relacher();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
/**
 * Méthode manger qui fait manger un philosophe
 * 
 */
  final void manger() 
  {
    System.out.println( _nom + " mange." );
  }
/**
 * Méthode penser qui fait penser le philosophe
 * 
 */
  final void penser() 
  {
    System.out.println( _nom + " pense." );
  }

  public static void main( String args[] )
  {
    final String[] noms = { "Platon", "Socrate", "Aristote", "Diogène", "Sénèque" };
    final Baguette[] baguettes = { new Baguette(), new Baguette(), new Baguette(), new Baguette(), new Baguette() }; 
    Philosophe[] table;

    table = new Philosophe[ 5 ];
    for( char cpt = 0 ; cpt < table.length ; ++cpt )
    {
      table[ cpt ] = new Philosophe( noms[ cpt ], baguettes[ cpt ], baguettes[ ( cpt + 1 ) % table.length ] );
      new Thread( table[ cpt ] ).start();
    }
  }
}

