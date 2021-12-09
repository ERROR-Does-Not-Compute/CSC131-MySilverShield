package com.example.myapplication.iceinfo.personalinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfoDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalinfodata";
    private static final String TABLE_NAME= "personal_info_table";
    private static final String KEY_ID = "id";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String DATEOFBIRTH = "dateOFBirth";
    private static final String ADDRESS = "Address";
    private static final String CITY = "City";
    private static final String ZIPCODE = "Zipcode";
    private static final String PHONENUMBER = "phoneNumber";
    private static final String INSURANCEPROVIDER = "insuranceProvider";
    private static final String POLICYNUMBER = "policyNumber";
    private static final String BLOODTYPE = "bloodType";

    public PersonalInfoDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase pi_db) {

        //Create Personal Info Table
        String CREATE_PERSONAL_INFO_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + FIRSTNAME + " TEXT,"
                + LASTNAME + " TEXT," + DATEOFBIRTH + " TEXT," + ADDRESS + " TEXT," + CITY
                + " TEXT," + ZIPCODE + " TEXT," + PHONENUMBER + " TEXT,"
                + INSURANCEPROVIDER + " TEXT," + POLICYNUMBER + " TEXT," + BLOODTYPE + " TEXT" + ")";
        pi_db.execSQL(CREATE_PERSONAL_INFO_TABLE);
    }

    //Not using, but required for SQLiteDatabase
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add Personal Info
    public void addPersonalInfo(PersonalInfoDesign personalInfo){
        SQLiteDatabase pi_db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(FIRSTNAME,personalInfo.getFirstName());
        c.put(LASTNAME,personalInfo.getLastName());
        c.put(DATEOFBIRTH,personalInfo.getDateOfBirth());
        c.put(ADDRESS,personalInfo.getAddress());
        c.put(CITY,personalInfo.getCity());
        c.put(ZIPCODE,personalInfo.getZipcode());
        c.put(PHONENUMBER,personalInfo.getPhoneNumber());
        c.put(INSURANCEPROVIDER,personalInfo.getInsuranceProvider());
        c.put(POLICYNUMBER,personalInfo.getPolicyNumber());
        c.put(BLOODTYPE,personalInfo.getBloodType());
        pi_db.insert(TABLE_NAME,null,c);
        pi_db.close();
    }

    //Get Personal Info
    public List<PersonalInfoDesign> getAllPersonalInfo(){
        List<PersonalInfoDesign> personal_info_list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase pi_db=this.getReadableDatabase();
        Cursor c=pi_db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {
                personal_info_list.add(new PersonalInfoDesign(c.getInt(0),c.getString(1),
                        c.getString(2), c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6), c.getString(7),c.getString(8),c.getString(9),
                        c.getString(10)));
            } while (c.moveToNext());
            c.close();
        }
        return personal_info_list;
    }

    //Count used to ensure only 1  set of personal info is collected
    public int count(){
        int count = 0;
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    //Delete Personal Info
    public void deletePersonalInfo(PersonalInfoDesign personalInfo) {
        SQLiteDatabase pi_db = this.getWritableDatabase();
        pi_db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(personalInfo.getId())});
        pi_db.close();
    }
}