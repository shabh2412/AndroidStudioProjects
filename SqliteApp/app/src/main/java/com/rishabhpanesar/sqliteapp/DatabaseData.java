package com.rishabhpanesar.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseData extends AppCompatActivity {

    TextView dataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_data);

        dataText = findViewById(R.id.dataText);

        Intent intent = getIntent();
        String output = intent.getStringExtra("data");
        dataText.setText(output);
        showMsg("Data", output);
    }
    public void showMsg(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}