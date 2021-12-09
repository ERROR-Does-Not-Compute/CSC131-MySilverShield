package com.example.myapplication.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PasswordsDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME= "passwords_table";
    private static final String KEY_ID = "id";
    private static final String APPNAME = "AppName";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";

    public PasswordsDatabaseHelper(Context context){
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Password Table
        String CREATE_PASSWORD_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + APPNAME + " TEXT,"
                + USERNAME + " TEXT," + PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_PASSWORD_TABLE);
    }

    //Not using, but required for SQLiteDatabase
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add Password Info
    public void addPassword(PasswordDesign password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(APPNAME,password.getAppName());
        c.put(USERNAME,password.getUsername());
        c.put(PASSWORD,password.getPassword());
        db.insert(TABLE_NAME,null,c);
        db.close();
    }

    //Get Password Info
    public List<PasswordDesign> getAllPasswords(){
        List<PasswordDesign> list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {
                list.add(new PasswordDesign(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            } while (c.moveToNext());
            c.close();
        }
        return list;
    }

    //Delete Password
    public void deletePassword(PasswordDesign password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(password.getId())});
        db.close();
    }
}