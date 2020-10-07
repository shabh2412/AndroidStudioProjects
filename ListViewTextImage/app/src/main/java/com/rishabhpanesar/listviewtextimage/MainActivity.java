package com.rishabhpanesar.listviewtextimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //    Creating string arrays to store info about cars
    String[] carNames = {"Aventador", "Mustang GT", "Ferrari F12", "Nissan GT-R", "911 Carrera"};
    String[] carDescription = {"Supercar by Lamborghini", "Ford Mustang GT", "SUV Beast", "Super Coupe Car", "Legendary Carrera"};
    //    Creating integer array to store the id of each image
    Integer[] carImages = {R.drawable.lamborghini, R.drawable.mustang, R.drawable.ferrari, R.drawable.gtr, R.drawable.carrera,};

    //     Creating ListView Variable
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        CustomListView customListView = new CustomListView(this, carNames, carDescription, carImages);
        listView.setAdapter(customListView);

    }
}