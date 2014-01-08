/*Bon j’ai commencé à écrire un algorithme pour tester
s’il ya 4 pions de la même couleur et de tester s’il y’a un gagnant, 
puisqu’on n’aura pas le temps après j’ai continué à travailler dessus 
au taf aujourd’hui et je viens de le finir, je l’ajoute au github pour
que tu puisses le voir, je sais pas si tu peux l’intégrer dans 
la classe PuissancGame , sinon laisse le je l’intègre ce soir 
quand je rentre pour bien le tester en même temps.

Voilà l’algorithme de teste :
*/
//on declare les differentes possibiltés de recheche possible
Final static int [] horiz = new int[] {1,0}; //Horizontale
Final static int [] verti = new int[] {0,1}; //Verticale
Final static int [] up = new int[] {1,1}; // diagonale en mantant
Final static int [] down = new int[] {1,-1}; // diagonale en descendant
//mettre toutes les possibiltés dans un tableau;
Final static int [][] possibilities = new int[][] { horiz  , verti  , up  , down }; // possibiltés

//dabord on test si la colonne touchée est pas pleine ;
If( !isFull(mycolumn)){
	//on recupere la premiere ligne vide dans la colonne 
	emptyLine = getEmptyLine(mycolumn) ;
	//on met le code du joueur courant
	plateau [mycolumn][emptyLine] = Player.code ;
	ifWin(mycolumn,emptyLine){
  	//declarer le gagnant ;
  	//bloquer le jeu
  	//afficher score ;
  	//Afficher rejouer ;
  	//afficher menu consulter solde
  }
}

Private Boolean lookfor4(int column, int line,int [] directionOfsearch){
	Int columnToverify = column + directionOfsearch[0] ;//horizontale (0)
	Int lineToverify = column + directionOfsearch[1] ;//vertical (0)
	Int countAfter; //compter combien de la meme couleur aprés
	While(plateau[columnToverify][ lineToverify] == player.code){
		columnToverify += directionOfsearch[0] //horizontale (0);
		lineToverify += directionOfsearch[1] //vertical (0);
		countAfter++;
  }
  //verifier de l’autre coté contraire
	Int columnToverify = column - directionOfsearch[0] ;//horizontale (0);
	Int lineToverify = column - directionOfsearch[1] ;//vertical (0);
	Int countbefore; //compter combien de la meme couleur avant
  While(plateau[columnToverify][ lineToverify] == player.code){
  		columnToverify -= directionOfsearch[0] //horizontale (0);
  		lineToverify -= directionOfsearch[1] //vertical (0);
  		countbefore ++;
  }
  //verifie si on trouvé 4 pions ou plus de la meme couleur des deux coté 
  Return countbefore+1 + countAfter >=4 ;
}

Private Boolean ifWin(in column , int line ){
	For(int I = 0 ;i<possibilities.length;i++){
		If(lookFor4(column,line,possibilities[i])){
	    Return true; // on a une sequence de 4 pions valide ->on a gagnant
    }
  }	
  Return false; // on a une sequence de 4 pions valide ->pas de gagnant ->jeu continue
}

