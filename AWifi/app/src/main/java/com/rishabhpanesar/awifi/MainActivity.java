package com.rishabhpanesar.awifi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.material.snackbar.Snackbar;
import com.rishabhpanesar.awifi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.INTERNET
        }, PackageManager.PERMISSION_GRANTED);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        binding.onWifi.setOnClickListener(v -> {
            Intent wifiOn = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(wifiOn, 1);
//            wifiManager.setWifiEnabled(true);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager.isWifiEnabled()) {
                Snackbar.make(v, "Wifi On", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(v, "Some Error Occured", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.offWifi.setOnClickListener(v -> {
            Intent wifiOff = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(wifiOff, WifiManager.WIFI_STATE_DISABLED);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//            wifiManager.setWifiEnabled(false);
            if (!wifiManager.isWifiEnabled()) {
                Snackbar.make(v, "Wifi Off", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(v, "Some Error Occured", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}