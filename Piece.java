package echec;
import java.util.*;
/**
 * @author David Sidoun
 */

public abstract class Piece implements Mouvement {
	protected Couleur couleur;
	protected Position position;
	private static List<Piece> toDelete=new ArrayList<Piece>();
	public static List<Position> posPrisesBlanc = new ArrayList<Position>(); //utiliser pour le roi mouvement des pieces blanches
	public static List<Position> posPrisesNoir = new ArrayList<Position>(); //utiliser pour le roi
	public static List<Piece> tabPiece = new ArrayList<Piece>();				//tableau de toutes les pieces du plateau
	protected List<Position> mouvementExecutable =new ArrayList<Position>();

	public Piece(int x,int y,Couleur c)
	{
		position = new Position(x,y);
		couleur =c ;
		tabPiece.add(this);
	}
	public Piece(int x,int y,Couleur c,boolean b)	//pour initialisation des reines
	{
		position = new Position(x,y);
		couleur =c ;

	}

	public static List<Piece> getColoredPiece(Couleur c)
	{
		List<Piece> temp;
		if (c.equals(Couleur.BLACK))
			temp=getBlackPiece();
		else
			temp=getWhitePiece();
		return temp;
	}


	public static List<Piece> getWhitePiece()
	{
		List<Piece> tempTab = new ArrayList<Piece>();
		for(Piece temp:tabPiece)
		{
			if(temp.couleur.equals(Couleur.WHITE))
				tempTab.add(temp);
		}
		return tempTab;
	}
	public static List<Piece> getBlackPiece()
	{
		List<Piece> tempTab = new ArrayList<Piece>();
		for(Piece temp:tabPiece)
		{
			if(temp.couleur.equals(Couleur.BLACK))
				tempTab.add(temp);
		}
		return tempTab;
	}

	public Position getPosition() {
		return position;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	protected boolean bloqueAmi(Position mouvement) //bloquer par piece ami
	{
		List<Piece> tempTab=new ArrayList<Piece>();
		List <Position> posPrises= new ArrayList<Position>();
		if (this.couleur==Couleur.WHITE) {
			tempTab= Piece.getWhitePiece();
			posPrises=posPrisesBlanc;

		}
		else if(this.couleur==Couleur.BLACK) {
			tempTab=Piece.getBlackPiece();
			posPrises=posPrisesNoir;

		}

		for (Piece count:tempTab)
		{
			if (count.position.equals(mouvement)) {
				posPrises.add(count.position);
				return true;
			}

		}
		return false;
	}

	protected boolean bloqueEnnemi(Position mouvement) //bloquer par piece ennemie
	{
		List<Piece> tempTab=new ArrayList<Piece>();

		if (this.couleur==Couleur.BLACK)
			tempTab= Piece.getWhitePiece();
		else if(this.couleur==Couleur.WHITE)
			tempTab=Piece.getBlackPiece();

		for (Piece count:tempTab)
		{
			if (count.position.equals(mouvement))
				return true;
		}

		return false;
	}

	public static void updateAll() // a chaque fois qu'un coup est joueur, a lancer
	{
		for (Piece count: tabPiece)
		{
			count.mouvementExecutable=count.getMouvementPossible();
			if(count.couleur.equals(Couleur.WHITE))
				posPrisesBlanc.addAll(count.mouvementExecutable);
			else
				posPrisesNoir.addAll(count.mouvementExecutable);

		}

		if(!toDelete.isEmpty())
		{
			for (Piece p:toDelete)
				tabPiece.remove(p);
		}

	}

	public boolean bouger(Position pos)		//determine si la piece peut se deplacer ??? la position d'entree
	{
		boolean ok=false;

		for (Position count: this.mouvementExecutable)
		{

			if(pos.equals(count))	//position de destination est compris dans les mouvements possibles
			{

				position=count;
				ok=true;
				break;
			}
		}
		return ok;
	}

	public static List<Piece> getToDelete() {
		return toDelete;
	}
	public static List<Position> getPosPrisesBlanc() {
		return posPrisesBlanc;
	}
	public static List<Position> getPosPrisesNoir() {
		return posPrisesNoir;
	}
	public static List<Piece> getTabPiece() {
		return tabPiece;
	}
	public List<Position> getMouvementExecutable() {
		return mouvementExecutable;
	}
	public void destroy()
	{
		//tabPiece.remove((Piece)this);
		toDelete.add(this);

	}

	public String toString() {
		return "Piece [couleur=" + couleur + ", position=" + position + "]";
	}

}
