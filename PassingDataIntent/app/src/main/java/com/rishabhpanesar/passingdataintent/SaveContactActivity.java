package com.rishabhpanesar.passingdataintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SaveContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_contact);

        Intent dataFromActivity = getIntent();
        final String name = dataFromActivity.getStringExtra("NAME");
        final String number = dataFromActivity.getStringExtra("NUMBER");
        TextView contactDetails = findViewById(R.id.contactDetails);
        contactDetails.setText("NAME\t:\t" + name + "\nNUMBER\t:\t" + number);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                contactIntent
                        .putExtra(ContactsContract.Intents.Insert.NAME, name)
                        .putExtra(ContactsContract.Intents.Insert.PHONE, number);

                startActivityForResult(contactIntent, 1);
            }
        });
    }
}