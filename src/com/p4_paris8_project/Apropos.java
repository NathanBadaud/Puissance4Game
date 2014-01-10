package com.p4_paris8_project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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

}
