package com.rishabhpanesar.explicitintentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText number, searchTerm, location;
    Button dialBtn, searchBtn, locateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        dialBtn = findViewById(R.id.dialBtn);
        dialBtn.setOnClickListener(this);

        searchTerm = findViewById(R.id.searchTerm);
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);

        locateBtn = findViewById(R.id.locateBtn);
        location = findViewById(R.id.location);
        locateBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialBtn:
                String num = number.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
                break;
            case R.id.searchBtn:
                String search = searchTerm.getText().toString();
                String searchString = "https://www.google.com/search?q=" + search;
                Intent chrome = new Intent(Intent.ACTION_VIEW, Uri.parse(searchString));
                startActivity(chrome);
                break;
            case R.id.locateBtn:
                String url = "https://www.google.com/maps/search/?api=1&query=" + location.getText().toString();
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent1);
        }
    }
}