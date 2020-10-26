package com.rishabhpanesar.databaseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDb.db";
    public static final String TABLE_NAME = "STUDENT_TABLE";
    public static final String ID = "ID";
    public static final String ROLL_NO = "ROLL_NO";
    public static final String NAME = "NAME";
    public static final String MARKS = "MARKS";
    public static final String BRANCH = "BRANCH";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ROLL_NO + " INTEGER UNIQUE, " + NAME + " TEXT, " + BRANCH + " TEXT," + MARKS + " TEXT)");
//        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
//        db.close();
    }

    public boolean addData(String roll, String name, String branch, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (roll.isEmpty() || name.isEmpty() || branch.isEmpty() || marks.isEmpty()) {
            return false;
        }
        values.put(ROLL_NO, roll);
        values.put(NAME, name);
        values.put(BRANCH, branch);
        values.put(MARKS, marks);
        long status = db.insert(TABLE_NAME, null, values);
//        db.close();
        return status != -1;
    }

    public boolean checkDataOccurrence(String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROLL_NO + " = ?", new String[]{roll});
        if (cursor.getCount() != 0) {
            cursor.close();
//            db.close();
            return true;
        }
        cursor.close();
//        db.close();
        return false;
    }

    public ArrayList<Students> getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Students> list = new ArrayList<Students>();
        while (cursor.moveToNext()) {

            String stRoll = cursor.getString(1);
            String stName = cursor.getString(2);
            String stBranch = cursor.getString(3);
            String stMark = cursor.getString(4);
            Students students = new Students(stName, stRoll, stBranch, stMark);
            list.add(students);
        }
        cursor.close();
//        db.close();
        return list;
    }

    public boolean delete(String rollNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, ROLL_NO + " =? ", new String[]{rollNumber});
//        db.close();
        return result != 0;
    }

    public boolean update(String roll, String name, String branch, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ROLL_NO, roll);
        values.put(NAME, name);
        values.put(BRANCH, branch);
        values.put(MARKS, marks);
        int result = 0;
        result = db.update(TABLE_NAME, values, ROLL_NO + " = ? ", new String[]{roll});
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
}
