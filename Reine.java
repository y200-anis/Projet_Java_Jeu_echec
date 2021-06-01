package echec;
import java.util.ArrayList;
import java.util.List;
/**
@ Ait Taouit Yanis
*/
public class Reine extends Piece implements Mouvement{

	public Reine(int x, int y, Couleur c) {
		super(x, y, c);
	}
	
	public
	List<Position> getMouvementPossible() {  //renvoi les cases de déplacement du fou
		//mouvementPossible.clear();
		List<Position> mouvementPossible = new ArrayList<Position>();
		int x=position.getX();
		int y=position.getY();
		Fou ftemp = new Fou(x,y);
		Tour Ttemp = new Tour(x,y, false); //mettre le 1er tour a faux
		
		
		mouvementPossible.addAll(ftemp.getMouvementPossible());	
		mouvementPossible.addAll(Ttemp.getMouvementPossible());

		return mouvementPossible;
	}
	
	public String toString() {
		return "[reine "+super.position.getX()+","+super.position.getY()+"]" ;
	}
}
