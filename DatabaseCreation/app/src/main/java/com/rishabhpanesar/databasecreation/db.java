package com.rishabhpanesar.databasecreation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test";

    public db(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTable = "CREATE TABLE empDetails(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Number TEXT NOT NULL, Dept TEXT NOT NULL)";
        database.openOrCreateDatabase(createTable, null);
    }

    public void onUpgrade(SQLiteDatabase database, int version1, int version2) {
    }
}
