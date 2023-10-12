package com.proj.padoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String Dbname = "register.db";

    public DBHelper(@Nullable Context context) {
        super(context, Dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT, full_name TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public boolean insertData(String username, String password, String full_name, String phone){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("full_name", full_name);
        contentValues.put("phone", phone);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else return false;

    }

}
