package com.example.myapplication.SOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactdata";
    private static final String TABLE_NAME= "contacts";
    private static final String KEY_ID = "id";
    private static final String CONTACTNAME = "ContactName";
    private static final String PHONENUMBER = "PhoneNumber";
    private SQLiteDatabase db;
    private String query;
    private Cursor cursor;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Contact Table
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + CONTACTNAME + " TEXT,"
                + PHONENUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    //Not using, but required for SQLiteDatabase
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add Contact Info
    public void addContact(ContactDesign contact){
        db=this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(CONTACTNAME,contact.getContactName());
        c.put(PHONENUMBER,contact.getPhoneNumber());
        db.insert(TABLE_NAME,null, c);
        db.close();
    }

   //Get Contact Info
    public List<ContactDesign> getAllContacts(){
        List<ContactDesign> list = new ArrayList<>();
        query="SELECT * FROM "+TABLE_NAME;
        db=this.getReadableDatabase();
        cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                list.add(new ContactDesign(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //Count used to ensure only 5 contacts selected
    public int count(){
        int count = 0;
        query="SELECT COUNT(*) FROM "+TABLE_NAME;
        db = this.getReadableDatabase();
        cursor=db.rawQuery(query,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            count =cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    //Delete Contact
    public void deleteContact(ContactDesign contact) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}