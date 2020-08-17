package com.rishabhpanesar.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    These are the global variables
    EditText firstNum, secondNum;
    TextView resultText;
    Button addButton, subButton, mulButton, divButton, factButton;
    public void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNum = (EditText) findViewById(R.id.firstNum);
        secondNum = (EditText) findViewById(R.id.secondNum);
        resultText = (TextView) findViewById(R.id.resultText);
        addButton = (Button) findViewById((R.id.addButton));
        subButton = (Button) findViewById((R.id.subButton));
        mulButton = (Button) findViewById((R.id.mulButton));
        divButton = (Button) findViewById((R.id.divButton));
        factButton = (Button) findViewById((R.id.factButton));
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = firstNum.getText().toString();
                float n1 = Float.valueOf(num1);
                String num2 = secondNum.getText().toString();
                float n2 = Float.valueOf(num2);
                float sol = n1 + n2;
                resultText.setText("Result : "+ sol);
                hideKeybaord(view);
            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = firstNum.getText().toString();
                float n1 = Float.valueOf(num1);
                String num2 = secondNum.getText().toString();
                float n2 = Float.valueOf(num2);
                float sol = n1 - n2;
                resultText.setText("Result : "+ sol);
                hideKeybaord(view);
            }
        });
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = firstNum.getText().toString();
                float n1 = Float.valueOf(num1);
                String num2 = secondNum.getText().toString();
                float n2 = Float.valueOf(num2);
                float sol = n1 * n2;
                resultText.setText("Result : "+ sol);
                hideKeybaord(view);
            }
        });
        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = firstNum.getText().toString();
                float n1 = Float.valueOf(num1);
                String num2 = secondNum.getText().toString();
                float n2 = Float.valueOf(num2);
                float sol = n1 / n2;
                resultText.setText("Result : "+ sol);
                hideKeybaord(view);
            }
        });
        factButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num1 = firstNum.getText().toString();
                float n1 = Float.valueOf(num1);
                float sol = fact(n1);
                resultText.setText("Result : "+ sol);
                hideKeybaord(view);
            }
            protected float fact(float x){
                if(x==0){
                    return 1;
                } else {
                     return x * fact(x-1);
                }
            }
        });
    }

}