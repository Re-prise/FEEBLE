package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistRecommendedActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets current layout from res.layouts
        setContentView(R.layout.activity_artist_main2);
        getSupportActionBar().hide();

    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void walkToHome(View view){startActivity(new Intent(ArtistRecommendedActivity2.this,MainActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void walkToCollections(View view){startActivity(new Intent(ArtistRecommendedActivity2.this,CollectionsActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void walkToSettings(View view){startActivity(new Intent(ArtistRecommendedActivity2.this,SettingsActivity.class));}
    //onClick from Back button to finish current Activity
    public void bacKkK (View view){finish();}
    public void walkToPlaylist(View view){startActivity(new Intent(ArtistRecommendedActivity2.this,PlaySongActivity4.class));}
}
