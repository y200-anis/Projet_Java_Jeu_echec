package echec;
import java.util.List;
import java.util.ArrayList;
/**
*@author Yanis Ait Taouit 
*/

public class Joueur{
	private String nom;
	private Couleur couleur;
	 List<Piece> tab=new ArrayList<Piece>();
	 
	private Couleur couleurEnnemie;
	
	public Joueur (String n, Couleur c)		
	{
		nom=n;
		couleur=c;
	}
	
	public Couleur getCouleur()
	{
		return couleur;
	}
	
	public void updateTab()
	{
		if (couleur.equals(Couleur.BLACK))
		{
			tab=Piece.getBlackPiece();
			
			couleurEnnemie=Couleur.WHITE;
		}
		 else
		 {
			 tab=Piece.getWhitePiece();		 // definition des pieces du joueur
			 couleurEnnemie=Couleur.BLACK;
			 
		 }
		
	}
	
	public String getNom() {
		return nom;
	}

	public void abandonner(){
		
	}

	public Piece bougerT1(Position depart) {					
		Piece selectionnee=null;
		boolean ok=false;
		for (Piece count: tab)
		{
			 if (depart.equals(count.getPosition()))
			 {
				 selectionnee=count;
				 ok=true;
				 System.out.println(count.getMouvementPossible());
				 break;
			 }
			 // si position fausse affiche erreur et arrete le code
			 
			 
		}
		if (!ok)
			System.out.println("position erronnée, rejouer");
		return selectionnee;
			
	}
	public Piece bougerRoiT1(Position depart) {					
		Piece selectionnee=null;
		boolean ok=false;
		if (depart.equals(Roi.getRoiCouleur(couleur).getPosition()))
		{
			selectionnee=Roi.getRoiCouleur(couleur);
			ok=true;
			System.out.println(selectionnee.getMouvementPossible());
				 
		}
			 // si position fausse affiche erreur et arrete le code
		
		if (!ok)
			System.out.println("position erronnée, rejouer");
		return selectionnee;
			
	}
	
	public boolean bougerT2(Piece selectionnee, Position depart,Position arrivee) {

		boolean tentative=selectionnee.bouger(arrivee); 						//deplace piece et renvoi vrai si la position utilisable
		if (tentative)															
		{			
			for (Piece count: Piece.getColoredPiece(couleurEnnemie)  )//verifier presence des piece ennemie sur nouvelle position (manger)
			{
				if(count.getPosition().equals(arrivee))
				{
					count.destroy();											//detruire la piece ennemie
					break;		
				}
			}
		}
		else
			System.out.println("erreur d'arrivée");
		return tentative;			
	}

	public String toString() {
		return "Joueur [nom=" + nom + ", couleur=" + couleur + "]";
	}
}
