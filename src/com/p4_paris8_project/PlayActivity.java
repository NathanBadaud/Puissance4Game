package com.p4_paris8_project;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class PlayActivity extends Activity {
	TimerTask mTimerTask;
	final Handler handler = new Handler();
	Timer t = new Timer();	
	TextView mytimer;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //set the activity
	        setContentView(R.layout.activity_play);
	        //initialise(tab of cells as a parameter)
	        
	        final Joueur player1 = new Joueur("Joueur 1",1,21,true);
	        final Joueur player2 = new Joueur("Joueur 2",2,21,false);
	        
	        final Puissance4Game monEspace = new Puissance4Game(6,7,player1,player2);
	        monEspace.init();
	        
	        //Get our linearlayout
	        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.grille);
	        
	        for (int i = 0; i < monEspace.getRow() ; i++) {

	            LinearLayout line = new LinearLayout(this);
	            line.setOrientation(LinearLayout.HORIZONTAL);


	            for (int j = 0; j < monEspace.getColumn(); j++) {
	                final int colIndex = j;
	                monEspace.plateauView[j][i] = new TextView(this);
	                monEspace.plateauView[j][i].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
	                monEspace.plateauView[j][i].setBackgroundResource(R.drawable.vide);
	                monEspace.plateauView[j][i].setOnClickListener(new View.OnClickListener() {
	                    @Override
	                    public void onClick(View view) {
	                    	doTimerTask();
	                        monEspace.verify(colIndex);
	                        final TextView scoreP1 = (TextView) findViewById(R.id.score1);
	                        final TextView scoreP2 = (TextView) findViewById(R.id.score2);
	                    	mytimer = (TextView) findViewById(R.id.timer);
	                    	
	                        scoreP1.setText(String.valueOf(player1.getScore()));
	                        scoreP2.setText(String.valueOf(player2.getScore()));
	                    
	                        //set verification of win here 
	                        if(monEspace.LookFor4()){
	                			Toast.makeText(getApplicationContext(), 
	                                    "You win", Toast.LENGTH_LONG).show();
	                		}
	                		else{
	                			Toast.makeText(getApplicationContext(), 
	                                    "To be continued", Toast.LENGTH_LONG).show();
	                		}
	                    }
	                });
	                
	                line.addView(monEspace.plateauView[j][i]);
	            }

	            linearLayout.addView(line);
	        }
	    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	private int nCounter = 0;

	public void doTimerTask(){
			stopTask();
	    	mTimerTask = new TimerTask() {
	    	        public void run() {
	    	                handler.post(new Runnable() {
	    	                        public void run() {
	    	                        	nCounter++;
	                                        // update TextView

	    		                        mytimer.setText(String.valueOf(nCounter));
	 
	    	                        	Log.d("TIMER", "TimerTask run");
	    	                        }
	    	               });
	    	        }};
	 
	            // public void schedule (TimerTask task, long delay, long period) 
	    	    t.schedule(mTimerTask, 0, 1000);  // 
	  
	    	 }
	 
	    	  public void stopTask(){
	 
	    	   if(mTimerTask!=null){
	    		  nCounter = 0;
	              mytimer.setText(String.valueOf(nCounter));
	    	      Log.d("TIMER", "timer canceled");
	    	      mTimerTask.cancel();
	    	 }
	 
	    }    
}
