package com.rishabhpanesar.filestreams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText txtFileName, txtFileMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFileName = findViewById(R.id.txtFileName);
        txtFileMsg = findViewById(R.id.txtFileMsg);
    }

    public void save(View view) {
        String fileName = txtFileName.getText().toString() + ".txt";
        String msg = txtFileMsg.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            Toast.makeText(MainActivity.this, fileName + " Saved!", Toast.LENGTH_LONG).show();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(View view) {
        String fileName = txtFileName.getText().toString() + ".txt";
        String msg;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            String inpStr;
            while ((inpStr = reader.readLine()) != null) {
                stringBuffer.append(inpStr);
            }
            msg = stringBuffer.toString();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
