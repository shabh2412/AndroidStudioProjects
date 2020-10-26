package com.rishabhpanesar.databasecreation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DbOutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_output);
        Intent intent = getIntent();
        ArrayList<String> names = intent.getStringArrayListExtra("names");
        ListView db_list = findViewById(R.id.db_list);
        assert names != null;
        ArrayAdapter<String> adapter = new ArrayAdapter(DbOutputActivity.this,android.R.layout.simple_list_item_1,names);
        db_list.setAdapter(adapter);
    }
}