package com.p4_paris8_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Apropos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apropos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apropos, menu);
		return true;
	}
	
    public void indispo(){
    	Toast.makeText(getApplicationContext(),
				"pas encore disponible", Toast.LENGTH_LONG)
				.show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		    case R.id.menu:
		    	mainmenu();
			    return true;
		    case R.id.exit:
		    	System.exit(0);
			    return true;
		    default:
		    return super.onOptionsItemSelected(item);
		}
	}
    
    public void mainmenu(){
    	Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
