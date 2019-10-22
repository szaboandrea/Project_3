package com.example.labor3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "project3";
    private static final String HOBBIES_TABLE_NAME = "hobbies";
    private static final String HOBBIES_COLUMN_ID = "id";
    private static final String HOBBIES_COLUMN_NAME = "first_name";
    private static final String HOBBIES_COLUMN_LASTNAME = "last_name";
    private static final String HOBBIES_COLUMN_DEPARTMENT = "department";

    private static final String HOBBY_TABLE_NAME = "hobby";
    private static final String HOBBY_COLUMN_ID = "hobby_id";
    private static final String HOBBY_COLUMN_NAME = "hobby_name";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_HOBBY = "CREATE TABLE "
            + HOBBY_TABLE_NAME + "(" + HOBBY_COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + HOBBY_COLUMN_NAME + " TEXT );";

    private static final String CREATE_TABLE_PROFILE = " CREATE TABLE "
            + HOBBIES_TABLE_NAME + "(" + HOBBIES_COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HOBBIES_COLUMN_NAME + "TEXT, "
            + HOBBIES_COLUMN_LASTNAME + " TEXT, "
            + HOBBIES_COLUMN_DEPARTMENT +  "TEXT );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_HOBBY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HOBBIES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HOBBY_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProfile(String firstName, String lastName, String department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HOBBIES_COLUMN_NAME, firstName);
        contentValues.put(HOBBIES_COLUMN_LASTNAME, lastName);
        contentValues.put(HOBBIES_COLUMN_DEPARTMENT, department);
        long result = db.insert(HOBBIES_TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertHobby(String hobbyName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HOBBY_COLUMN_NAME, hobbyName);
        long result = db.insert(HOBBY_TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getaAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + HOBBIES_TABLE_NAME, null);
        return res;
    }

    public List<String> getAllHobbies(){
        List<String> hobbyList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + HOBBY_TABLE_NAME, null);

        if (cursor.moveToFirst()){
            do{
                hobbyList.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return hobbyList;
    }

    public Integer deleteDatas(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(HOBBIES_TABLE_NAME,null,null);
    }

    public Integer deleteHobbies(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(HOBBY_TABLE_NAME, null,null);
    }

    public Integer foundName(String name){
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from hobbies where first_name='" + name +"'";
        cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public Integer foundLastName(String name){
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from hobbies where last_name='" + name +"'";
        cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public Integer foundDepartment(String department){
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from hobbies where department='" + department +"'";
        cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }
}


