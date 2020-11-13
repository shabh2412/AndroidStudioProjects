package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class NotesWindow extends AppCompatActivity {

    TextView notesTextOutput;
    ListView listItems;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_window);
        Intent intent = getIntent();
        notesTextOutput = findViewById(R.id.notesTextOutput);
        listItems = findViewById(R.id.listItems);
        SharedPreferences sharedPreferences = getSharedPreferences("Notes",MODE_PRIVATE);

        int count = intent.getIntExtra("count", 0);
        if (count == 0) {
            String x = sharedPreferences.getString("note0","NA");
            notesTextOutput.setText(x);
            notesTextOutput.setVisibility(View.VISIBLE);
            list.add(x);
        } else {
            for (int i = 1; i < count; i++) {
                String x = sharedPreferences.getString("note"+i,"NA");
                list.add(x);
                notesTextOutput.append("\n"+x);
            }
        }
    }
}