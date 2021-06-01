package echec;
import java.util.*;
/**
@author Yanis Ait Taouit
*/
public class Cavalier  extends Piece implements Mouvement {

	  public Cavalier(int x, int y, Couleur c) {
		super(x, y, c);
	  }
  
		public List<Position> getMouvementPossible() {
		
		int x=position.getX();
		int y=position.getY();
		Position temp=new Position(x,y);
		List<Position> mouvementPossible = new ArrayList<Position>();
		
		for(int px=-2;px<=2;px++)
		{
			for(int py=-2;py<=2;py++)
			{
				
				if(Math.abs(px)+Math.abs(py)==3)
				{
					temp.setPosition(x+px, y+py);
					if(temp.inBounds()&& !this.bloqueAmi(temp)) // pas d'ami sur les cases
					{
						mouvementPossible.add(temp.clone());
					}
				}
			}
		}
		return mouvementPossible;
	}
  
	public String toString() {
		return "[cheval "+super.position.getX()+","+super.position.getY()+"]" ;
	}

}
