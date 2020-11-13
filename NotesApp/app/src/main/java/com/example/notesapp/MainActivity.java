package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText notesText;
    Button btnSave, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesText = findViewById(R.id.notesText);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);

        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                String notes = "";
                notes = notesText.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int count = sharedPreferences.getInt("count", 0);
                if (count == 0) {
                    editor.putString("note" + count, notes);
                    count = count + 1;
                    editor.putInt("count", count);
                    editor.apply();
                    Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("note" + count, notes);
                    count = count + 1;
                    editor.putInt("count", count);
                    editor.apply();
                    Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnView:
                SharedPreferences sharedPreferences1 = getSharedPreferences("Notes", MODE_PRIVATE);
                int count1 = sharedPreferences1.getInt("count", 0);
                Intent intent = new Intent(MainActivity.this, NotesWindow.class);
                intent.putExtra("count", count1);
                startActivity(intent);
                break;
        }
    }
}