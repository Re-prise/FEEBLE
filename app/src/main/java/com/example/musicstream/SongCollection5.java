package com.example.musicstream;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SongCollection5 extends AppCompatActivity {
    Song albumplaylistplaylistsong[] = new Song[5];
    public SongCollection5() {
        Song tashuo = new Song(1,
                "她说",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/c2b951a9e420f3aa614ffb69a299850c53e1f031?cid=2afe87a64b0042dabf51f37318616965",
                5.31,
                R.drawable.artist_cover_jj);
        Song aixiaodeyanjing = new Song(2,
                "爱笑的眼睛",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/1097b7e92d038493659e7e5466d5970757c263a5?cid=2afe87a64b0042dabf51f37318616965",
                4.19,
                R.drawable.artist_cover_jj);
        Song zhiduiniyouganjue = new Song(3,
                "只对你有感觉",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/7971cbae63412fee6a34e0c268e031a71475251f?cid=2afe87a64b0042dabf51f37318616965",
                4.41,
                R.drawable.artist_cover_jj);
        Song dangni = new Song(4,
                "当你",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/0220451d271dfcf8a3b9a9ac79565ad530c24631?cid=2afe87a64b0042dabf51f37318616965",
                4.15,
                R.drawable.artist_cover_jj);
        Song yiyanwannian = new Song(5,
                "一眼万年",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/eac11ffd37966c1a2a5deca6581314bd99d12be3?cid=2afe87a64b0042dabf51f37318616965",
                4.27,
                R.drawable.artist_cover_jj);
        Song baohuse = new Song(6,
                "保护色",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/5a4fceee194feb14e834c6e6bd18616bb2f1d07f?cid=2afe87a64b0042dabf51f37318616965",
                3.29,
                R.drawable.artist_cover_jj);
        Song wobujindeta = new Song(7,
                "握不紧的他",
                "JJ Lin",
                "https://p.scdn.co/mp3-preview/f8dc7d881b19230e65656c83cde76e8e315f861e?cid=2afe87a64b0042dabf51f37318616965",
                3.49,
                R.drawable.artist_cover_jj);
        albumplaylistplaylistsong[0] = tashuo;
        albumplaylistplaylistsong[1] = aixiaodeyanjing;
        albumplaylistplaylistsong[2] = zhiduiniyouganjue;
        albumplaylistplaylistsong[3] = dangni;
        albumplaylistplaylistsong[4] = yiyanwannian;
        albumplaylistplaylistsong[5] = baohuse;
        albumplaylistplaylistsong[6] = wobujindeta;
    }
    public Song getCurrentSong(int currentSongId) {
        return albumplaylistplaylistsong[currentSongId];
    }
    public int SearchSongById(int id){
        for(int index=0; index < albumplaylistplaylistsong.length; index++){
            Song tempsong = albumplaylistplaylistsong[index];
            if(tempsong.getId() == id){return index;} }
        return -1;}
    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);}

    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = SearchSongById(Integer.parseInt(resourceId));
        //tester//Log.d("temasek","The index in the array for this song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);}

    public int getNextSong(int currentSongIndex){
        //tester//Log.d("temasek","The current index is :" + currentSongIndex);
        //tester//Log.d("temasek","The index in the arris :" + playlistSongs.length);
        if (currentSongIndex >= albumplaylistplaylistsong.length-1){return currentSongIndex;}
        else{return currentSongIndex + 1;}}

    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <=0){return currentSongIndex;}
        else{return currentSongIndex-1;}}
}
