package com.example.musicstream;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SongItem extends AppCompatActivity {
    ImageButton favouritesicon = findViewById(R.id.songitemOth);
    int isClicked = 0;


    public void travelToSong(View myView) {
        //replace with button stuff
    }

    public void favourite(View myView) {
        if (isClicked == 0) {
            favouritesicon.setImageResource(R.drawable.ic_hearts_filled);
            Toast.makeText(this, "Added to favourites", Toast.LENGTH_SHORT).show();
            isClicked = 1;
        } else if (isClicked == 1){
            favouritesicon.setImageResource(R.drawable.collection_icon);
            Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show();
            isClicked = 0;
        }
    }
    public static void songLists (String[] args){
        ArrayList<String> artists = new ArrayList<>();
        artists.add("Eric Chou");
        artists.add("JJ Lin");

        ArrayList<String> titles = new ArrayList<>();
        titles.add("离开你以后");
        titles.add("dangni");

        ArrayList<Integer> coverimgs = new ArrayList<>();
        coverimgs.add(R.drawable.li_kai_ni_yi_hou);
        coverimgs.add(R.drawable.jj_lin_dang_ni);
    }

}


