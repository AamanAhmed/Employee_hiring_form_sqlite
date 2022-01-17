package com.example.employee_hiring_form_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {
    String Databasename = "emp.db";

    public Db(@Nullable Context context) { super (context, "emp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table register(Id integer primary key autoincrement,Username text,Email text,Password text,Expected_Salary Integer,Project_handle Integer,Speciality text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists register");
    }

    public  boolean Insertrecord(String Username , String Email , String Password , Integer Expected_Salary , Integer Projects_Handle , String Speciality ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datainserted = new ContentValues();
        datainserted.put("Username", Username);
        datainserted.put("Email", Email);
        datainserted.put("Password", Password);
        datainserted.put("Expected_Salary", Expected_Salary);
        datainserted.put("Project_handle", Projects_Handle);
        datainserted.put("Speciality", Speciality);
        long success = db.insert("register", null, datainserted);
        if (success > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkemail(String useremail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor find = db.rawQuery("select * from register where Email = ?",new String[]{useremail});
        if (find.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean loginemail(String uemail,String upswd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor find = db.rawQuery("select * from register where Email = ? and Password = ?",new String[]{uemail,upswd});
        if (find.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
