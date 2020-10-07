package com.rishabhpanesar.texteditorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button submitBtn;
    RadioGroup options;
    RadioButton concat, wordCount, trim;
    EditText msg1, msg2;
    TextView outPutText;
    Boolean concatenate = false, countWord = false, Trim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        options = (RadioGroup) findViewById(R.id.options);
        concat = (RadioButton) findViewById(R.id.concat);
        wordCount = (RadioButton) findViewById(R.id.wordCount);
        trim = (RadioButton) findViewById(R.id.trim);
        msg1 = (EditText) findViewById(R.id.msg1);
        msg2 = (EditText) findViewById(R.id.msg2);
        outPutText = (TextView) findViewById(R.id.outPutText);
        concat.setOnClickListener(this);
        wordCount.setOnClickListener(this);
        trim.setOnClickListener(this);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (concatenate) {
                    Concatenate(view);
                } else if (Trim) {
                    trimFunction(view);
                } else if (countWord) {
                    getCountWord(view);
                } else {
                    Toast.makeText(getApplicationContext(), "Choose an Option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.concat:
                concatenate = true;
                countWord = false;
                Trim = false;
                break;
            case R.id.wordCount:
                concatenate = false;
                countWord = true;
                Trim = false;
                break;
            case R.id.trim:
                concatenate = false;
                countWord = false;
                Trim = true;
        }
    }

    public void trimFunction(View view) {
        String m1 = getMsg(msg1);
        String m2 = getMsg(msg2);
        String outPut = (m1+m2).trim();
        outPutText.setText(outPut);
    }

    public void Concatenate(View view) {
        String m1 = getMsg(msg1);
        String m2 = getMsg(msg2);
        String outPut = m1+m2;
        outPutText.setText(outPut);
    }

    public void getCountWord(View view) {
        String m1, m2, output;
        m1 = getMsg(msg1);
        m2 = getMsg(msg2);
        output = m1 +" "+ m2;
        String total = Integer.toString(countWordsUsingSplit(output));
        outPutText.setText(total);
    }

    public String getMsg(EditText editText) {
        return editText.getText().toString();
    }

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }
}