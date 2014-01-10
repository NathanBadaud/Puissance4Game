package com.p4_paris8_project;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 public void two_player(View view) {

	        Intent intent = new Intent(this, PlayActivity.class);
	        startActivity(intent);

	    }
	    public void one_player(View view) {
	    	indispo();
	        //Intent intent = new Intent(this, PlayActivity.class);
	        //startActivity(intent);

	    }
	    public void score(View view) {


	    	indispo();

	    }

	    public void about(View view) {

	        Intent intent = new Intent(this, Apropos.class);
	        startActivity(intent);

	    }
	    
	    public void exit(View view) {
	    	System.exit(0);
	    }
	    public void indispo(){
	    	Toast.makeText(getApplicationContext(),
					"pas encore disponible", Toast.LENGTH_LONG)
					.show();
	    }
	    public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			    case R.id.score:
			    	indispo();
				    return true;
			    case R.id.exit:
			    	System.exit(0);
				    return true;
			    default:
			    return super.onOptionsItemSelected(item);
			}
		}
}
