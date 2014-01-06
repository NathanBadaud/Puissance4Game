package com.puissancequatre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	    public static final String TABLE_Scores = "table_Score";
	    public static final String COL_Id = "_id";
	    public static final String COL_Score = "Score";

	    private static final String CREATE_DB = "CREATE TABLE " + TABLE_Scores + " (" + COL_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_Score + " INTEGER NOT NULL  );";

	    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
	        super(context, name, factory, version);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(CREATE_DB);
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int i, int i1)
	    {
	        db.execSQL("drop table if exists "+TABLE_Scores);
	        db.execSQL(CREATE_DB);
	    }
}
