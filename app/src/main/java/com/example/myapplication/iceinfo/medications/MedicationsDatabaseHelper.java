package com.example.myapplication.iceinfo.medications;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicationsDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME= "medication_table";
    private static final String KEY_ID = "id";
    private static final String MEDICATION = "medication";

    public MedicationsDatabaseHelper(Context context){
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase m_db) {

        //Create Medication Table
        String CREATE_MEDICATION_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MEDICATION + " TEXT" + ")";
        m_db.execSQL(CREATE_MEDICATION_TABLE);
    }

    //Not using, but required for SQLiteDatabase
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Add Medication
    public void addMedication(MedicationsDesign medication){
        SQLiteDatabase m_db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(MEDICATION,medication.getMedication());
        m_db.insert(TABLE_NAME,null,c);
        m_db.close();
    }

    //Get Medication
    public List<MedicationsDesign> getAllMedications(){
        List<MedicationsDesign> medications_list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase m_db=this.getReadableDatabase();
        Cursor c=m_db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {
                medications_list.add(new MedicationsDesign(c.getInt(0),c.getString(1)));
            } while (c.moveToNext());
            c.close();
        }
        return medications_list;
    }

    //Delete Medication
    public void deleteMedication(MedicationsDesign medication) {
        SQLiteDatabase m_db = this.getWritableDatabase();
        m_db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(medication.getId())});
        m_db.close();
    }
}