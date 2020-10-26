package com.rishabhpanesar.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.logging.StreamHandler;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "STUDENT_TABLE";
    public static final String ID = "ID";
    public static final String F_NAME = "FIRST_NAME";
    public static final String L_NAME = "LAST_NAME";
    public static final String MARKS = "MARKS";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+F_NAME+" TEXT, "+L_NAME+" TEXT, "+MARKS+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(F_NAME, name);
        values.put(L_NAME, surname);
        values.put(MARKS, marks);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public boolean update(String id, String f_NAME, String l_NAME, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(F_NAME, f_NAME);
        values.put(L_NAME, l_NAME);
        values.put(MARKS, marks);
        int x = 0;
        x = db.update(TABLE_NAME, values, ID + " = ? ", new String[]{id});
        if (x == 0) {
            return false;
        }
        return true;
    }

    public boolean delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int status = 0;
        status = db.delete(TABLE_NAME, ID + " = ? ", new String[]{id});
        if (status == 0) {
            return false;
        }
        return true;
    }
}
