package com.rishabhpanesar.patternchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button factorial, evenOdd, primeNum, armNum;
    TextView outPut;
    EditText editTextNumber;

    public void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        outPut = (TextView) findViewById(R.id.outPut);
        factorial = (Button) findViewById(R.id.factorial);
        evenOdd = (Button) findViewById(R.id.evenOdd);
        primeNum = (Button) findViewById(R.id.primeNum);
        armNum = (Button) findViewById(R.id.armNum);
        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp = editTextNumber.getText().toString();
                hideKeybaord(view);
                if (inp.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter a Number ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(inp);
                String output = String.valueOf(fact(num));
                outPut.setText("Factorial of "+num+" = "+output);
                outPut.setTextColor(Color.GREEN);
            }
            protected float fact(int x){
                if(x==0){
                    return 1;
                } else {
                    return x * fact(x-1);
                }
            }
        });
        evenOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp = editTextNumber.getText().toString();
                hideKeybaord(view);
                if (inp.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter a Number ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(inp);
                if(num%2 == 0){
                    outPut.setText(num+" is Even");
                    outPut.setTextColor(Color.GREEN);
                } else{
                    outPut.setText(num+" is Odd ");
                    outPut.setTextColor(Color.RED);
                }
            }
        });
        armNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp = editTextNumber.getText().toString();
                hideKeybaord(view);
                if (inp.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter a Number ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int length = inp.length();
                int[] num = new int[length];
                double sol = 0.0;
                for (int i = 0; i < length; i++) {
                    char ch = inp.charAt(i);
                    String st = String.valueOf(ch);
                    num[i] = Integer.parseInt(st);
                    System.out.println(num[i]);
                    sol = Math.pow(num[i],3) + sol;
                }
                int sum = (int) sol;
                if(sum == Integer.valueOf(inp)){
                    outPut.setText(inp+" is an Armstrong Number");
                    outPut.setTextColor(Color.GREEN);
                    return;
                }
                outPut.setTextColor(Color.RED);
                outPut.setText(inp +" is not an Armstrong Number");
            }
        });

        primeNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp = editTextNumber.getText().toString();
                hideKeybaord(view);
                if (inp.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter a Number ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(inp);
                for (int i = num-1; i > 1; i--) {
                    System.out.println(num%i);
                    if(num%i == 0){
                        outPut.setTextColor(Color.RED);
                        outPut.setText(inp +" is not a Prime Number");
                        return;
                    }
                }
                outPut.setText(inp+" is a Prime Number");
                outPut.setTextColor(Color.GREEN);
            }
        });
    }
}