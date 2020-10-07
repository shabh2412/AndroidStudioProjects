package com.rishabhpanesar.intentdatacommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.Manifest.permission.CALL_PHONE;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final Intent intent = getIntent();
        final String name = intent.getStringExtra("NAME");
        final String phone = intent.getStringExtra("PHONE");
        String email = intent.getStringExtra("EMAIL");
        TextView output = findViewById(R.id.output);
        output.setText("NAME : " + name + "\nPHONE : " + phone + "\nEMAIL : " + email);

        Button call = findViewById(R.id.callButton);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                System.out.println(phone);
                intent1.setAction(Intent.ACTION_CALL);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent1);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });
    }
}