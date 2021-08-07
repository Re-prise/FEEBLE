package com.example.musicstream;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class SongCollection extends AppCompatActivity {
    Song songs[] = new Song[8];
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

        Song wuli = new Song(4,
                "雾里",
                "姚六一",
                "https://p.scdn.co/mp3-preview/36aacec17422bfe768edc6d6196971bdb284ad2a?cid=2afe87a64b0042dabf51f37318616965",
                4.2,
                R.drawable.cover_wuli);

        Song xingchendahai = new Song(5,
                "星辰大海",
                "黄霄雲",
                "https://p.scdn.co/mp3-preview/d1c4a4296c5b7915b4d1a141fc84530c180c9c60?cid=2afe87a64b0042dabf51f3731861696",
                3.46,
                R.drawable.coverimg_xingchendahai);

        Song bingtai = new Song(6,
                "病態",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/e80f93514ad3b589dc18765773b181004df88ebb?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                R.drawable.coverimg_chen);

        Song muouren = new Song(7,
                "木偶人",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/a09ed6a74e0a04e40877dbaff4b1bfcba93ecea8?cid=2afe87a64b0042dabf51f37318616965",
                4.78,
                R.drawable.coverimg_chen);

        Song manbanpai = new Song(8,
                "慢半拍",
                "Joker Xue",
                "https://p.scdn.co/mp3-preview/7696818fd57a3baf2708ef0b917f0c6ab587711b?cid=2afe87a64b0042dabf51f37318616965",
                4.03,
                R.drawable.coverimg_chen);
        songs[0] = dangNi;
        songs[1] = liKaiNiYiHou;
        songs[2] = baige;
        songs[3] = wuli;
        songs[4] = xingchendahai;
        songs[5] = bingtai;
        songs[6] = muouren;
        songs[7] = manbanpai;
    }
    public Song getCurrentSong(int currentSongId) {
        return songs[currentSongId];
    }
    public int SearchSongById(int id){
        for(int index=0; index < songs.length; index++){
            Song tempsong = songs[index];
            if(tempsong.getId() == id){return index;} }
        return -1;}
    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);}
    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());
        SongCollection CAI = new SongCollection();
        int currentArrayIndex = CAI.SearchSongById(Integer.parseInt(resourceId));
        Log.d("temasek","The index in the array for this song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);}

    //TODO: CURRENT GET NEXT SONG is not randomised and hardstuck with default order
    //TODO: need make currentsong always first of randomised list
    public int getNextSong(int currentSongIndex){
        Log.d("temasek","The current index is :" + currentSongIndex);
        Log.d("temasek","The index in the arris :" + songs.length);
        if (currentSongIndex >= songs.length-1){return currentSongIndex;}
        else{return currentSongIndex + 1;}}


    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <=0){return currentSongIndex;}
        else{return currentSongIndex-1;}}

}
