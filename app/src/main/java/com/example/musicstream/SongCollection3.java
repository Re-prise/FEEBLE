package com.example.musicstream;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SongCollection3 extends AppCompatActivity {
    Song playlistplaylistsong[] = new Song[3];
    public SongCollection3() {
        Song guaika = new Song(1,
                "怪咖",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/3a4c0c2a48c36ee1a8fdc5f334c6d329d0329942?cid=2afe87a64b0042dabf51f37318616965",
                4.17,
                R.drawable.coverimg_jokerx);
        Song huli = new Song(2,
                "狐狸",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/4d5218a47363261853ee9a1e96386c85675385cf?cid=2afe87a64b0042dabf51f37318616965",
                3.91,
                R.drawable.coverimg_jokerx);
        Song tianfen = new Song(3,
                "天份",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/7fbe0aed175bbb32e7df2a506dd9ba2f68931437?cid=2afe87a64b0042dabf51f37318616965",
                4.14,
                R.drawable.coverimg_jokerx);
        playlistplaylistsong[0] = guaika;
        playlistplaylistsong[1] = huli;
        playlistplaylistsong[2] = tianfen;
    }
    public Song getCurrentSong(int currentSongId) {
        return playlistplaylistsong[currentSongId];
    }
    public int SearchSongById(int id){
        for(int index=0; index < playlistplaylistsong.length; index++){
            Song tempsong = playlistplaylistsong[index];
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
        if (currentSongIndex >= playlistplaylistsong.length-1){return currentSongIndex;}
        else{return currentSongIndex + 1;}}

    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <=0){return currentSongIndex;}
        else{return currentSongIndex-1;}}
}
