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
        setContentView(R.layout.activity_playlist_myplaylist);
        getSupportActionBar().hide();
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
    public void rocketjumpToPlaySong (View view){
        switch (view.getId()) {
            case R.id.btn_playlist_song1:
                // do something
                Intent i = new Intent(PlaylistActivity.this, PlaySongActivity.class);
                i.putExtra("songname1", "雾里");
                startActivity(i);
                break;
            case R.id.btn_playlist_song2:
                Intent j = new Intent(PlaylistActivity.this, PlaySongActivity.class);
                j.putExtra("songname1", "病态");
                startActivity(j);
                break;
            case R.id.btn_playlist_song3:
                Intent k = new Intent(PlaylistActivity.this, PlaySongActivity.class);
                k.putExtra("songname1", "星辰大海");
                startActivity(k);
                break;
        }
    }
}
