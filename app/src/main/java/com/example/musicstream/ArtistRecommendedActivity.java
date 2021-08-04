package com.example.musicstream;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistRecommendedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_main);
        getSupportActionBar().hide();
    }
}
