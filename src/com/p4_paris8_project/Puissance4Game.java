package com.p4_paris8_project;


import android.widget.TextView;

public class Puissance4Game {
	private int row;
    private int column;
    private int LastRow;
    private int LastCol;
    private int first;
    //verifie si le jeu est en cours(0),egalité(NULL),gagnant(1 OU 2)
    private int game = 0;
    private int NULL = -1;
    public int plateau[][];
    public TextView plateauView[][];
    //for calculate score;
    public final static int VIDE = 0;

  //on declare les differentes possibiltés de recheche possible
    final static int [] horiz = new int[] {1,0}; //Horizontale
    final static int [] verti = new int[] {0,1}; //Verticale
    final static int [] up = new int[] {1,1}; // diagonale en mantant
    final static int [] down = new int[] {1,-1}; // diagonale en descendant
    //mettre toutes les possibiltés dans un tableau;
    final static int [][] possibilities = new int[][] { horiz  , verti  , up  , down }; // possibiltés
    
    private Joueur[] players = new Joueur[2];
    private Joueur currentPlayer = new Joueur();
    
	public Puissance4Game(int nbrow,int nbcol,Joueur player1,Joueur player2){
		setRow(nbrow);
		setColumn(nbcol);
		plateau = new int[getColumn()][getRow()];
		plateauView = new TextView[getColumn()][getRow()];
		players[0] = player1;
		players[1] = player2;
		LastRow = nbrow - 2;
		LastCol = nbcol - 2;
		first = 1;
	}
	
    
    //initialise the game
    public void init() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getColumn(); j++) {
            	plateau[j][i] = VIDE;
            }
        }

    }
    //reset the game
    public void reset() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getColumn(); j++) {
            	plateau[j][i] = 0;
            	plateauView[j][i].setBackgroundResource(R.drawable.vide);
            }
        }
    }
    // Trouve la première place vide dans la colonne
    public int debut_colonne(int colonne) {
        int ligne = LastRow;
        
        while (ligne > first && plateau[colonne][ligne-1] == VIDE) {
            ligne--;
        }

        return ligne;
    }
	

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}


	public int getLastRow() {
		return LastRow;
	}


	public void setLastRow(int lastRow) {
		LastRow = lastRow;
	}


	public int getLastCol() {
		return LastCol;
	}


	public void setLastCol(int lastCol) {
		LastCol = lastCol;
	}


	public int verify(int colIndex){
	//verify if plateau is full
	  if(!isFull()){
		int debut;
		 debut = debut_colonne(colIndex);
		if (players[0].isMyTurn() && plateau[colIndex][debut] == VIDE) {
        	//create an animation method to animate set of cells
			plateauView[colIndex][debut].setBackgroundResource(R.drawable.cerclejaune);
            plateau[colIndex][debut] = 1;
            players[0].setMyTurn(false);
            players[0].score(players[1].getScore()-1);
            currentPlayer = players[0];
            //turn on Task
        } else if (plateau[colIndex][debut] == VIDE) {
        	//create an animation method to animate set of cells
        	plateauView[colIndex][debut].setBackgroundResource(R.drawable.cerclerouge);
            plateau[colIndex][debut] = -1;
            players[0].setMyTurn(true);
            players[1].score(players[1].getScore()-1);
            currentPlayer = players[0];
            //turn on Task

        }else{
        	//
        }
	
		//on met le code du joueur courant
		if (ifWin(colIndex,debut)){
			 game = currentPlayer.getCouleur();
			 return game;
	  	//declarer le gagnant ;
	  	//bloquer le jeu
	  	//afficher score ;
	  	//Afficher rejouer ;
	  	//afficher menu consulter solde
		}
	  }else{
		  //plateau plein , partie null
		  game = NULL;
		  return game;
	  }
	  return game;
	}

	private Boolean lookFor4(int column, int line,int [] directionOfsearch){
		int columnToverify = column + directionOfsearch[0] ;//horizontale (0)
		int lineToverify = line + directionOfsearch[1] ;//vertical (0)
		int countAfter = 0; //compter combien de la meme couleur aprés
		while(plateau[columnToverify][ lineToverify] == currentPlayer.getCouleur()){
			columnToverify += directionOfsearch[0]; //horizontale (0);
			lineToverify += directionOfsearch[1]; //vertical (0);
			countAfter++;
	  }
	  //verifier de l’autre coté contraire
		 columnToverify = column - directionOfsearch[0] ;//horizontale (0);
		 lineToverify = line - directionOfsearch[1] ;//vertical (0);
		 int  countbefore = 0; //compter combien de la meme couleur avant
	  while(plateau[columnToverify][ lineToverify] == currentPlayer.getCouleur()){
	  		columnToverify -= directionOfsearch[0]; //horizontale (0);
	  		lineToverify -= directionOfsearch[1]; //vertical (0);
	  		countbefore ++;
	  }
	  //verifie si on trouvé 4 pions ou plus de la meme couleur des deux coté 
	  return countbefore+1 + countAfter >=4 ;
	}

	private boolean ifWin(int column , int line ){
		for(int i = 0 ;i<possibilities.length;i++){
			if(lookFor4(column,line,possibilities[i])){
				return true; // on a une sequence de 4 pions valide ->on a gagnant
			}
		}	
	  return false; // on a une sequence de 4 pions valide ->pas de gagnant ->jeu continue
	}
	
	
	private boolean isEmpty(int column){
		return plateau[column][LastRow] == VIDE ;
	}
	
	private boolean isFull(){
		int loopColumn = first;
		while(loopColumn<=LastCol){
			if(isEmpty(loopColumn)) return false;
			loopColumn++;
		}
		return true;
	}
}
