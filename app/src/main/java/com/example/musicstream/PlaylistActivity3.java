package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PlaylistActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets current layout from res.layouts
        setContentView(R.layout.activity_playlist_playlist);
        getSupportActionBar().hide();
    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void flyToHome(View view){startActivity(new Intent(PlaylistActivity3.this,MainActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void flyToCollections(View view){startActivity(new Intent(PlaylistActivity3.this,CollectionsActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void flyToSettings(View view){startActivity(new Intent(PlaylistActivity3.this,SettingsActivity.class));}
    //onClick from Back button to finish current Activity
    public void fly_back (View view){finish();}
    public void flyToPlaySong (View view){startActivity(new Intent(PlaylistActivity3.this,PlaySongActivity3.class)); }
}
