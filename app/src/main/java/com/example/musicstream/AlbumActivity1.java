package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AlbumActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets current layout from res.layouts
        setContentView(R.layout.activity_album_jjlin1);
        getSupportActionBar().hide();

    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void jogToHome(View view){startActivity(new Intent(AlbumActivity1.this,MainActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void jogToCollections(View view){startActivity(new Intent(AlbumActivity1.this,CollectionsActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void jogToSettings(View view){startActivity(new Intent(AlbumActivity1.this,SettingsActivity.class));}
    //onClick from Back button to finish current Activity
    public void jog_back (View view){finish();}
    public void jogToPlaySong(View view){
        Intent newIntent = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
        finish();
        startActivity(newIntent);
    }
}
