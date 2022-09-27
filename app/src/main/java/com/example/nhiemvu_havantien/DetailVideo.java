package com.example.nhiemvu_havantien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailVideo extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String API_KEY = "AIzaSyAUedqdLm23G97TGwcpnXh3kSDk2Ts1PSI";
    TextView titledetail, desc;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        Intent get = getIntent();

        String des = get.getStringExtra("desc");
        String title = get.getStringExtra("title");
        String video = get.getStringExtra("video");
        back=findViewById(R.id.back);
        titledetail=findViewById(R.id.titledetail);
        desc=findViewById(R.id.desc);
        youTubePlayerView = findViewById(R.id.youtube_view);
        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(video);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(),"that bai",Toast.LENGTH_SHORT).show();
            }
        };
        youTubePlayerView.initialize(API_KEY,listener);

        titledetail.setText(title);
        desc.setText(des);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}