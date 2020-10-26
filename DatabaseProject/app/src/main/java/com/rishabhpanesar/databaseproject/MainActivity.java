package com.rishabhpanesar.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    DbHelper myDb;
    Button btnAdd, btnRetrieve, btnUpdate;
    EditText studentName, branchName, rollTxt, scoreTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = findViewById(R.id.btnAdd);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        btnUpdate = findViewById(R.id.btnUpdate);


        studentName = findViewById(R.id.studentName);
        branchName = findViewById(R.id.branchName);
        rollTxt = findViewById(R.id.rollTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        myDb = new DbHelper(this);
//        Toast.makeText(this, myDb.getDatabaseName() + " Created", Toast.LENGTH_SHORT).show();
        Add();
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                Intent intent = new Intent(MainActivity.this, StudentDetails.class);
                startActivity(intent);
            }
        });

        updateData();
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    public void Add() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = myDb.addData(rollTxt.getText().toString(), studentName.getText().toString(), branchName.getText().toString(), scoreTxt.getText().toString());
                Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                if (result) {
                    snackbar.setText("Success");
                    clearTxt();
                    snackbar.show();
//                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else if (myDb.checkDataOccurrence(rollTxt.getText().toString())) {
                    snackbar.setText("Student Already Exists");
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    clearFocus();
                    snackbar.show();
                } else {
                    snackbar.setText("Some Error Occurred");
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    clearFocus();
                    snackbar.show();
                }

                hideKeyboard(v);
            }
        });

    }

    public void clearTxt() {
        rollTxt.setText("");
        studentName.setText("");
        branchName.setText("");
        scoreTxt.setText("");
        clearFocus();
    }

    private void clearFocus() {
        scoreTxt.clearFocus();
        branchName.clearFocus();
        studentName.clearFocus();
        rollTxt.clearFocus();
    }

    public void updateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll, name, branch, marks;
                roll = rollTxt.getText().toString();
                name = studentName.getText().toString();
                branch = branchName.getText().toString();
                marks = scoreTxt.getText().toString();
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                boolean status = dbHelper.update(roll, name, branch, marks);
                Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                if (status) {
                    snackbar.setText("Updated Successfully!");
                    clearTxt();
                } else {
                    snackbar.setText("Some Error Occurred");
                    clearFocus();
                }
                snackbar.show();
                hideKeyboard(v);
            }
        });
    }



}