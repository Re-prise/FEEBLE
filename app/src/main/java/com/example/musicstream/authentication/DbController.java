package com.example.musicstream.authentication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbController extends DbHandler {
    public static Context context;

    public DbController(Context context) {
        //context = this;
        super(context);
    }

    //CREATE function, adds users into database
    public boolean create(ClassUser objectStudent) {
        ContentValues values = new ContentValues();
        values.put("firstname", objectStudent.userName);
        values.put("pword", objectStudent.passWord);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert("users", null, values) > 0;
        db.close();
        return createSuccessful;
    }

    //READ function, gathers all info from the database (user database, in this instance)
    public List<ClassUser> read() {
        List<ClassUser> recordsList = new ArrayList<ClassUser>();
        String sql = "SELECT * FROM users ORDER BY id DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String userName = cursor.getString(cursor.getColumnIndex("firstname"));
                String userPword = cursor.getString(cursor.getColumnIndex("pword"));
                ClassUser objectStudent = new ClassUser();
                objectStudent.id = id;
                objectStudent.userName = userName;
                objectStudent.passWord = userPword;
                recordsList.add(objectStudent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recordsList;
    }
}