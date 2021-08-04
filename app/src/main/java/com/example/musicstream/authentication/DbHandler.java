package com.example.musicstream.authentication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "UserDatabase";
    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //when launch sqlite db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "pword TEXT ) ";
        db.execSQL(sql);
    }

    //if already exist, drop iteration of onCreate
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS users";
        db.execSQL(sql);
        onCreate(db);
    }
}