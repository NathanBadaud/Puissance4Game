package com.puissancequatre;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Score extends Activity {

    ListView lvListe;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);


       // ScoreDB db = new ScoreDB(this);
        ScoreDB db = new ScoreDB();
        db.open();
        Cursor c = db.getCursor();

        lvListe = (ListView) findViewById(R.id.scoreLView);

    lvListe.setAdapter(new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, new String[]{DataBase.COL_Score}, new int[]{android.R.id.text1}));

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);

        db.close();
    }
}