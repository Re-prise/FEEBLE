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
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity2 extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;
    private double songLength;

    private MediaPlayer songplayer = new MediaPlayer();
    ImageButton play_button;
    SongCollection songCollection = new SongCollection();
    //for randomised shuffle
    SongCollection randomCollection = new SongCollection();
    ImageButton loopbtn;
    ImageButton shufflebtn;

    int convertedTime;
    SeekBar seekBar;
    Boolean repeatFlag;
    Boolean shuffleFlag;
    List<Song> shuffleList;


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
        seekBar = findViewById(R.id.seekBar);
        repeatFlag = false;
        shuffleFlag = false;


        ImageButton play_button = findViewById(R.id.play_button);
        //TODO: add a list at homepage in the future and link it to here
        //Bundle songData = this.getIntent().getExtras();
        //currentIndex = songData.getInt("index");
        currentIndex = 1;
        Log.d("temasek", "Retrieved Position is: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        Log.d("temasek", "My file link is: " + fileLink);

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


    //Runnable songbar = () -> Log.d("temasek","running");//
    public void displaySongBasedOnIndex(int currentIndex) {
        SongCollection SC = new SongCollection();
        //Song song;
//        if (shuffleFlag){
//            song = getRandoSong(currentIndex);
//        }
//        else{
//            song = SC.getCurrentSong(currentIndex);
//        }

        Song song = SC.getCurrentSong(currentIndex);
        title = song.getTitle();
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
        seekBar.setMax(convertedTime);
        Log.d("music_tid", "Song time: "+ doubleToTid);

        playSong(fileLink);
    }



    protected void onStop() {
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
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            songplayer.stop();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


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
            gracefullyStopsWhenMusicEnds();

//            //TODO: for PlaySongActivity1 only
//            playsNextSongWhenMusicEnds();

            play_button.setImageResource(R.drawable.pause_button);
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playPrev (View view){
        currentIndex = songCollection.getPrevSong(currentIndex);
        Log.d("temasek","After playPrevious, the index is now :" + currentIndex);
//        displaySongBasedOnIndex(currentIndex);
//        playSong(fileLink);
    }
    public void playOrPauseMusic(View view){
        if (!songplayer.isPlaying()) {
            songplayer.start();
            //handler.postDelayed(songbar,1000);//
            gracefullyStopsWhenMusicEnds();
            play_button.setImageResource(R.drawable.pause_button);
            setTitle(" " + title +  " - " + artiste);
        }
        else {
            songplayer.pause();
            play_button.setImageResource(R.drawable.play_arrow); }
    }
    public void playNext (View view) {
//        if(shuffleFlag){
//            Log.d("shuffle1", "activated");
//            currentIndex = getRandomSong(currentIndex);
//            reshuffleSong();
//        }
//        else{
//            Log.d("shuffle1", "deactivated");
//            currentIndex = songCollection.getNextSong(currentIndex);
//        }
        Log.d("temasek","After playNexy, the index is now :" + currentIndex);
//        displaySongBasedOnIndex(currentIndex);
//        playSong(fileLink);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        songplayer.release();
    }
    private void gracefullyStopsWhenMusicEnds() {
        songplayer.setOnCompletionListener(mp -> {
            if (repeatFlag){
                playOrPauseMusic(null);
            } else {
                play_button.setImageResource(R.drawable.play_arrow);
            }
        });
    }

    //TODO: for PlaySongActivity1 only
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
            songCollection = new SongCollection();
        } else {
            shufflebtn.setImageResource(R.drawable.shuffle_icon_filled);
            shuffleList = Arrays.asList(randomCollection.songs);
            Collections.shuffle(Arrays.asList(randomCollection.songs));

            for (int s = 0; s < shuffleList.size(); s++){
                Log.d("shuffle1", "arr: " + shuffleList.get(s).getTitle());

                if (title == shuffleList.get(s).getTitle()){
                    shuffleList.toArray(randomCollection.songs);
                }
            }

        }
        shuffleFlag = !shuffleFlag;
    }

//    //initiate song on list
//    public Song getRandoSong(int currentSongId) {
//
//        return randomCollection.songs[currentSongId];
//    }
//
//    //everytime when song ends, the playlist will reshuffle again
//    public void reshuffleSong() {
//        shuffleList = Arrays.asList(randomCollection.songs);
//        Collections.shuffle(shuffleList);
//
//        for (int s = 0; s < shuffleList.size(); s++){
//            Log.d("shuffle1", "arr: " + shuffleList.get(s).getTitle());
//
//            if (title == shuffleList.get(s).getTitle()){
//                Log.d("shuffle1", "located! song position is " + s);
//                //List<~> al = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 6));
//                // currentIndex = songCollection.removeSong(currentIndex);
//                shuffleList.toArray(songCollection.songs);
////                    Arrays.asList(randomCollection.songs).add(1, randomCollection.songs[s]);
//            }
//        }
//    }
//
//    //for playing next and previous shuffling song
//    public int getRandomSong(int currentSongIndex){
//        Log.d("temasek","The current index is :" + currentSongIndex);
//        Log.d("temasek","The index in the arris :" + randomCollection.songs.length);
//        if (currentSongIndex >= randomCollection.songs.length-1){return currentSongIndex;}
//        else{return currentSongIndex + 1;}
//    }

    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void teleportToHome(View view){startActivity(new Intent(PlaySongActivity2.this,MainActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void teleportToSettings(View view){startActivity(new Intent(PlaySongActivity2.this,SettingsActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void teleportToCollections(View view){startActivity(new Intent(PlaySongActivity2.this,CollectionsActivity.class));}
    //onClick from Back button to finish current Activity
    public void bacK(View view){
        finish();
    }
}

