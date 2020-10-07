package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class NotesWindow extends AppCompatActivity {

    TextView notesTextOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_window);
        Intent intent = getIntent();
        notesTextOutput = findViewById(R.id.notesTextOutput);
        int count = intent.getIntExtra("count", 0);
        if (count == 0) {
            String x = intent.getStringExtra("notes"+count);
            notesTextOutput.setText(x);
        } else {
            for (int i = 0; i < count; i++) {
                String x = intent.getStringExtra("notes"+count);
                notesTextOutput.append(x);
            }
        }
    }
}