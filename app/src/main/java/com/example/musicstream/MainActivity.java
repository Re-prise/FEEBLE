package com.example.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicstream.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToPlaySong(View myView) {
        //Tester//Log.d("temasek","The id of the pressed Image Button is : WORKING");
        //String resourceId = getResources().getResourceEntryName(myView.getId());
        //Tester//Log.d("temasek","The id of the pressed Image Button is :" + resourceId);
        Intent newIntent = new Intent(MainActivity.this, PlaySongActivity2.class);
        finish();
        startActivity(newIntent);
        //startActivity(new Intent(MainActivity.this, PlaySongActivity2.class));
        setContentView(R.layout.activity_play_song);
    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
    }
    public void goToSettings(View view){startActivity(new Intent(MainActivity.this, SettingsActivity.class)); }

    public void goToArtist(View view) {
        Log.d("HUBS","Working");
        startActivity(new Intent(MainActivity.this, ArtistRecommendedActivity.class));
    }
    public void goToArtist2(View view){
        startActivity(new Intent(MainActivity.this, ArtistRecommendedActivity2.class));
    }
    public void goToCollections(View view) {
        startActivity(new Intent(MainActivity.this, CollectionsActivity.class));
    }
    public void goToPlaylist(View view){startActivity(new Intent(MainActivity.this, PlaylistActivity.class));}
    public void goToPlaylistst(View view){startActivity(new Intent(MainActivity.this, PlaylistActivity3.class));}
}
//       int isClicked = 0;
//    public void favourite(View View) {
//        if (isClicked == 0) {
//            favouritesicon.setImageResource(R.drawable.ic_hearts_filled);
//            Toast.makeText(this, "Added to favourites", Toast.LENGTH_SHORT).show();
//            isClicked = 1;
//        } else if (isClicked == 1){
//            favouritesicon.setImageResource(R.drawable.collection_icon);
//            Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show();
//            isClicked = 0;
//        }


