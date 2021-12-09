package com.example.myapplication.iceinfo.allergies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AllergiesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME= "allergy_table";
    private static final String KEY_ID = "id";
    private static final String ALLERGY = "allergy";

    public AllergiesDatabaseHelper(Context context){
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase a_db) {

        //Create Allergy Table
        String CREATE_ALLERGY_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ALLERGY + " TEXT" + ")";
        a_db.execSQL(CREATE_ALLERGY_TABLE);
    }

    //Not using, but required for SQLiteDatabase
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add Allergy
    public void addAllergy(AllergiesDesign allergy){
        SQLiteDatabase a_db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(ALLERGY,allergy.getAllergy());
        a_db.insert(TABLE_NAME,null,c);
        a_db.close();
    }

    //Get Allergy
    public List<AllergiesDesign> getAllAllergies(){
        List<AllergiesDesign> allergies_list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase a_db=this.getReadableDatabase();
        Cursor c=a_db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {
                allergies_list.add(new AllergiesDesign(c.getInt(0),c.getString(1)));
            } while (c.moveToNext());
            c.close();
        }
        return allergies_list;
    }

    //Delete Allergy
    public void deleteAllergy(AllergiesDesign allergy) {
        SQLiteDatabase a_db = this.getWritableDatabase();
        a_db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(allergy.getId())});
        a_db.close();
    }
}