package com.rockstarrooster.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;

    String createUserTable = "CREATE TABLE if not exists 'user' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' TEXT, 'password' TEXT)";

    public Database(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createUserTable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Insert(ContentValues contentValues){
        getWritableDatabase().insert("user","",contentValues);
    }

    public boolean Login(String username, String password){
        String sqlQuery = "SELECT count(username) from user where username = '"+username+"' and password = '"+password+"'";
        SQLiteStatement lstatement = getReadableDatabase().compileStatement(sqlQuery);
        long lValue = lstatement.simpleQueryForLong();
        lstatement.close();

        if (lValue==1){
            return true;
        }else {
            return false;
        }
    }
}
