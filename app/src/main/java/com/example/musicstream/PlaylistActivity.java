package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PlaylistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets current layout from res.layouts
        setContentView(R.layout.activity_playlist_main);
    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void rocketjumpToHome(View view){startActivity(new Intent(PlaylistActivity.this,MainActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void rocketjumpToCollections(View view){startActivity(new Intent(PlaylistActivity.this,CollectionsActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void rocketjumpToSettings(View view){startActivity(new Intent(PlaylistActivity.this,SettingsActivity.class));}
    //onClick from Back button to finish current Activity
    public void rocketjump_back (View view){finish();}
}
