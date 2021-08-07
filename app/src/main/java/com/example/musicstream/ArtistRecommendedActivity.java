package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistRecommendedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets current layout from res.layouts
        setContentView(R.layout.activity_artist_main);
        getSupportActionBar().hide();

    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void globallyTravelToHome(View view){startActivity(new Intent(ArtistRecommendedActivity.this,MainActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void globallyTravelToCollections(View view){startActivity(new Intent(ArtistRecommendedActivity.this,CollectionsActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void globallyTravelToSettings(View view){startActivity(new Intent(ArtistRecommendedActivity.this,SettingsActivity.class));}
    //onClick from Back button to finish current Activity
    public void bacKk (View view){finish();}
    public void globallyTravelToPlaylist1(View view){
        Intent newIntent = new Intent(ArtistRecommendedActivity.this, AlbumActivity1.class);
        finish();
        startActivity(newIntent);
    }
}
