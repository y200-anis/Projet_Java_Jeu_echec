package echec;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *@author Ait Taouit Yanis 
 */
public class Partie {
	private Joueur joueurActuel;
	private int numTour=1;
	ArrayList <Joueur> listeJoueurs = new ArrayList <Joueur> ();
	Plateau plateau;
	private Scanner entree = new Scanner(System.in);

	boolean fini;

	public Partie(String n1,String n2)
	{
		Joueur j1=new Joueur (n1,Couleur.WHITE);
		Joueur j2=new Joueur (n2,Couleur.BLACK);

		listeJoueurs.add(j1);
		listeJoueurs.add(j2);
		initialisation();

		j1.updateTab();
		j2.updateTab();
		fini=false;
		Piece.updateAll();
	}


	private void initialisation()
	{

		String w="";
		do{
			System.out.println("Lancer en mode\nnormal | echec et mat | ");
			w = entree.nextLine();
		}
		while(!w.equals("normal")&&!w.equals("echec et mat"));

		if (w.equals("normal"))
		{
			init_pion();
			init_fou();
			init_tour();
			init_cavalier();
			init_royaux();
		}
		else if ( w.equals("echec et mat"))
			echecMat_init();

		Plateau p = new Plateau();
		plateau=p;
		plateau.initialisation();
	}


	public Joueur getJoueurActuel() {
		return joueurActuel;
	}


	public int getNumTour() {
		return numTour;
	}


	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}


	public Plateau getPlateau() {
		return plateau;
	}


	public Scanner getEntree() {
		return entree;
	}


	public boolean isFini() {
		return fini;
	}


	public void perso_init()
	{
		String x="";
		do{

			x = entree.next();
		}while (x=="");


	}

	private void echecMat_init()
	{
		Tour t=new Tour(8,3,Couleur.BLACK);
		Tour t3=new Tour(8,2,Couleur.BLACK);
		Roi r=new Roi(1,2,Couleur.WHITE);
		Roi r2=new Roi(2,5,Couleur.BLACK);
	}


	private void init_pion()
	{
		int x=1,y=2;
		for (x=1;x<=8; x++ )
		{
			Pion p=new Pion(x,y,Couleur.WHITE);
		}
		y=7;
		for (x=1;x<=8; x++ )
		{
			Pion p=new Pion(x,y,Couleur.BLACK);
		}
	}

	private void init_tour() {
		Tour t1=new Tour(1,8,Couleur.BLACK);
		Tour t2=new Tour(8,8,Couleur.BLACK);
		Tour t3=new Tour(1,1,Couleur.WHITE);
		Tour t4=new Tour(8,1,Couleur.WHITE);

	}
	private void init_cavalier() {
		Cavalier t1=new Cavalier(2,8,Couleur.BLACK);
		Cavalier t2=new Cavalier(7,8,Couleur.BLACK);
		Cavalier t3=new Cavalier(2,1,Couleur.WHITE);
		Cavalier t4=new Cavalier(7,1,Couleur.WHITE);

	}
	private void init_fou() {
		Fou t1=new Fou(3,8,Couleur.BLACK);
		Fou t2=new Fou(6,8,Couleur.BLACK);
		Fou t3=new Fou(3,1,Couleur.WHITE);
		Fou t4=new Fou(6,1,Couleur.WHITE);

	}
	private void init_royaux() {

		Reine r4=new Reine (5,1,Couleur.WHITE);
		Reine r2=new Reine (5,8,Couleur.BLACK);
		Roi r1= new Roi(4,8,Couleur.BLACK);
		Roi r3= new Roi(4,1,Couleur.WHITE);
	}

	public void lancer() {
		while(!fini)
			debutTour();
	}

	public void finDuTour() {

		numTour++;
	}

	public void finDePartie() {
		System.out.println("Fini!");

		System.out.println("Voulez vous rejouez une partie ? (oui ou non)");
		String x=".";
		do{
			x = entree.nextLine();
		}
		while(x.equals(".") || !(x.equals("oui") || x.equals("non")));
		if (x.equals("oui"))
		{
			String y=".";
			do{
				System.out.println("Voulez vous changer de couleur? (oui ou non)");
				y = entree.nextLine();
			}
			while(y.equals(".") || !(y.equals("oui") || y.equals("non")));

			if(y.equals("oui"))
				recommencerPartie(2);
			else if (y.equals("non"));
				recommencerPartie(1);

		}
	}

	public void recommencerPartie(int n) {

		if (n==1)		//pas de changement de couleur
		{

			Piece.tabPiece.clear();
			Roi.tabRoi.clear();
			initialisation();fini=false;Piece.updateAll();
		}
		else if(n==2)	//changement de couleur des joueurs
		{
			Piece.tabPiece.clear();
			Roi.tabRoi.clear();
			Joueur j=this.listeJoueurs.get(0);
			listeJoueurs.set(0, listeJoueurs.get(1));
			listeJoueurs.set(1,j);
			initialisation();fini=false;Piece.updateAll();

		}

	}

	public void debutTour() { 	//lance le tour d'un des 2 joueurs
		this.update();
		if (!fini)
		{

			if (numTour%2==1)
				joueurActuel=this.listeJoueurs.get(0);
			else
				joueurActuel=this.listeJoueurs.get(1);


			System.out.println("C'est au "+joueurActuel.getNom()+" de jouer");
			jouer(joueurActuel);
		}
		else
		{
			this.finDePartie();
		}

	}

	private void jouer(Joueur j)
	{

		Position depart=null;
		Position arrivee=null;
		Piece selectionnee=null;
		Couleur jc=this.joueurActuel.getCouleur();

		//Roi.getRoiCouleur(jc).isEchec())

		boolean temp2=false;
		while (!temp2)										//si erreur sur pos arriver tout recommencer
		{
			boolean temp=false;
			while (!temp)									//tant que la pos de depart est fausse recommencer
			{
				if (!Roi.getRoiCouleur(jc).isEchec())
				{
					System.out.println(j.tab);
					depart=getEntree(1);						//recupere du user la pos de depart
					selectionnee=joueurActuel.bougerT1(depart);	//une piece allie est à cette position
				}
				else
				{
					System.out.println(Roi.getRoiCouleur(jc));
					depart=getEntree(1);
					selectionnee=joueurActuel.bougerRoiT1(depart);

				}
				if (selectionnee!=null)
					temp=true;
			}

			temp=false;
			arrivee=getEntree(2);							//recuperer pos arrivée
			temp2=joueurActuel.bougerT2(selectionnee,depart,arrivee);	//deplace pion et mange pion ennemie si present

		}
		Case caseTemp=plateau.getCase(depart.getX(), depart.getY());
		caseTemp.vider();
		System.out.println(caseTemp.getPosition());

		finDuTour();

	}

	private Position getEntree(int n)
	{
		String temp=null;
		if(n==1)
			temp="Entrez la position de depart (x puis y)";
		if(n==2)
			temp="Entrez la position d'arriver (x puis y)";


		System.out.println(temp);
		int x=0, y=0;
		do{
			x = entree.nextInt();
			y = entree.nextInt();
		}
		while(x==0 && y==0);

		System.out.println(x+"  "+y);
		Position pos=new Position(x,y);
		return pos;
	}

	private void update()
	{

		this.listeJoueurs.get(0).updateTab();
		this.listeJoueurs.get(1).updateTab();

		Piece.updateAll();
		plateau.update();

		for (Roi count:Roi.tabRoi)
		{
			String t;
			t=count.update();



			Couleur coul=count.getCouleur();
			if(count.echec && !count.echecEtMat)
				System.out.println("Le roi "+count.getCouleur()+ " est en echec");
			else if (count.echec && count.echecEtMat || count.echecEtPat)
			{
				fini=true;
				System.out.println("Le roi "+count.couleur+" est en "+t+"\n");
				if (coul.equals(Couleur.WHITE))
					System.out.print("Le joueur "+this.listeJoueurs.get(0));
				else
					System.out.print("Le joueur "+this.listeJoueurs.get(1));
				System.out.println(" a perdu");

			}
		}
	}
}
