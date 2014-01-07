package com.puissancequatre;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnePlayer extends Activity{

static final int nb_row=6;
static final int nb_column=7;

static int tab[][]= new int[nb_column][nb_row];
static TextView tabView[][]= new TextView[nb_column][nb_row];

int nb_coup=0;

	    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tableau);
	    } // "}" a effacer
	    /********
	   String txt = new String();
      TextView tv = new TextView(this);
      accelerometre = new Capteur();
      
        SensorManager m = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
      //  m.registerListener(accelerometre, SensorManager.SENSOR_ACCELEROMETER);

        
        init(tab);


        //recuperer layout
        final  LinearLayout linearLayout = (LinearLayout) findViewById(R.id.game_table);
        for (int i=0 ;i<nb_row;i++)
        {

            final int rowIndex = i;
            LinearLayout line = new LinearLayout(this);
            line.setOrientation(LinearLayout.HORIZONTAL);



            for(int j=0; j<nb_column;j++)
            {
                final int colIndex = j;
                tabView[j][i] = new TextView(this);
                tabView[j][i].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
                
                tabView[j][i].setBackgroundResource(R.drawable.back2);// à modifier
                
                tabView[j][i].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Context context = getApplicationContext();
                        int  row  ;
                        int gagnant=0;

                        row=debut_colonne(tab,colIndex);
                      if( tab[colIndex][row]==0 )
                        {
                            //  animation(row,colIndex,linearLayout);

                            tabView[colIndex][row].setBackgroundResource(R.drawable.beige); // source image à modifier
                            tab[colIndex][row]=1;


                        }

                         verfication_gagnant(tab) ;
                      /*   {
                             int colId=IA.play(2,tab,nb_row,nb_column);
                             if(colId<0 || colId>6)
                             {
                                 Random rnd = new Random();

                                 colId = rnd.nextInt(7);
                             }
                             row=debut_colonne(tab, colId);
                             tabView[colId][row].setBackgroundResource(R.drawable.****);
                             tab[colId][row]=2;
                             nb_coup++;


                         }*/


/*************

                    }
                });

                line.addView(tabView[j][i]);

            }


            line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT,1));
            linearLayout.addView(line);
           // debut();

        }
    }
	    public  void init(int tab[][])
	    {
	        for (int i=0;i<nb_row;i++)
	        {
	            for (int j=0;j<nb_column;j++)
	            {
	                tab[j][i]=0;

	            }
	        }

	    }
	    static void reset()
	    {
	        //animation();
	        for (int i=0;i<nb_row;i++)
	        {
	            for (int j=0;j<nb_column;j++)
	            {
	                tab[j][i]=0;
	                tabView[j][i].setBackgroundResource(R.drawable.back2); // source image à modifier
	            }
	        }
	    }
	    public int debut_colonne(int tab[][],int colonne)
	    {
	        int ligne=nb_row-1;


	        while(ligne!= 0 && tab[colonne][ligne]!=0 )
	        {
	            ligne--;

	        }


	        return ligne;
	    }

	    public int  verfication_gagnant(int  tab[][])
	    {

	        int gagnant=0;
	        /* Context context = getApplicationContext();
	int duration = Toast.LENGTH_SHORT;     */
	
	/***************
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
	        
	     // *********************    à revoir et vérifier 		*************************
	        int x= Regle.verification_rangee(tab);
	        int y= Regle.verification_colonne(tab);
	        int da= Regle.verification_diagonale_asc(tab);
	        int dd= Regle.verification_diagonale_desc(tab);
	     // *********************    à revoir et vérifier 		*************************
	        
	        if (x!=0)gagnant=x;
	        if (y!=0)gagnant=y;
	        if (da!=0)gagnant=da;
	        if (dd!=0)gagnant=dd;
	        switch (gagnant)
	        {
	            case 1 :
	                fenetre.setMessage("Bravo vous avez gagnez.");
	                fenetre.show();
	                break;
	            case 2:

	                fenetre.setMessage("désolé vous avez perdu.");
	                fenetre.show();
	                break;
	            default:
	                break;
	        }
	        if (gagnant!=0)
	        {

	            //ScoreDB bdd= new ScoreDB(this);
	        	ScoreDB db= new ScoreDB();
	            db.open();
	            db.insertScore(nb_coup);
	            db.close();
	        }
	        return gagnant;
	        
	    }*************/
}