package com.rishabhpanesar.intentdatacommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText nameET = findViewById(R.id.nameET);
        final EditText phoneET = findViewById(R.id.phoneET);
        final EditText emailET = findViewById(R.id.emailET);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, number, email;
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                name = nameET.getText().toString();
                number = phoneET.getText().toString();
                email = emailET.getText().toString();
                intent.putExtra("NAME", name);
                intent.putExtra("PHONE", number);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });
    }
}