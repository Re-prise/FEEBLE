package com.example.musicstream;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SongCollection extends AppCompatActivity {
    Song songs[] = new Song[3];

    public SongCollection() {
        Song dangNi = new Song(1,
                "当你",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/0220451d271dfcf8a3b9a9ac79565ad530c24631?cid=2afe87a64b0042dabf51f37318616965",
                4.15,
                R.drawable.jj_lin_dang_ni);

        Song liKaiNiYiHou = new Song(2,
                "离开你以后",
                "Eric Chou",
                "https://p.scdn.co/mp3-preview/a17b76039f0d27816e1efb42b61bc6cf510069ec?cid=2afe87a64b0042dabf51f37318616965",
                5.16,
                R.drawable.li_kai_ni_yi_hou);

        Song baige = new Song(3,
                "白鸽",
                "你的上好佳",
                "https://p.scdn.co/mp3-preview/a3a056edce4f0fdace784afc42e3ffd6e1b77816?cid=2afe87a64b0042dabf51f37318616965",
                3.47,
                R.drawable.baige_cover);
        //Add Songs When Needed
    songs[0] = dangNi;
    songs[1] = liKaiNiYiHou;
    songs[2] = baige;
    //Fill Array number
    }
    public Song getCurrentSong(int currentSongId) {
        return songs[currentSongId];
    }

    public int SearchSongById(int id){
        for(int index=0; index < songs.length; index++){
            Song tempsong = songs[index];
            if(tempsong.getId() == id){
                return index;
            }
        }
        return -1;
    }
    public void sendDataToActivity(int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());
        SongCollection CAI = new SongCollection();
        int currentArrayIndex = CAI.SearchSongById(Integer.parseInt(resourceId));
        Log.d("temasek","The index in the array for this song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }
    public int getNextSong(int currentSongIndex) {
        Log.d("temasek","The index in the arris :" + songs.length);
        if (currentSongIndex >= songs.length-1) {
            return currentSongIndex;
        }
        else {
            return currentSongIndex + 1;
        }
    }
    public int getPrevSong(int currentSongIndex) {
        if(currentSongIndex <=0){ return currentSongIndex; }
        else { return currentSongIndex-1; }
    }
}
