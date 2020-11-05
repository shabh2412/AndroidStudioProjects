package com.rishabhpanesar.bluetoothwifi;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button onButton, offButton;
    RadioGroup option;
    RadioButton bluetooth, wifi;
    String bOrW = "";
    final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButton = findViewById(R.id.onButton);
        offButton = findViewById(R.id.offButton);
        option = findViewById(R.id.option);
        bluetooth = findViewById(R.id.bluetooth);
        wifi = findViewById(R.id.wifi);

        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bOrW = "BT";
            }
        });

        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bOrW = "WIFI";
            }
        });

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (bOrW) {
                    case "BT":
                        if(bAdapter == null)
                        {
                            Snackbar.make(v,"Bluetooth Not Supported", Snackbar.LENGTH_SHORT).show();
                        }
                        else{
                            if(!bAdapter.isEnabled()){
                                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                                Snackbar.make(v,"Bluetooth Turned ON",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case "WIFI":
                        Intent wifiIntent= new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivityForResult(wifiIntent,1);
                        Snackbar.make(v,"Wi-fi Turned ON",Snackbar.LENGTH_SHORT).show();
                        break;
                    default:
                        Snackbar.make(v,"No option selected",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (bOrW) {
                    case "BT":
                        if(bAdapter == null)
                        {
                            Snackbar.make(v,"Bluetooth Not Supported", Snackbar.LENGTH_SHORT).show();
                        }
                        else{
                            if(!bAdapter.isEnabled()){
                                bAdapter.disable();
                                Snackbar.make(v,"Bluetooth Turned OFF",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case "WIFI":
                        WifiManager wmgr = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wmgr.setWifiEnabled(false);
                        Snackbar.make(v,"Wi-fi Turned OFF",Snackbar.LENGTH_SHORT).show();
                        break;
                    default:
                        Snackbar.make(v,"No option selected",Snackbar.LENGTH_SHORT).show();
                }


            }
        });
    }
}