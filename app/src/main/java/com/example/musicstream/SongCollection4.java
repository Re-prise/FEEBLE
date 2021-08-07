package com.example.musicstream;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SongCollection4 extends AppCompatActivity {
    Song albumplaylistsong[] = new Song[5];
    public SongCollection4() {
        Song muouren = new Song(1,
                "木偶人",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/a09ed6a74e0a04e40877dbaff4b1bfcba93ecea8?cid=2afe87a64b0042dabf51f37318616965",
                4.78,
                R.drawable.coverimg_chen);
        Song manbanpai = new Song(2,
                "慢半拍",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/7696818fd57a3baf2708ef0b917f0c6ab587711b?cid=2afe87a64b0042dabf51f37318616965",
                4.03,
                R.drawable.coverimg_chen);
        Song zenmejiumeijian = new Song(3,
                "這麼久沒見",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/4cf1e77a0bc5060a48692105ec60c7b94461c399?cid=2afe87a64b0042dabf51f37318616965",
                4.92,
                R.drawable.coverimg_chen);
        Song xiaochang = new Song(4,
                "笑場",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/b9cda6b1738ef497b28d19f02e343a4a4ff108d4?cid=2afe87a64b0042dabf51f37318616965",
                4.54,
                R.drawable.coverimg_chen);
        Song bingtai = new Song(5,
                "病態",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/e80f93514ad3b589dc18765773b181004df88ebb?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                R.drawable.coverimg_chen);
        albumplaylistsong[0] = muouren;
        albumplaylistsong[1] = manbanpai;
        albumplaylistsong[2] = zenmejiumeijian;
        albumplaylistsong[3] = xiaochang;
        albumplaylistsong[4] = bingtai;
    }
    public Song getCurrentSong(int currentSongId) {
        return albumplaylistsong[currentSongId];
    }
    public int SearchSongById(int id){
        for(int index=0; index < albumplaylistsong.length; index++){
            Song tempsong = albumplaylistsong[index];
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
        if (currentSongIndex >= albumplaylistsong.length-1){return currentSongIndex;}
        else{return currentSongIndex + 1;}}

    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <=0){return currentSongIndex;}
        else{return currentSongIndex-1;}}
}
