package com.p4_paris8_project;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {
	//variable d'initailisation timer;
	TimerTask mTimerTask;
	final Handler handler = new Handler();
	Timer t = new Timer();
	boolean pauseTimer = false;
	TextView mytimer;
	// nombre de seconde de jeu
	private int nCounter = 0,currentTimerValue;
	private boolean finished = false;
	//Deux joeur initials
	final Joueur player1 = new Joueur("Joueur 1", 1, 21, true);
	final Joueur player2 = new Joueur("Joueur 2", 2, 21, false);
	//creer une classe de jeu;
	final Puissance4Game monEspace = new Puissance4Game(8, 9, player1, player2);
	private LinearLayout grille;
	TextView scoreP1,scoreP2,name1,name2; 
	int result;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// afficher activity
		setContentView(R.layout.activity_play);
		// initialiser la grille a VIDE
		monEspace.init();
		// recuperer linear layout qui va contenir le plateau de jeu
		grille = (LinearLayout) findViewById(R.id.grille);
		
		for (int i = monEspace.getLastRow(); i >= 1; i--) {

			LinearLayout line = new LinearLayout(this);
			line.setOrientation(LinearLayout.HORIZONTAL);
			//initialiser l'affichage du plateau
			for (int j = 1; j <= monEspace.getLastCol(); j++) {
				final int colIndex = j;
				monEspace.plateauView[j][i] = new TextView(this);
				monEspace.plateauView[j][i]
				.setLayoutParams(new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT, 1));
				monEspace.plateauView[j][i]
						.setBackgroundResource(R.drawable.vide);
				monEspace.plateauView[j][i]
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						doTimerTask();
						TouchVerification(colIndex);
					}
				});

				line.addView(monEspace.plateauView[j][i]);
			}
			//ajouter la ligne a la layout grille;
			grille.addView(line);
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		    case R.id.replay:
		    	replay();
			    return true;
		    case R.id.about:
		    	about();
			    return true;
		    case R.id.exit:
		    	System.exit(0);
			    return true;
		    default:
		    return super.onOptionsItemSelected(item);
		}
	}
    public void replay() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    public void about() {

    	Intent intent = new Intent(this, Apropos.class);
        startActivity(intent);

    }
	public void doTimerTask() {
		currentTimerValue = nCounter;
		stopTask();
		//verifie si le jeu est active si l'activité est en pause;
		if(!finished || pauseTimer){
		mTimerTask = new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
							nCounter++;
							// affiche le timer
							mytimer.setText(String.valueOf(nCounter));
							Log.w("myApp", "it work");
					}
				});
			}
		};

		t.schedule(mTimerTask, 0, 1000); //chaque 1 seconde ++ 
	 }
	}

	public void stopTask() {

		if (mTimerTask != null) {
			nCounter = 0;
			mytimer.setText(String.valueOf(nCounter));
			mTimerTask.cancel();
			Log.w("myApp", "stop");
		}

	}
	
	public void TouchVerification(int colIndex){
		mytimer = (TextView) findViewById(R.id.timer);
		scoreP1 = (TextView) findViewById(R.id.score1);
		scoreP2 = (TextView) findViewById(R.id.score2);
		name1 = (TextView) findViewById(R.id.name1);
		name2 = (TextView) findViewById(R.id.name2);
	    result = monEspace.verify(colIndex);
		// si le jeu est terminé (result = 0 -> en cours)
		if(result !=0 && !finished){
			// demarrer timer
			//pour ne pas rentrer a chaque verification aprés la fin du jeu
			finished = true;
			if (result == 1) {
				DisplayWinner(name1,player1);
			} else if (result == 2) {
				DisplayWinner(name2,player2);
			} else if (result == -1) {
				checkWinner();
			}
			stopTask();//stop timer ;
		}
		
		// augmente le temps de reflexion du joueur
		int oldTime = monEspace.getCurrentPlayer().getTempsReflexion();
		monEspace.getCurrentPlayer().setTempsReflexion(oldTime + currentTimerValue);
		
		//afficher le score courant;
		scoreP1.setText(String.valueOf(player1
				.getScore()));
		scoreP2.setText(String.valueOf(player2
				.getScore()));
	}
	//verifie le gagnant si le jeu est nulle;
	void checkWinner(){
		int p1Time = player1.getTempsReflexion();
		int p2Time = player2.getTempsReflexion();
		//declarrer partie nulle
		Toast.makeText(getApplicationContext(),
				"Partie nulle", Toast.LENGTH_LONG)
				.show();
		if(p1Time > p2Time){
			//joueur 2 gagne;
			DisplayWinner(name2,player2);
			//mettre a zéro le score du joueur qui a perdu
			player1.setScore(0);
			//enregistrer le score dans la base
			
		}else if (p2Time > p1Time){
			//joueur 1 gagne;
			DisplayWinner(name1,player1);
			//mettre a zéro le score du joueur qui a perdu
			player2.setScore(0);
			//enregistrer le score dans la base
			
		}else{
			Toast.makeText(getApplicationContext(),
					"Partie sans gagnant", Toast.LENGTH_LONG)
					.show();
			grille.setBackgroundResource(R.drawable.equal);
		}//pas de gagnant(egalité temps)
		
	}
	
	//afficher le gagnant
	void DisplayWinner(TextView gagnant,Joueur player){
		Toast.makeText(getApplicationContext(),
				player.getNom()+" remporte le jeu",
				Toast.LENGTH_LONG).show();
		gagnant.setTextColor(getResources().getColor(R.color.gold));
		blink(gagnant);
		grille.setBackgroundResource(R.drawable.done);
	}
	
	public void blink(final TextView txt){
	    final Handler handler = new Handler();
	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	        int timeToBlink = 1000;    //in milissegunds
	        try{Thread.sleep(timeToBlink);}catch (Exception e) {}
	            handler.post(new Runnable() {
	                @Override
	                    public void run() {
	                	
	                    if(txt.getVisibility() == View.VISIBLE){
	                        txt.setVisibility(View.INVISIBLE);
	                    }else{
	                        txt.setVisibility(View.VISIBLE);
	                    }
	                    blink(txt);
	                }
	                });
	            }
	        }).start();
	}
	//bloquer le button retour : a activer aprés
	/*@Override
	public void onBackPressed() {	
	      return;
	}
	 */
	@Override
	protected void onPause() {
        super.onPause();	
		pauseTimer = true;
	}
	//poursuivre le timer 
	@Override
	protected void onResume() {
        super.onPause();	
		pauseTimer = false;
	}
	
}
