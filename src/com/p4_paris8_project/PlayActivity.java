package com.p4_paris8_project;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
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
	TextView mytimer;
	// nombre de seconde de jeu
	private int nCounter = 30;
	private boolean finished = false;
	//Deux joeur initials
	final Joueur player1 = new Joueur("Joueur 1", 1, 21, true);
	final Joueur player2 = new Joueur("Joueur 2", 2, 21, false);
	//creer une classe de jeu;
	final Puissance4Game monEspace = new Puissance4Game(8, 9, player1, player2);
	private LinearLayout grille;
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

	public void doTimerTask() {
		stopTask();
		mTimerTask = new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						nCounter--;
						// affiche le timer
						mytimer.setText(String.valueOf(nCounter));
						if (nCounter <= 0) {
							nCounter = 30;
							// diminuer le score si temps de jeu fini , et
							// rejouer;
							int myscore = monEspace.getCurrentPlayer().score();
							monEspace.getCurrentPlayer().score(myscore - 1);
						}
					}
				});
			}
		};

		t.schedule(mTimerTask, 0, 1000); //chaque 1 seconde ++ 

	}

	public void stopTask() {

		if (mTimerTask != null) {
			nCounter = 30;
			mytimer.setText(String.valueOf(nCounter));
			mTimerTask.cancel();
		}

	}
	
	public void TouchVerification(int colIndex){
		final TextView scoreP1 = (TextView) findViewById(R.id.score1);
		final TextView scoreP2 = (TextView) findViewById(R.id.score2);
		final TextView name1 = (TextView) findViewById(R.id.name1);
		final TextView name2 = (TextView) findViewById(R.id.name2);
		mytimer = (TextView) findViewById(R.id.timer);
		// demarrer timer
		doTimerTask();
		int result = monEspace.verify(colIndex);
		// si le jeu est terminé (result = 0 -> en cours)
		if(result !=0 && !finished){
			//pour ne pas rentrer a chaque verification aprés la fin du jeu
			finished = true;
			if (result == 1) {
				Toast.makeText(getApplicationContext(),
						"Joueur 1 remporte le jeu",
						Toast.LENGTH_LONG).show();
				name1.setTextColor(getResources().getColor(R.color.gold));
				blink(name1);
				grille.setBackgroundResource(R.drawable.done);
			} else if (result == 2) {
				Toast.makeText(getApplicationContext(),
						"Joueur 2 remporte le jeu",
						Toast.LENGTH_LONG).show();
				name2.setTextColor(getResources().getColor(R.color.gold));
				blink(name2);
				grille.setBackgroundResource(R.drawable.done);
				
			} else if (result == -1) {
				Toast.makeText(getApplicationContext(),
						"Egalité", Toast.LENGTH_LONG)
						.show();
				grille.setBackgroundResource(R.drawable.equal);
			}
			//stop timer ;
			stopTask();
		}
		scoreP1.setText(String.valueOf(player1
				.getScore()));
		scoreP2.setText(String.valueOf(player2
				.getScore()));
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
}
