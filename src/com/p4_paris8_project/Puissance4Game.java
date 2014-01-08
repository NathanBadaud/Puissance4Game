package com.p4_paris8_project;


import android.widget.TextView;

public class Puissance4Game {
	private int row;
    private int column;
    public int plateau[][];
    public TextView plateauView[][];
    //for calculate score;
    public final static int VIDE = 0;
    public final static int JAUNE = 1;
    public final static int ROUGE = 2;
    private Joueur[] joueurs = new Joueur[2];
    
	public Puissance4Game(int nbrow,int nbcol,Joueur player1,Joueur player2){
		setRow(nbrow);
		setColumn(nbcol);
		plateau = new int[getColumn()][getRow()];
		plateauView = new TextView[getColumn()][getRow()];
		joueurs[0] = player1;
		joueurs[1] = player2;
	}
	
    
    //initialise the game
    public void init() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getColumn(); j++) {
            	plateau[j][i] = 0;
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
        int ligne = row - 1;

        while (ligne != 0 && plateau[colonne][ligne] != 0) {
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


	public void verify(int colIndex){
		 int debut;
		 debut = debut_colonne(colIndex);
		if (joueurs[0].isMyTurn() && plateau[colIndex][debut] == 0) {
        	//create an animation method to animate set of cells
			plateauView[colIndex][debut].setBackgroundResource(R.drawable.cerclejaune);
            plateau[colIndex][debut] = 1;
            joueurs[0].setMyTurn(false);
            joueurs[1].setMyTurn(true);
            joueurs[0].score(joueurs[1].getScore()-1);
            //turn on Task
        } else if (plateau[colIndex][debut] == 0) {
        	//create an animation method to animate set of cells
        	plateauView[colIndex][debut].setBackgroundResource(R.drawable.cerclerouge);
            plateau[colIndex][debut] = -1;
            joueurs[1].setMyTurn(false);
            joueurs[0].setMyTurn(true);
            joueurs[1].score(joueurs[1].getScore()-1);
            //turn on Task

        }else{
        	//
        }
		
	}
	  public boolean LookFor4() {
		    // Vérifie les horizontales ( - )
		    for (int ligne = 0; ligne < row; ligne++) {
		      if (search4(0, ligne, 1, 0)) {
		        return true;
		      }
		    }

		    // Vérifie les verticales ( ¦ )
		    for (int col = 0; col < column; col++) {
		      if (search4(col, 0, 0, 1)) {
		        return true;
		      }
		    }

		    // Diagonales (cherche depuis la ligne du bas)
		    for (int col = 0; col < column; col++) {
		      // Première diagonale ( / )
		      if (search4(col, 0, 1, 1)) {
		        return true;
		      }
		      // Deuxième diagonale ( \ )
		      if (search4(col, 0, -1, 1)) {
		        return true;
		      }
		    }

		    // Diagonales (cherche depuis les colonnes gauches et droites)
		    for (int ligne = 0; ligne < row; ligne++) {
		      // Première diagonale ( / )
		      if (search4(0, ligne, 1, 1)) {
		        return true;
		      }
		      // Deuxième diagonale ( \ )
		      if (search4(column - 1, ligne, -1, 1)) {
		        return true;
		      }
		    }

		    // On n'a rien trouvé
		    return false;
		  }
	
	//on verifie on a 4 pions qui ont la meme couleur : return true, sinon false
	public boolean search4(int oCol, int oLigne, int dCol, int dLigne) {
	    int couleur = VIDE;
	    int compteur = 0;

	    int curCol = oCol;
	    int curRow = oLigne;

	    while ((curCol >= 0) && (curCol < column-1) && (curRow >= 0) && (curRow < row-1)) {
	      if (plateau[curRow][curCol] != couleur) {
	        // Si la couleur change, on réinitialise le compteur
	        couleur = plateau[curRow][curCol];
	        compteur = 1;
	      } else {
	        // Sinon on l'incrémente
	        compteur++;
	      }

	      // On sort lorsque le compteur atteint 4
	      if ((couleur != VIDE) && (compteur == 4)) {
	        return true;
	      }

	      // On passe à l'itération suivante
	      curCol += dCol;
	      curRow += dLigne;
	    }

	    // Aucun alignement n'a été trouvé
	    return false;
	  }
	

	
}
