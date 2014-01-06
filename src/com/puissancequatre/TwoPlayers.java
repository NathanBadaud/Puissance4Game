package com.puissancequatre;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class TwoPlayers extends Activity {



	    static final int nb_row = 6;
	    static final int nb_column = 7;

	    public int tab[][] = new int[nb_column][nb_row];
	    public TextView tabView[][] = new TextView[nb_column][nb_row];

	    boolean isYellowTurn;
	    int nb_coup = 0;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        setContentView(R.layout.tableau);
	        init(tab);


	        //recuperer layout
	        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.game_table);
	        for (int i = 0; i < nb_row; i++) {

	            final int rowIndex = i;
	            LinearLayout line = new LinearLayout(this);
	            line.setOrientation(LinearLayout.HORIZONTAL);


	            for (int j = 0; j < nb_column; j++) {
	                final int colIndex = j;
	                tabView[j][i] = new TextView(this);
	                tabView[j][i].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
	                // image à modifier
	                tabView[j][i].setBackgroundResource(R.drawable.beige);
	                tabView[j][i].setOnClickListener(new View.OnClickListener() {
	                    @Override
	                    public void onClick(View view) {
	                        Context context = getApplicationContext();
	                        int row;
	                        int gagnant = 0;

	                        row = debut_colonne(tab, colIndex);
	                        if (isYellowTurn && tab[colIndex][row] == 0) {
	                            //  animation(row,colIndex,linearLayout);

	                            tabView[colIndex][row].setBackgroundResource(R.drawable.back2);// source d'image a changer
	                            tab[colIndex][row] = 1;
	                            isYellowTurn = false;

	                        } else if (tab[colIndex][row] == 0) {
	                            //  animation(row,colIndex,linearLayout);
	                            tabView[colIndex][row].setBackgroundResource(R.drawable.back2);
	                            tab[colIndex][row] = 2;
	                            isYellowTurn = true;
	                            nb_coup++;
	                        }


	                        gagnant = verfication_gagnant(tab);
	                        if (gagnant != 0) {
	                            for (int i = 0; i < 7; i++) {
	                                for (int j = 0; j < 6; j++) {
	                                    tab[i][j] = 3;
	                                }

	                            }
	                        }


	                    }
	                });

	                line.addView(tabView[j][i]);

	            }

	            line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT, 1));
	            linearLayout.addView(line);

	        }
	    }

	    public void init(int tab[][]) {
	        for (int i = 0; i < nb_row; i++) {
	            for (int j = 0; j < nb_column; j++) {
	                tab[j][i] = 0;

	            }
	        }

	    }

	    public void reset() {
	        for (int i = 0; i < nb_row; i++) {
	            for (int j = 0; j < nb_column; j++) {
	                tab[j][i] = 0;
	                tabView[j][i].setBackgroundResource(R.drawable.back2);
	            }
	        }
	    }

	    public int debut_colonne(int tab[][], int colonne) {
	        int ligne = nb_row - 1;


	        while (ligne != 0 && tab[colonne][ligne] != 0) {
	            ligne--;

	        }


	        return ligne;
	    }

	    public int verfication_gagnant(int tab[][]) {

	        int gagnant = 0;
	        /* Context context = getApplicationContext();
	int duration = Toast.LENGTH_SHORT;     */
	        AlertDialog.Builder fenetre = new AlertDialog.Builder(this);
	        fenetre.setTitle("Resultat");
	        fenetre.setPositiveButton("recommencer", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                Context context = getApplicationContext();
	                reset();

	            }
	        });

	        fenetre.setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                finish();

	            }
	        });

// ***************************     à revoir 
	        int x = Regle.verification_rangee(tab);
	        int y = Regle.verification_colonne(tab);
	        int da = Regle.verification_diagonale_asc(tab);
	        int dd = Regle.verification_diagonale_desc(tab);
	        
//   		   à revoir ***************************
	        
	        
	        if (x != 0) gagnant = x;
	        if (y != 0) gagnant = y;
	        if (da != 0) gagnant = da;
	        if (dd != 0) gagnant = dd;
	        switch (gagnant) {
	            case 1:
	                fenetre.setMessage("Le joueur  jaune à gagner.");
	                fenetre.show();
	                break;
	            case 2:

	                fenetre.setMessage("Le joueur  rouge à gagner.");
	                fenetre.show();
	                break;
	            default:
	                break;
	        }
	        if (gagnant != 0) {

	            ScoreDB bdd = new ScoreDB(this);
	            bdd.open();
	            bdd.insertScore(nb_coup);
	            bdd.close();
	        }
	        return gagnant;

	    }
	    /*public  void animation (int row,int colIndex,LinearLayout linearLayout)
	      {
	          LinearLayout line = new LinearLayout(this);
	          line.setOrientation(LinearLayout.HORIZONTAL);

	          for (int y=0;y<row;y++)
	          {
	              if (isYellowTurn==true)tabView[colIndex][y].setBackgroundResource(R.drawable.sample_2);
	              else tabView[colIndex][y].setBackgroundResource(R.drawable.sample_1);
	              for (int attente=0;attente<7000;attente++)
	                  linearLayout.addView(line);
	              tabView[colIndex][y].setBackgroundResource(R.drawable.sample_3);

	          }
	       

	      



	    }     */

	}