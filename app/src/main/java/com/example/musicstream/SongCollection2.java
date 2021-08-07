package com.example.musicstream;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SongCollection2 extends AppCompatActivity {
    Song playlistSongs[] = new Song[3];
    public SongCollection2() {
        Song wuli = new Song(1,
                "雾里",
                "姚六一",
                "https://p.scdn.co/mp3-preview/36aacec17422bfe768edc6d6196971bdb284ad2a?cid=2afe87a64b0042dabf51f37318616965",
                4.2,
                R.drawable.cover_wuli);

        Song xingchendahai = new Song(2,
                "星辰大海",
                "黄霄雲",
                "https://p.scdn.co/mp3-preview/d1c4a4296c5b7915b4d1a141fc84530c180c9c60?cid=2afe87a64b0042dabf51f3731861696",
                3.46,
                R.drawable.coverimg_xingchendahai);

        Song bingtai = new Song(3,
                "病態",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/e80f93514ad3b589dc18765773b181004df88ebb?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                R.drawable.coverimg_chen);

        playlistSongs[0] = wuli;
        playlistSongs[1] = xingchendahai;
        playlistSongs[2] = bingtai;

    }
    public Song getCurrentSong(int currentSongId) {
        return playlistSongs[currentSongId];
    }
    public int SearchSongById(int id){
        for(int index=0; index < playlistSongs.length; index++){
            Song tempsong = playlistSongs[index];
            if(tempsong.getId() == id){return index;} }
        return -1;}
    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);}
    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());
        SongCollection2 CAI = new SongCollection2();
        int currentArrayIndex = CAI.SearchSongById(Integer.parseInt(resourceId));
        Log.d("temasek","The index in the array for this song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);}

    public int getNextSong(int currentSongIndex){
        Log.d("temasek","The current index is :" + currentSongIndex);
        Log.d("temasek","The index in the arris :" + playlistSongs.length);
        if (currentSongIndex >= playlistSongs.length-1){return currentSongIndex;}
        else{return currentSongIndex + 1;}}

    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <=0){return currentSongIndex;}
        else{return currentSongIndex-1;}}
}
