package com.rishabhpanesar.mplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.rishabhpanesar.mplayer.databinding.ActivityMainBinding;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String TAG = "MainActivity";
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.seekBar.setProgress(0);
        binding.seekBar.setEnabled(false);
        setListeners();
    }


    private void setListeners() {
        binding.btnPlay.setOnClickListener(v -> {
            if (player == null) {
                binding.btnPlay.setBackgroundResource(R.drawable.stop);
                player = MediaPlayer.create(this, R.raw.malang);
                binding.seekBar.setEnabled(true);
                binding.seekBar.setMax(player.getDuration());
                binding.seekBar.setProgress(0);
                int min = player.getDuration() / 1000 / 60;
                int sec = player.getDuration() / 1000 % 60;
                String duration = min + "\t:\t";
                if (sec < 10) duration += 0;
                duration += sec;
                binding.remTime.setText(duration);
                player.start();
                activateSeekBar();
            } else if (player != null && player.isPlaying()) {
                player.pause();
                binding.btnPlay.setBackgroundResource(R.drawable.play);

            } else {
                player.start();
                activateSeekBar();
                binding.btnPlay.setBackgroundResource(R.drawable.stop);
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (player.isPlaying() && !fromUser) {
                    binding.elapsedTime.setText(timeString(player.getCurrentPosition()));
                    binding.remTime.setText("-" + timeString(player.getDuration() - player.getCurrentPosition()));
                }
                else if (player.isPlaying() && fromUser) {
                    player.seekTo(binding.seekBar.getProgress());
                    binding.elapsedTime.setText(timeString(progress));
                    binding.remTime.setText("-" + timeString(player.getDuration() - progress));
                } else if (!player.isPlaying() && player != null && fromUser) {
                    player.seekTo(binding.seekBar.getProgress());
                    binding.elapsedTime.setText(timeString(progress));
                    binding.remTime.setText("-" + timeString(player.getDuration() - progress));
                    player.pause();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.volumeBar.addOnChangeListener((slider, value, fromUser) -> {
            player.setVolume(value / 100, value / 100);
            slider.setLabelFormatter(value1 -> {
                return String.valueOf((int)value);
            });
        });

    }

    private String timeString(int milliseconds) {
        String time = "";
        milliseconds /= 1000;
        int min = milliseconds / 60;
        int sec = milliseconds % 60;
        time += min + "\t:\t";
        if (sec < 10) time += 0;
        time += sec;
        return time;
    }


//Updates seekbar every second
    private void activateSeekBar() {
        Runnable mRunnable = () -> {
            while (player != null && player.isPlaying()) {
                Log.d(TAG, "setListeners: Position: " + player.getCurrentPosition() / 1000);
                binding.seekBar.setProgress(player.getCurrentPosition());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //Execute SeekBar Update on separate thread
        Thread thread = new Thread(mRunnable);
        thread.start();
    }
}