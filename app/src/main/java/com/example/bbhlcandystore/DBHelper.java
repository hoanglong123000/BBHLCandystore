package com.example.bbhlcandystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{
    // Initialize a variable DBName = name of database in String.
    private static final String DBName= "login.db";

    // Constructor of DBHelper.
    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    // This function is overwritten(Override) to create a data table in order to create database.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createdatabase = "create Table logindatabase(username TEXT primary key, pass TEXT)";
        sqLiteDatabase.execSQL(createdatabase);
    }

    // This function will delete data table in database if there is available database.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists logindatabase");
    }

    // Function to insert information into database.
    public Boolean insertData(String username, String pass)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("pass", pass);
        long res = mydb.insert("logindatabase", null, contentValues);
        if (res == -1)
        {
            return false;
        }
        else
            return true;
    }

    // Function to check username in information of database whether it exists or not.
    public Boolean checkusername(String username)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from logindatabase where username = ?", new String[] { username});
        if (cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Function to check username and its password of database.
    public Boolean checkusernamepassword(String username, String password)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from logindatabase where username = ? and pass = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }


}
