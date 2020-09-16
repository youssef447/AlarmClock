package com.example.android.alarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

public static final String dbName="Users_Data.db";
public static final String TableName="Users_table";
public static final String Email="Email";
public static final String Username="username";
public static final String Phone="Phone";
public static final String password="Password";

    public database(@Nullable Context context) {
        super(context, dbName, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableName + " ("
                + Email + " VARCHAR(320) UNIQUE," + Username + " TEXT UNIQUE," + password + " TEXT,"+Phone+" VARCHAR(320))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(db);
    }
    public void insert(String email,String username,String phone,String psswd ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues row =new ContentValues();
        row.put(Email,email);
        row.put(Username,username);
        row.put(password,psswd);
        row.put(Phone,phone);

        db.insert(TableName,null,row);
        db.close();

    }
    public Boolean read(String email_username,String Password){

        SQLiteDatabase db=getReadableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM "+TableName+ " WHERE ("+Email+" = '"+email_username+"' OR "+Username+" = '"+email_username+"') AND "+ password +" = '"+Password+"'",null);


        if(c.getCount()>0) {
            c.close();
            db.close();
            return true;
        }
        c.close();
        db.close();
        return false;




    }
}
