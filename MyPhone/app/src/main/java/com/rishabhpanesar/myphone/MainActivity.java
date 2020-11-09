package com.rishabhpanesar.myphone;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.rishabhpanesar.myphone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final static String TELEPHONY_SERVICE = Context.TELEPHONY_SERVICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);
    }

    public void getDetails(View view) {
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
        binding.androidId.setText(Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID));
        String networkCountryISO = telephonyManager.getNetworkCountryIso();
        String SIMCountryCode = telephonyManager.getSimCountryIso();
        int phoneType = telephonyManager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                binding.phoneType.setText("CDMA");
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                binding.phoneType.setText("GSM");
                break;
            case (TelephonyManager.PHONE_TYPE_SIP):
                binding.phoneType.setText("SIP");
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                binding.phoneType.setText("None");
                break;

        }
        binding.roaming.setText(telephonyManager.isNetworkRoaming() ? "Roaming" : "Not Roaming");
        binding.networkCountryIso.setText(networkCountryISO);
        binding.simCountryCode.setText(SIMCountryCode);
//        binding.textView1.setText(info);
//        binding.textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}