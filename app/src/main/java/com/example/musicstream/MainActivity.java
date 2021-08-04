package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playlist = findViewById(R.id.playlist_button);
        Button songRecommend = findViewById(R.id.recommended_song);
        Button artistRecommend = findViewById(R.id.artist_recommeded_button);
        ImageButton toolbarFavourites = findViewById(R.id.Toolbar_button_collection);
        ImageButton toolbarHome = findViewById(R.id.Toolbar_button_home);
        ImageButton toolbarSettings = findViewById(R.id.Toolbar_button_settings);}

    public void goToPlaySong(View myView) {
        Log.d("temasek","The id of the pressed Image Button is : WORKING");
        //String resourceId = getResources().getResourceEntryName(myView.getId());
       // Log.d("temasek","The id of the pressed Image Button is :" + resourceId);
        startActivity(new Intent(MainActivity.this, PlaySongActivity2.class));
        setContentView(R.layout.activity_play_song);}

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main); }

    public void goToSettings(View view){startActivity(new Intent(MainActivity.this, SettingsActivity.class));}
    public void goToArtist(View view){startActivity(new Intent(MainActivity.this, ArtistRecommendedActivity.class));}
    public void goToCollections(View view){startActivity(new Intent(MainActivity.this,CollectionsActivity.class));}
}

