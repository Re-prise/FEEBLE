package com.example.musicstream;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity3 extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex;
    private double songLength;

    private MediaPlayer songplayer = new MediaPlayer();
    ImageButton play_button;
    SongCollection3 songCollection3 = new SongCollection3();
    SongCollection3 randomCollection3 = new SongCollection3();
    ImageButton loopbtn;
    ImageButton shufflebtn;
    Boolean repeatFlag;
    Boolean shuffleFlag;
    List<Song> shuffleList;
    int convertedTime;
    SeekBar seekBar;
    private Handler mHandler = new Handler();
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        getSupportActionBar().hide();

        loopbtn = findViewById(R.id.loop_button);
        shufflebtn = findViewById(R.id.shuffle_button);
        play_button = (ImageButton) findViewById(R.id.play_button);
        repeatFlag = false;
        shuffleFlag = false;
        shuffleList = Arrays.asList(songCollection3.playlistplaylistsong);
        seekBar = findViewById(R.id.seekBar);
        shuffleList = Arrays.asList(songCollection3.playlistplaylistsong);
        System.out.println(shuffleList);
        //Bundle songData = this.getIntent().getExtras();
        //currentIndex = songData.getInt("index");
        currentIndex = 1;
        currentIndex = 0;
        Log.d("temasek", "Retrieved Position is: " + currentIndex);

        displaySongBasedOnIndex(currentIndex);
    }


    public void ConvertToSecs(String n)
    {
        String[] words = n.split("\\:");
        Log.d("show", "show num1: " + words[0]);
        Log.d("show", "show num2: " + words[1]);
        int minutes, seconds;

        minutes = Integer.parseInt(words[0]) * 60;
        seconds = Integer.parseInt(words[1]) + minutes;
        convertedTime = seconds;

        Log.d("show", "Minutes = " + minutes
                + ", Seconds = " + seconds);
    }


    public void displaySongBasedOnIndex(int currentIndex) {
        SongCollection3 SC = new SongCollection3();
        Song song;
        if (shuffleFlag){
            song = getRandoSong(currentIndex);
        }
        else{
            song = SC.getCurrentSong(currentIndex);
        }

        String title = song.getTitle();
        String artiste = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();
        songLength = song.getSongLength();
        TextView txtTitle = findViewById(R.id.txt_song_title);
        txtTitle.setText(title);
        TextView txtArtiste = findViewById(R.id.txt_artist);
        txtArtiste.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.song_cover_art);
        iCoverArt.setImageResource(drawable);
        TextView txtDuration = findViewById(R.id.txt_artist_duration_total);
        txtDuration.setText(Double.toString(songLength));

        String doubleToTid = Double.toString(songLength);
        doubleToTid = doubleToTid.replace(".",":");
        ConvertToSecs(doubleToTid);

        playSong(fileLink);
    }

    protected void onStop() {
        Log.d("music_tid", "Perts");
        super.onStop();
        if(songplayer != null) {
            if (songplayer.isPlaying()) {
                seekBar.setProgress(0);
                songplayer.stop();
                finish();
                if (mHandler!= null && runnable != null) {
                    mHandler.removeCallbacks(runnable);
                }
            }
            songplayer.release();
            songplayer = null;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("music_tid", "Blyat");
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            songplayer.stop();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    //to play song
    public void playSong(String songUrl){
        try{
            songplayer.reset();
            songplayer.setDataSource(songUrl);
            songplayer.prepare();
            songplayer.start();

            this.runOnUiThread(runnable = new Runnable() {
                @Override
                public void run() {
                    if(songplayer != null){
                        int mCurrentPosition = songplayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    mHandler.postDelayed(this, 1000);
                }
            });


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(songplayer != null && fromUser){
                        songplayer.seekTo(progress * 1000);
                    }
                }
            });



            //TODO: for PlaySongActivity2 only
            //gracefullyStopsWhenMusicEnds();

            //TODO: for PlaySongActivity1 only
            playsNextSongWhenMusicEnds();
            gracefullyStopsWhenMusicEnds();

            play_button.setImageResource(R.drawable.pause_button);
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //to skip to prev song.
    public void playPrev (View view){
        currentIndex = songCollection3.getPrevSong(currentIndex);
        //tester//Log.d("temasek","After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    //to play or pause music. Changes play/pause buttons
    public void playOrPauseMusic(View view){
        if (!songplayer.isPlaying()) {
            songplayer.start();
            gracefullyStopsWhenMusicEnds();
            play_button.setImageResource(R.drawable.pause_button);
            setTitle(" " + title +  " - " + artiste);
        }
        else {
            songplayer.pause();
            play_button.setImageResource(R.drawable.play_arrow); }
    }
    //to skip to next song.
    public void playNext (View view) {
        if(shuffleFlag){
            Log.d("shuffle1", "activated");
            currentIndex = getRandomSong(currentIndex);
            reshuffleSong();
        }
        else{
            Log.d("shuffle1", "deactivated");
            currentIndex = songCollection3.getNextSong(currentIndex);
        }
        //tester//Log.d("temasek","After playNexy, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }


    //for back button on press
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        songplayer.release();
    }


    //for when music ends. Sets to loop if loop(repeat flag) is turned on.
    private void gracefullyStopsWhenMusicEnds() {
        songplayer.setOnCompletionListener(mp -> {
            if (repeatFlag){
                playOrPauseMusic(null);
            } else {
                play_button.setImageResource(R.drawable.play_arrow);
            }
        });
    }
    private void playsNextSongWhenMusicEnds() {
        songplayer.setOnCompletionListener(mp -> {
            if (repeatFlag){
                playOrPauseMusic(null);
            } else {
                //play_button.setImageResource(R.drawable.play_arrow);
                playNext(null);
            }
        });
    }


    //toggles loop icon from on/off
    public void loopSong(View view) {
        if (repeatFlag){
            loopbtn.setImageResource(R.drawable.repeat_icon);
        } else {
            loopbtn.setImageResource(R.drawable.repeat_icon_filled);
        }
        repeatFlag = !repeatFlag;
    }



    //activate shuffling function
    public void shuffleSong(View view) {
        if (shuffleFlag){
            shufflebtn.setImageResource(R.drawable.shuffle_icon);
            songCollection3 = new SongCollection3();
        } else {
            shufflebtn.setImageResource(R.drawable.shuffle_icon_filled);
            shuffleList = Arrays.asList(randomCollection3.playlistplaylistsong);
            Collections.shuffle(Arrays.asList(randomCollection3.playlistplaylistsong));

            for (int s = 0; s < shuffleList.size(); s++){
                Log.d("shuffle1", "arr: " + shuffleList.get(s).getTitle());

                if (title == shuffleList.get(s).getTitle()){
                    Log.d("shuffle1", "located! song position is " + s);

                    shuffleList.toArray(randomCollection3.playlistplaylistsong);
                }
            }

        }
        shuffleFlag = !shuffleFlag;
    }
    //initiate song on list
    public Song getRandoSong(int currentSongId) {

        return randomCollection3.playlistplaylistsong[currentSongId];
    }
    //everytime when song ends, the playlist will reshuffle again
    public void reshuffleSong() {
        shuffleList = Arrays.asList(randomCollection3.playlistplaylistsong);
        Collections.shuffle(shuffleList);

        for (int s = 0; s < shuffleList.size(); s++){
            Log.d("shuffle1", "arr: " + shuffleList.get(s).getTitle());

            if (title == shuffleList.get(s).getTitle()){
                Log.d("shuffle1", "located! song position is " + s);
                shuffleList.toArray(songCollection3.playlistplaylistsong);
            }
        }
    }
    //for playing next and previous shuffling song
    public int getRandomSong(int currentSongIndex){
        Log.d("temasek","The current index is :" + currentSongIndex);
        Log.d("temasek","The index in the arris :" + randomCollection3.playlistplaylistsong.length);
        if (currentSongIndex >= randomCollection3.playlistplaylistsong.length-1){return currentSongIndex;}
        else{return currentSongIndex + 1;}
    }


    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void teleportToHome(View view){startActivity(new Intent(PlaySongActivity3.this,MainActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void teleportToSettings(View view){startActivity(new Intent(PlaySongActivity3.this,SettingsActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void teleportToCollections(View view){startActivity(new Intent(PlaySongActivity3.this,CollectionsActivity.class));}
    //onClick from Back button to finish current Activity
    public void bacK(View view){
        finish();
    }
}

