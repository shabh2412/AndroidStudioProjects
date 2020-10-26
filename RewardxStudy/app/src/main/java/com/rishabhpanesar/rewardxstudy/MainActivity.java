package com.rishabhpanesar.rewardxstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button mineButton;
    public EditText countdown_text;
    public EditText countdown_secs;
    public TextView timerCountText;
    public TextView colon;
    public long timeLeftInMilliSeconds = 600000;
    private Boolean timerRunning = false;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mineButton = findViewById(R.id.mineButton);
        mineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdown_text = findViewById(R.id.countdown_mins);
                countdown_secs = findViewById(R.id.countdown_secs);
                timerCountText = findViewById(R.id.timerCountText);
                colon = findViewById(R.id.colon);
                String userInp = countdown_text.getText().toString();
                timeLeftInMilliSeconds = Integer.parseInt(userInp) * 600000;
                startStop();
            }
        });
    }
    public void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerRunning = true;
        mineButton.setText("Cancel");
        countdown_text.setVisibility(View.INVISIBLE);
        countdown_secs.setVisibility(View.INVISIBLE);
        colon.setVisibility(View.INVISIBLE);
        timerCountText.setVisibility(View.VISIBLE);
    }

    public void updateTimer() {
        int minutes = (int)timeLeftInMilliSeconds / 600000;
        int seconds = (int) timeLeftInMilliSeconds % 60000 / 1000;
        String timeLeft;
        timeLeft = "" + minutes;
        timeLeft += ":";
        if(seconds<10) timeLeft += "0";
        timeLeft += "" + seconds;
        timerCountText.setText(timeLeft);
    }

    public void stopTimer() {
        mineButton.setText("Focus & Mine Coins");
        countDownTimer.cancel();
        timerRunning = false;
        timerCountText.setVisibility(View.GONE);
        countdown_text.setVisibility(View.VISIBLE);
        countdown_secs.setVisibility(View.VISIBLE);
        colon.setVisibility(View.VISIBLE);
    }



}