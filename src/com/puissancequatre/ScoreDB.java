package com.puissancequatre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ScoreDB {

    private static final int VERSION_DB = 2;
    private static final String NOM_DB = "score.db";

    private static final int NUM_COL_Id = 0;
    private static final int NUM_COL_Score = 1;

    private SQLiteDatabase db;

    private DataBase maBaseSQLite;

    public static String getCOL_Score() {
        return DataBase.COL_Score;
    }

    // à  revérifier 
    public void ScoreBDD(Context context) {
        //On créer la BDD et sa table
        maBaseSQLite = new DataBase(context, NOM_DB, null, VERSION_DB);
    }

    public void open() {
        //on ouvre la BDD en écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        db.close();
    }

    public SQLiteDatabase getBDD() {
        return db;
    }

    public Cursor getCursor() {

        Cursor c = db.query(DataBase.TABLE_Scores, new String[]{DataBase.COL_Id,DataBase.COL_Score}, null, null, null, null, DataBase.COL_Score + " LIMIT 0,5");
        //bdd.rawQuery("SELECT " + BaseDeDonnees.COL_Id, BaseDeDonnees.COL_Score + " FROM " + BaseDeDonnees.TABLE_Scores + " ORDER BY " + BaseDeDonnees.COL_Score + " ASC", null);
        //bdd.query(BaseDeDonnees.TABLE_Scores, new String[]{BaseDeDonnees.COL_Id,BaseDeDonnees.COL_Score}, null, null, null, null, "rand ASC");
        return c;
    }

    public long insertScore(int score) {

        //Création d'un ContentValues (fonctionne comme une HashMap)

        ContentValues values = new ContentValues();

        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(DataBase.COL_Score,score);


        //on insère l'objet dans la BDD via le ContentValues

        return db.insert(DataBase.TABLE_Scores, null, values);
    }


    public int removeTaskWithID(int id) {


        return db.delete(DataBase.TABLE_Scores, DataBase.COL_Id + " = " + id, null);
    }


    public int getScoreWithId(int id) {


        Cursor c = db.query(DataBase.TABLE_Scores, new String[]{DataBase.COL_Id,DataBase.COL_Score},DataBase.COL_Id + " LIKE \"" +  id + "\"", null, null, null, null);

        //si aucun élément n'a été retourné dans la requête, on renvoie null

        if (c.getCount() == 0)
            return 0;

        //Sinon on se place sur le premier élément
         c.moveToFirst();
        int score = c.getInt(NUM_COL_Score);

        return score;
    }

}
