package com.rishabhpanesar.databaseproject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class StudentDetails extends AppCompatActivity {
    ListView listView;
    DbHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        myDb = new DbHelper(this);

        listView = findViewById(R.id.listView);
        loadData();
    }

    public void loadData() {
        ArrayList<Students> list = myDb.getData();
        if (list.isEmpty()) {
            Snackbar snackbar = Snackbar.make(listView, "No Data To Display!", Snackbar.LENGTH_LONG);
            snackbar.setTextColor(Color.BLACK);
            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            snackbar.show();
        } else {
            StudentsAdapter adapter = new StudentsAdapter(this, list);
            listView.setAdapter(adapter);
        }
    }
}