package com.rishabhpanesar.lbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.Criteria;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Button locate;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    String lati = "";
    String longi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locate = findViewById(R.id.locateBtn);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                try {
                    if (ActivityCompat.checkSelfPermission(context, mPermission) != REQUEST_CODE_PERMISSION) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
//                this block of code will check for the permission everytime the user opens the app
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    lati = String.valueOf(location.getLatitude());
                    longi = String.valueOf(location.getLongitude());
                }
                if(!lati.equals("") && !longi.equals("")){
                    Toast.makeText(getApplicationContext(), "Latitude\t:\t" + lati + "\nLongitude\t:\t" + longi, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
