package echec;
import java.util.*;
/**
* @author David Sidoun
*/
public class Fou extends Piece implements Mouvement{

	public Fou(int x, int y, Couleur c) {
		super(x, y, c);
	}
	public Fou(int x, int y,Couleur c,boolean b) {
		super(x, y,c,b);
	}

	public List<Position> getMouvementPossible() {  //renvoi les cases de dplacement du fou
		//mouvementPossible.clear();
		int x=position.getX();
		int y=position.getY();
		Position temp=new Position(x,y);
		List<Position> mouvementPossible = new ArrayList<Position>();

		/*8 cases max par diagonale
		optimisation possible  ce niveau si out of bound
		valable pour les 2 couleurs*/

			for(int c=1;c<8;c++)				//diagonale haut droite
			{
				temp.setX(x+c);
				temp.setY(y+c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	//si un alli dans la ligne s'arrete
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		//si la case a un ennemi les suivantes pas possibles
						break;
				}
				else
					break;					// si ami bloque case prend fin

			}
			for(int c=1;c<8;c++)				//diagonale haut gauche
			{
				temp.setX(x-c);
				temp.setY(y+c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	//si un alli dans la ligne s'arrete
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		//si la case a un ennemi les suivantes pas possibles
						break;
				}
				else
					break;					// si ami bloque case prend fin
			}
			for(int c=1;c<8;c++)				//diagonale bas gauche
			{
				temp.setX(x-c);
				temp.setY(y-c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	//si un alli dans la ligne s'arrete
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		//si la case a un ennemi les suivantes pas possibles
						break;
				}
				else
					break;					// si ami bloque case prend fin
			}
			for(int c=1;c<8;c++)				//diagonale bas droite
			{
				temp.setX(x+c);
				temp.setY(y-c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	//si un alli dans la ligne s'arrete
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		//si la case a un ennemi les suivantes pas possibles
						break;
				}
				else
					break;					// si ami bloque case prend fin
			}
		// rajouter regle 1er mouv
		return mouvementPossible;
	}

	public String toString() {
		return "[Fou "+super.position.getX()+","+super.position.getY()+"]" ;
	}
}
