package com.rishabhpanesar.asms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.rishabhpanesar.asms.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.SEND_SMS
        }, PackageManager.PERMISSION_GRANTED);

        binding.sendSms.setEnabled(checkPermission(Manifest.permission.SEND_SMS));
        binding.LLayout.setOnClickListener(v -> {
            hideKeyboard();
        });

        binding.sendSms.setOnClickListener(v -> {
            hideKeyboard();
            String phone, msg;
            phone = binding.receiverNumber.getText().toString();
            msg = binding.smsText.getText().toString();
            if (msg.equals("") || msg.length() == 0 || phone.equals("") || phone.length() < 10) {
                Snackbar.make(v, "Please enter correct number\n Message cannot be empty", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (checkPermission(Manifest.permission.SEND_SMS)) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, msg, null, null);
                Snackbar.make(v, "Message Sent!", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(v, "Permission Not granted", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void hideKeyboard() {
        binding.receiverNumber.clearFocus();
        binding.smsText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.LLayout.getWindowToken(), 0);
    }

    private boolean isEmpty(TextInputEditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private boolean checkPermission(String sendSms) {
        int check = checkSelfPermission(sendSms);
        return check == 0;
    }
}