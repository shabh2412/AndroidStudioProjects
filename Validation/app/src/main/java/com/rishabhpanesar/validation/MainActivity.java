package com.rishabhpanesar.validation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnSub);
        editText = findViewById(R.id.et1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name = editText.getText().toString();
                if (Name.length() == 0) {
                    editText.requestFocus();
                    editText.setError("Field cannot be empty");
                } else if (!Name.matches("[a-zA-Z]+")){
                    editText.requestFocus();
                    editText.setError("Alphabetic chars only");
                } else {
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}