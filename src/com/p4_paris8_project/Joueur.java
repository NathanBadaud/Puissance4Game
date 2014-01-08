package com.p4_paris8_project;

public class Joueur {
	  private String nom;
	  private int couleur;
	  private int score;
	  private boolean isMyTurn;
	  
	  public Joueur(String nom, int couleur,int score,boolean isMyturn) {
	    this.nom     = nom;
	    this.couleur = couleur;
	    this.score   = score;
	    this.isMyTurn = isMyturn;
	  }
	  public Joueur() {
		    
	  }
	  public String getNom() {
	    return nom;
	  }

	  public int getCouleur() {
	    return couleur;
	  }
	  public int getScore() {
		    return score;
	  }
	 

	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
	public int score() {
		return score;
	}

	public void score(int score) {
		this.score = score;
	}
	public void color(int color) {
		this.couleur = color;
	}
	public int color() {
		return couleur;
	}

	public void nom(String nom) {
		this.nom = nom;
	}
	public String nom() {
		return nom;
	}

}
