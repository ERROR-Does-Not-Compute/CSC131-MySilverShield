package com.example.myapplication.iceinfo.medicalconditions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicalConditionsDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME= "medical_condition_table";
    private static final String KEY_ID = "id";
    private static final String MEDICALCONDITION = "medicalCondition";

    public MedicalConditionsDatabaseHelper(Context context){
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase mc_db) {

        //Create Medical Condition Table
        String CREATE_MEDICAL_CONDITION_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MEDICALCONDITION + " TEXT" + ")";
        mc_db.execSQL(CREATE_MEDICAL_CONDITION_TABLE);
    }

    //Not using, but required for SQLiteDatabase
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add Medical Condition
    public void addMedicalCondition(MedicalConditionsDesign medicalCondition){
        SQLiteDatabase mc_db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(MEDICALCONDITION,medicalCondition.getMedicalCondition());
        mc_db.insert(TABLE_NAME,null,c);
        mc_db.close();
    }

    //Get Medical Condition
    public List<MedicalConditionsDesign> getAllMedicalConditions(){
        List<MedicalConditionsDesign> medical_conditions_list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase mc_db=this.getReadableDatabase();
        Cursor c=mc_db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {
                medical_conditions_list.add(new MedicalConditionsDesign(c.getInt(0),c.getString(1)));
            } while (c.moveToNext());
            c.close();
        }
        return medical_conditions_list;
    }

    //Delete Medical Condition
    public void deleteMedicalCondition(MedicalConditionsDesign medicalCondition) {
        SQLiteDatabase mc_db = this.getWritableDatabase();
        mc_db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(medicalCondition.getId())});
        mc_db.close();
    }
}