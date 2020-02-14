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
        db.execSQL("CREATE TABLE INFO(CODE INT, NAME VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS INFO");
        onCreate(db);
    }

    public void insert(int code, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("code", code);
        cv.put("name", name);
        db.insert("info", null, cv);
    }

    public void update(int code, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("code", code);
        cv.put("name", name);
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
}
