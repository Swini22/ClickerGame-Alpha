package com.mygdx.game.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import actor.Player;

/**
 * Created by mikael.mathis on 07.12.2015.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PLAYER_TABLE = "CREATE TABLE player ( " +
                "name TEXT " +
                "gold REAL" +
                "exp REAL)";

        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS player");

        // create fresh books table
        this.onCreate(db);
    }

    public void setPlayer(Player player) {

        ContentValues values = new ContentValues();
        values.put("name", player.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("player", null, values);
        db.close();
    }

    public Player getPlayer() {
        String query = "Select * FROM player";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Player player = new Player("", 0, 0);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            player.setName(cursor.getString(0));
            player.setGold(cursor.getDouble(1));
            player.setExp(cursor.getDouble(2));
            cursor.close();
        } else {
            player = null;
        }
        db.close();
        return player;
    }

}
