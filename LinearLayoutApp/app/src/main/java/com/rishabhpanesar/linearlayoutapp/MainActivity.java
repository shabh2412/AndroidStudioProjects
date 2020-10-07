package com.rishabhpanesar.linearlayoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText receiverName, receiverEmail, userMessage;
    TextView textView;
    public void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    public String getText(EditText editText) {
        return editText.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiverName = (EditText) findViewById(R.id.receiverName);
        receiverEmail = (EditText) findViewById(R.id.receiverEmail);
        userMessage = (EditText) findViewById(R.id.userMessage);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getText(receiverName);
                String email = getText(receiverEmail);
                String Message = getText(userMessage);
                if(name.equals("") || email.equals("") || Message.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else {
                    String output = "Message Details:\nTo\t:\t" + name + "\nE-Mail ID\t:\t" + email + "\nMessage\t:\t" + Message;
                    textView.setText(output);
                    Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
                    textView.setTextColor(Color.WHITE);
                }
            }
        });
    }
}