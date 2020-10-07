package com.rishabhpanesar.tablelayoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Button button;
    EditText fName, lName, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        number = (EditText) findViewById(R.id.number);
        Intent data = new Intent(MainActivity.this, FormDetails.class);
        data.putExtra(Intent.EXTRA_TEXT, number.getText().toString());
        startActivity(data);
    }
}