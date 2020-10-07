package com.rishabhpanesar.lbs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    private TextView latitude;
    private TextView longitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        latitude = (TextView) findViewById(R.id.textlati);
        longitude = (TextView) findViewById(R.id.textlongi);
        Button Choose = (Button) findViewById(R.id.btn);
        final TextView choice = (TextView) findViewById(R.id.choice);
        final CheckBox fineAcc = (CheckBox) findViewById(R.id.fineAccuracy);
        //This class provides access to the system location services. These services allow applications to obtain
        // periodic updates of the device's geographical location,
        // or to be notified when the device enters the proximity of a given geographical location.
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //A class indicating the application criteria for selecting a location provider.
        // Providers may be ordered according to accuracy, power usage
        //Constructs a new Criteria object.
        final Criteria criteria = new Criteria();
        //setAccuracy() Indicates the desired accuracy for latitude and longitude.
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fineAcc.isChecked()) {
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);
                    choice.setText("fine accuracy selected");
                } else {
                    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                    choice.setText("coarse accuracy selected");
                }
            }
        });
        //setCostAllowed (boolean costAllowed) Indicates whether the provider is allowed to incur monetary cost.
        criteria.setCostAllowed(false);
        // get the best provider depending on the criteria
        String provider = locationManager.getBestProvider(criteria, false);
        //getLastKnownLocation(String provider) Gets the last known location from the given provider,
        // or null if there is no last known location.
        Location location = locationManager.getLastKnownLocation(provider);
        MyLocationListener mylistener = new MyLocationListener();
        //Step 3
        locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Initialize the location fields
            latitude.setText("Latitude: "+String.valueOf(location.getLatitude()));
            longitude.setText("Longitude: "+String.valueOf(location.getLongitude()));
            Toast.makeText(MainActivity.this,  "Location changed!",
                    Toast.LENGTH_SHORT).show();
        }

    }}
