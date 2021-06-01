package echec;
import java.util.ArrayList;
import java.util.List;
/**
*@author David Sidoun
*/
public class Tour extends Piece implements Mouvement{

	boolean _1erTour;
	
	public Tour(int x, int y, Couleur c) {
		super(x, y, c);
		_1erTour=true;
		
	}
	public Tour(int x, int y, Couleur c,boolean b) {
		super(x, y, c);

		_1erTour=b;
		
	}
	public Tour(int x, int y, boolean b) {
		super(x, y);
		_1erTour=b;
		
	}

	public List<Position> getMouvementPossible() {  // sera lancé a chaque fois que le pion est bouger

		List<Position> mouvementPossible = new ArrayList<Position>(); 
		
		int x=position.getX();
		int y=position.getY();
		Position temp=new Position(x,y);
		
		
		//max de 8 case par ligne
		//valable pour les deux couleurs
		
		for(int c=1;c<8;c++)				//ligne a droite
		{
			temp.setX(x+c);
			temp.setY(y);
			if (temp.inBounds() && !this.bloqueAmi(temp)) {
				mouvementPossible.add(temp.clone());
				if (this.bloqueEnnemi(temp))	//si un ennemie sur la case 
					break;						// les suivantes innaccessibles
			}
			else
				break;
		}

		for(int c=1;c<8;c++)				//ligne a gauche
		{
			temp.setX(x-c);
			temp.setY(y);
			if (temp.inBounds() && !this.bloqueAmi(temp)) {
				mouvementPossible.add(temp.clone());
				if (this.bloqueEnnemi(temp))	//si un ennemie sur la case 
					break;						// les suivantes innaccessibles
			}
			else
				break;
		}

		for(int c=1;c<8;c++)				//ligne au dessus
		{
			temp.setX(x);
			temp.setY(y+c);
			if (temp.inBounds() && !this.bloqueAmi(temp)) {
				mouvementPossible.add(temp.clone());
				if (this.bloqueEnnemi(temp))	//si un ennemie sur la case 
					break;						// les suivantes innaccessibles
			}
			else
				break;
		}

		for(int c=1;c<8;c++)				//ligne en dessous
		{
			temp.setX(x);
			temp.setY(y-c);
			if (temp.inBounds() && !this.bloqueAmi(temp)) {
				mouvementPossible.add(temp.clone());
				if (this.bloqueEnnemi(temp))	//si un ennemie sur la case 
					break;						// les suivantes innaccessibles
			}
			else
				break;
		}
		return mouvementPossible;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return "[tour "+super.position.getX()+","+super.position.getY()+"]" ;
	}

}
