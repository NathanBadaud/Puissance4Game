package com.p4_paris8_project;

public class Joueur {
	  private String nom;
	  private int couleur;
	  private int score;
	  private int tempsReflexion;
	  private boolean isMyTurn;
	  
	  public Joueur(String nom, int couleur,int score,boolean isMyturn) {
	    this.nom     = nom;
	    this.couleur = couleur;
	    this.score   = score;
	    this.isMyTurn = isMyturn;
	    this.tempsReflexion = 30;
	  }
	  public Joueur() {
		    
	  }
	  public String getNom() {
	    return nom;
	  }

	  public boolean isMyTurn() {
		return isMyTurn;
	  }
	  public int getCouleur() {
	    return couleur;
	  }
	  public int getScore() {
		    return score;
	  }
	  public int getTempsReflexion() {
		    return tempsReflexion;
	  }
	  public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	  }

	  public void setScore(int score) {
		this.score = score;
	  }
	  public void setTempsReflexion(int tempsReflexion) {
			this.tempsReflexion = tempsReflexion;
		  }
	  public void setCouleur(int couleur) {
		this.couleur = couleur;
	  }
	  public void setNom(String nom) {
		this.nom = nom;
	  }




}
