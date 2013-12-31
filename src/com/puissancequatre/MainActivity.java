package com.puissancequatre;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//j'ajoute ce commentaire par ex et j'enregistre
		// un commentaire nimportkoi pour voir
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		//je fais une modification aussi pour te montrer
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
