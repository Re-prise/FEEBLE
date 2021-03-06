package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CollectionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sets current layout from res.layouts
        setContentView(R.layout.activity_collections_main);
        getSupportActionBar().hide();
    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void travelToHome(View view){startActivity(new Intent(CollectionsActivity.this,MainActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void travelToSettings(View view){startActivity(new Intent(CollectionsActivity.this,SettingsActivity.class));}
    //onClick from Back button to finish current Activity
    public void backk (View view){finish();}
    public void travelToPlaylist(View view){startActivity(new Intent(CollectionsActivity.this, PlaylistActivity3.class));}
    public void travelToMyPlaylist(View view){startActivity(new Intent(CollectionsActivity.this, PlaylistActivity.class));}
    public void travelToSong(View view){startActivity((new Intent(CollectionsActivity.this, PlaySongActivity6.class)));}
}
