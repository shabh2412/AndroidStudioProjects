//Develop an android application to understand passing data through Intent
package com.rishabhpanesar.passingdataintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText Name, Number;
        Button saveBtn;
        Name = findViewById(R.id.Name);
        Number = findViewById(R.id.Number);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String number = Number.getText().toString();

                Intent data2Activity = new Intent(MainActivity.this, SaveContactActivity.class);
                data2Activity.putExtra("NAME", name);
                data2Activity.putExtra("NUMBER", number);
                startActivity(data2Activity);

            }
        });
    }
}