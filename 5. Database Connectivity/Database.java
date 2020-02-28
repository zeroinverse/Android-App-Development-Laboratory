package com.example.databaseconnectivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "info", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE INFO(NAME VARCHAR, GENDER VARCHAR, CODE INT PRIMARY KEY, " +
                "DEPARTMENT VARCHAR, SALARY VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS INFO");
        onCreate(db);
    }

    public void insert(int code, String name, String gender, String dept, String salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("code", code);
        cv.put("name", name);
        cv.put("gender", gender);
        cv.put("department", dept);
        cv.put("salary", salary);
        db.insert("info", null, cv);
    }

    public void update(int code, String name, String gender, String dept, String salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("gender", gender);
        cv.put("department", dept);
        cv.put("salary", salary);
        db.update("info", cv, "code=?", new String[]{Integer.toString(code)});
    }

    public void delete(int code) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("info", "code=?", new String[]{Integer.toString(code)});
    }

    public Cursor display(int code) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from info where code=" + Integer.toString(code), null);
    }

    public Cursor display() {
        SQLiteDatabase db = this.getWritableDatabase();
            return db.rawQuery("select * from info", null);
    }

}
