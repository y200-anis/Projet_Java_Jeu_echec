package echec;
/**
 * @author Yanis Ait Taouit
 */
public class Case {
	private Position position;
	private Piece piece;
	private String affichage;
	
	
	public Case(Position position) {
		this.position = position;
	}
	
	public void positionne(Piece p)
	{
		this.piece=p;
	}
	
	
	public Position getPosition() {
		return position;
	}
	public void vider() {
		piece=null;
	}
	 public Piece getPiece()
	 {
		 return piece;
	 }

	
	public String toString() {
		return ("Case"+ position + piece);
	}
	 
	 
}
