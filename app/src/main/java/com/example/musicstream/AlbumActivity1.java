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
        //Intent newIntent = new Intent(AlbumActivity1.this, PlaySongActivity6.class);
        switch (view.getId()) {
            case R.id.btn_playlist_song1:
                // do something
                Intent i = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                i.putExtra("songname5", "她说");
                startActivity(i);
                break;
            case R.id.btn_playlist_song2:
                Intent j = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                j.putExtra("songname5", "爱笑的眼睛");
                startActivity(j);
                break;
            case R.id.btn_playlist_song3:
                // i'm lazy, do nothing
                Intent k = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                k.putExtra("songname5", "只对你有感觉");
                startActivity(k);
                break;
            case R.id.btn_playlist_song:
                // i'm lazy, do nothing
                Intent l = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                l.putExtra("songname5", "当你");
                startActivity(l);
                break;
            case R.id.btn_playlist_song5:
                // i'm lazy, do nothing
                Intent m = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                m.putExtra("songname5", "一眼万年");
                startActivity(m);
                break;
            case R.id.btn_playlist_song4:
                // i'm lazy, do nothing
                Intent n = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                n.putExtra("songname5", "保护色");
                startActivity(n);
                break;
            case R.id.btn_playlist_song6:
                // i'm lazy, do nothing
                Intent o = new Intent(AlbumActivity1.this, PlaySongActivity5.class);
                o.putExtra("songname5", "握不紧的他");
                startActivity(o);
                break;
        }
    }
}
