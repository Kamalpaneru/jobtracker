package com.example.dillichalise.jt;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dillichalise on 5/24/17.
 */

public class DatabaseHelperForm extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "forms.db";
    private static final String TABLE_NAME = "forms";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_JOBTITLE = "jobtitle";
    private static final String COLUMN_SKILLS = "skills";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE = "mn";
    private static final String COLUMN_LOCATION = "location";
    private static final String TABLE_CREATE = "create table forms (id integer primary key not null , " +
            "jobtitle text not null, skills text not null, company text not null, email text not null , mn text not null, location text not null );";
    SQLiteDatabase db ;
    public DatabaseHelperForm(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertForm(Form c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from forms";
        Cursor formCursor = db.rawQuery(query, null);
        int count = formCursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_JOBTITLE, c.getJobtitle());
        values.put(COLUMN_SKILLS, c.getSkills());
        values.put(COLUMN_COMPANY, c.getCompany());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_MOBILE, c.getMn());
        values.put(COLUMN_LOCATION, c.getLocation());

        db.insert(TABLE_NAME, null, values);
        //db.close();
    }


//    public String getFormContents(){
//        db = this.getReadableDatabase();
//        String query = "select location from forms";
//        Cursor cursor = db.rawQuery(query, null);
//        String a = null;
//        if(cursor.moveToFirst()){
//            do{
//                a = cursor.getString(0);
//
//            }while(cursor.moveToNext());
//            cursor.close();
//        }
//        return a;
//
//    }


    public List<String> getFormLoc(){
        db = this.getReadableDatabase();
        String query = "select location from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List a = new ArrayList();
        if(cursor.moveToFirst()){
            do{
                a.add(cursor.getString(0));
            }while(cursor.moveToNext());
            cursor.close();
        }
        return a;

    }

    public List<Form> getFormContents(){
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<Form> all = new ArrayList();
        if(cursor.moveToFirst()){
            do{
                Form form=new Form();
                form.setId(cursor.getString(0));
                form.setJobtitle(cursor.getString(1));
                form.setSkills(cursor.getString(2));
                form.setCompany(cursor.getString(3));
                form.setEmail(cursor.getString(4));
                form.setMn(cursor.getString(5));
                form.setLocation(cursor.getString(6));
//                all.add(cursor.getString(1));
//                all.add(cursor.getString(2));
//                all.add(cursor.getString(3));
//                all.add(cursor.getString(4));
//                all.add(cursor.getString(5));
                all.add(form);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return all;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

}
