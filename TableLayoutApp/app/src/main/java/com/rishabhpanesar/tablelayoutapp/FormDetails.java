package com.rishabhpanesar.tablelayoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FormDetails extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_form);
        Intent data = new Intent();
        Bundle x = data.getExtras();
        Toast.makeText(getApplicationContext(), x.toString(), Toast.LENGTH_SHORT).show();
    }
}