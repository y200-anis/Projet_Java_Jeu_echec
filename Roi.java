package echec;
/**
 *@author David Sidoun
 */
import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece implements Mouvement{
	public static List <Roi> tabRoi=new ArrayList<Roi>();
	boolean echec=false;
	boolean echecEtMat=false;
	boolean echecEtPat=false;
	boolean premierTour=true;

	public Roi(int x, int y, Couleur c) {
		super(x, y, c);
		tabRoi.add(this);
	}


	public boolean isEchec() {
		return echec;
	}


	public boolean isEchecEtMat() {
		return echecEtMat;
	}

	public List<Position> getMouvementPossible() {
		int x=position.getX();
		int y=position.getY();
		List<Position> mouvementPossible = new ArrayList<Position>();


		for (int c=-1;c<=1;c++)	//colonne
		{
			for(int l=-1;l<=1;l++)	//ligne
			{
				Position temp=new Position(x+l,y+c);
				if((l!=0 || c!=0 )&& temp.inBounds() && !this.bloqueAmi(temp))	//ne pas mettre la pos actuelle dans la matrice
				{

					mouvementPossible.add(temp.clone());			//obtient carrer autour du roi

				}
			}
		}

		mouvementPossible=MouvementAutorises(mouvementPossible); //car roi ne peut pas se sacrifier

		return mouvementPossible;
	}


	public List<Position> MouvementAutorises(List<Position> mouvement){
		List <Position> posPrises;
		List <Position> posEnlevees=new ArrayList <Position>();
		if (couleur.equals(Couleur.WHITE) )
			posPrises=posPrisesNoir;
		else
			posPrises=posPrisesBlanc;

		for (Position pos:mouvement) {
			for (Position pos2:posPrises) {
				if (pos.equals(pos2)) {
					posEnlevees.add(pos);
					break;
				}

			}
		}
		mouvement.removeAll(posEnlevees);

		return mouvement;
	}

	String update()
	{
		String result="";
		List<Position> tempTab;
		if (couleur.equals(Couleur.BLACK))
			tempTab=posPrisesBlanc;
		else
			tempTab=posPrisesNoir;
		if (echec)			// reset de la variable
			echec=false;



		for (Position pos:tempTab) {	// verifie si une piece peut manger le roi
			if(pos.equals(position))
			{
				echec=true;
				result="echec";
			}

		}

		if(!echec && this.mouvementExecutable.isEmpty() &&
				Piece.getColoredPiece(couleur).size()==1)
		{

			echecEtPat=true;
			result="echec et pat";
		}

		if(echec && this.mouvementExecutable.isEmpty())
		{
			result="echec et mat";
			echecEtMat=true;
		}
		return result;
	}

	static Roi getRoiCouleur(Couleur c)
	{
		for (Roi r:Roi.tabRoi) {
			if (r.couleur.equals(c))
				return r;
		}
		return null;
	}

	public String toString() {
		return "[Roi "+super.position.getX()+","+super.position.getY()+"]" ;
	}
}
