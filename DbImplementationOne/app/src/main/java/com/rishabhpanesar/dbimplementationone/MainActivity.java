package com.rishabhpanesar.dbimplementationone;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text1);
        File storagePath = getApplication().getFilesDir();
//        String myDbPath = storagePath + "/fd";
        String myDbPath = storagePath + "/dd";
        text.setText("DB Path : " + myDbPath);
        try {
//            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db = this.openOrCreateDatabase("dd", MODE_PRIVATE, null);
            db.close();
            text.append("\n All Done!");
        } catch (SQLException e) {
            text.append("\nError : " + e.getMessage());
        }
    }
}