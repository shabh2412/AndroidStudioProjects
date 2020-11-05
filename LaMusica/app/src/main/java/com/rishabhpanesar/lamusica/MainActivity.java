package com.rishabhpanesar.lamusica;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnPlay;
    Slider volumeBar;
    TextView elapsedTime, remTime;
    MediaPlayer player;
    int totalTime;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btnPlay);
        seekBar = findViewById(R.id.seekBar);
        volumeBar = findViewById(R.id.volumeBar);
        elapsedTime = findViewById(R.id.elapsedTime);
        remTime = findViewById(R.id.remTima);

        // Media Player
        player = MediaPlayer.create(this, R.raw.malang);
        player.setLooping(true);
        player.seekTo(0);
        player.setVolume(0.5f, 0.5f);
        totalTime = player.getDuration();

        //Slider
        seekBar.setMax(totalTime);

        seekBar.setEnabled(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //volume bar
        volumeBar.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                float volNum = value / 100f;
                player.setVolume(volNum, volNum);
                volumeBar.setLabelFormatter(new LabelFormatter() {
                    @NonNull
                    @Override
                    public String getFormattedValue(float value) {
                        int val = (int) value;
                        return String.valueOf(val);
                    }
                });
            }
        });
    }


    private String createTimeLabel(int currentPosition) {
        String timeLabel = "";
        int min = currentPosition / 1000 / 60;
        int sec = currentPosition / 1000 % 60;
        timeLabel = min + "\t:\t";
        if (sec < 10) timeLabel += 0;
        timeLabel += sec;

        return timeLabel;
    }

    public void playSong(View view) {
        if (!player.isPlaying()) {
            seekBar.setEnabled(true);
//            update();
            updateSeekBar();
            player.start();
            btnPlay.setBackgroundResource(R.drawable.stop);
        } else {
            player.pause();
            btnPlay.setBackgroundResource(R.drawable.play);
        }
    }

//    private void update() {
//        Runnable runnable = new Runnable() {
//
//            @Override
//            public void run() {
//                while (player != null && player.isPlaying()) {
//                    System.out.println("Position\t:\t" + player.getCurrentPosition()/1000);
//                    seekBar.setProgress(player.getCurrentPosition());
//                    try {
//                        Thread.sleep(1500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
//    }

    public void updateSeekBar() {

        // Worker thread that will update the seekbar as each song is playing
        Thread t = new Thread() {
            Handler handler = new Handler();

            @Override
            public void run() {

                // Log.d(TAG, "Updating Seekbar.....");
//                int currentPosition = 0;
                int total = (int) player.getDuration();
                seekBar.setMax(total);
                while (player != null
                        && player.getCurrentPosition() < total) {
                    try {

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    } catch (Exception e) {
                        return;
                    }

                    seekBar.setProgress(player
                            .getCurrentPosition());

                    handler.post(new Runnable() {

                        @Override
                        public void run() {

                        }
                    });

                }

            }

        };
        t.start();

//        private String millisecondsToTime(long milliseconds) {
//        long minutes = (milliseconds / 1000) / 60;
//        long seconds = (milliseconds / 1000) % 60;
//        String secondsStr = Long.toString(seconds);
//        String secs;
//        if (secondsStr.length() >= 2) {
//            secs = secondsStr.substring(0, 2);
//        } else {
//            secs = "0" + secondsStr;
//        }
//
//        return minutes + ":" + secs;
    }
}