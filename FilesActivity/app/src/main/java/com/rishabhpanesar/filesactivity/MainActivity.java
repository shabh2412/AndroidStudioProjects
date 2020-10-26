package com.rishabhpanesar.filesactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText text;
    public static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.txt1);
    }

    public void save(View view) {
        String msg = text.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput("myTxt.py", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            text.setText("");
            Toast.makeText(MainActivity.this, "File Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(View view) {
        try {
            FileInputStream fileInputStream = openFileInput("myTxt.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}