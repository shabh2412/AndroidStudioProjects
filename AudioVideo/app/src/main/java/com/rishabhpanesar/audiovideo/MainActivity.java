package com.rishabhpanesar.audiovideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.rishabhpanesar.audiovideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isPlaying = false;
    private boolean isPlayingVideo = false;
    private MediaPlayer player;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        player = MediaPlayer.create(this, R.raw.bensound_buddy);
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.playSong.setOnClickListener(v -> {
            if (isPlaying) {
                player.pause();
                isPlaying = false;
                binding.playSong.setText("Play");
                return;
            }
            player.start();
            isPlaying = true;
            binding.playSong.setText("Pause");
        });
        VideoView videoView = binding.videoView;

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.jellyfish));
        videoView.seekTo(1000);
        binding.playVideo.setOnClickListener(v -> {
            if (isPlayingVideo) {
                binding.videoView.pause();
                binding.playVideo.setText("Play");
                isPlayingVideo = false;
                return;
            }
            videoView.start();
            binding.playVideo.setText("Pause");
            isPlayingVideo = true;
//            mediaController = new MediaController(this);
//            mediaController.setAnchorView(videoView);
//            videoView.setMediaController(mediaController);
        });
        videoView.setOnPreparedListener(mp -> {
            mp.setOnVideoSizeChangedListener((mp1, width, height) -> {
                mediaController = new MediaController(MainActivity.this);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
            });
        });
    }
}