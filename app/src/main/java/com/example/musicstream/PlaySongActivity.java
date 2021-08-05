package com.example.musicstream;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


//TODO: for playlist songs
//TODO: add seekbar for songs

public class PlaySongActivity extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;
    private double songLength;

    private MediaPlayer songplayer = new MediaPlayer();
//    private Handler myHandler = new Handler();;
//    private SeekBar seekBar;
//    private double startTime = 0;
//    private double finalTime = 0;
//    private int forwardTime = 5000;
//    private int backwardTime = 5000;
//    public static int oneTimeOnly = 0;
//    TextView txtstartduration = findViewById(R.id.txt_artist_duration_start);
//    TextView txtendduration = findViewById(R.id.txt_artist_duration_total);
    ImageButton play_button;
    SongCollection songCollection = new SongCollection();
    ImageButton loopbtn;
    ImageButton shufflebtn;
    Boolean repeatFlag;
    Boolean shuffleFlag;
    List<Song> shuffleList;

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
        shuffleList = Arrays.asList(songCollection.songs);
        System.out.println(shuffleList);
        ImageButton play_button = findViewById(R.id.play_button);
        //TODO: add a list at homepage in the future and link it to here
        //Bundle songData = this.getIntent().getExtras();
//        seekBar = findViewById(R.id.seekBar);
        //currentIndex = songData.getInt("index");
        currentIndex = 1;
        Log.d("temasek", "Retrieved Position is: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        Log.d("temasek", "My file link is: " + fileLink);}
        //TODO: solve raw issue?
//        songplayer = MediaPlayer.create(this, R.raw.song);
//        seekBar = (SeekBar)findViewById(R.id.seekBar);
//        seekBar.setClickable(false);
//        finalTime = songplayer.getDuration();
//        startTime = songplayer.getCurrentPosition();
//        if (oneTimeOnly == 0) {
//            seekBar.setMax((int) finalTime);
//            oneTimeOnly = 1;
//        }
//        txtendduration.setText(String.format("%d min, %d sec",
//                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
//                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                finalTime)))
//        );
//
//        txtstartduration.setText(String.format("%d min, %d sec",
//                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                startTime)))
//        );
//        seekBar.setProgress((int)startTime);
//        myHandler.postDelayed(UpdateSongTime,100);
//    }
//    private Runnable UpdateSongTime = new Runnable() {
//        public void run() {
//            startTime = songplayer.getCurrentPosition();
//            txtstartduration.setText(String.format("%d min, %d sec",
//                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
//                                    toMinutes((long) startTime)))
//            );
//            seekBar.setProgress((int) startTime);
//            myHandler.postDelayed(this, 100);
//        }
//    };
    public void displaySongBasedOnIndex(int currentIndex) {
        SongCollection SC = new SongCollection();
        Song song = SC.getCurrentSong(currentIndex);
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

        playSong(fileLink); }
    //to play song
    public void playSong(String songUrl){
        try{
            songplayer.reset();
            songplayer.setDataSource(songUrl);
            songplayer.prepare();
            songplayer.start();
            gracefullyStopsWhenMusicEnds();

            play_button.setImageResource(R.drawable.pause_button);

            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //to skip to prev song.
    public void playPrev (View view){
        currentIndex = songCollection.getPrevSong(currentIndex);
        //tester//Log.d("temasek","After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    //to play or pause music. Changes play/pause buttons
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

    //to skip to next song.
    public void playNext (View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
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

    //toggles loop icon from on/off
    public void loopSong(View view) {
        if (repeatFlag){
            loopbtn.setImageResource(R.drawable.repeat_icon);
        } else {
            loopbtn.setImageResource(R.drawable.repeat_icon_filled);
        }
        repeatFlag = !repeatFlag;
    }

    //toggles shuffle icon from on/off. Also produces shuffled list.
    public void shuffleSong(View view) {
        if (shuffleFlag){
            shufflebtn.setImageResource(R.drawable.shuffle_icon);
            songCollection = new SongCollection();
        } else {
            shufflebtn.setImageResource(R.drawable.shuffle_icon_filled);
            Collections.shuffle(shuffleList);
            for (int i = 0; i < shuffleList.size(); i++) {
                //tester//Log.d("shuffle", shuffleList.get(i).getTitle());
                shuffleList.toArray(songCollection.songs);
            }
        }
        shuffleFlag = !shuffleFlag;
    }
    //onClick function for Toolbar + back button
    //onClick from Toolbar(home) to Main Activity
    public void teleportToHome(View view){startActivity(new Intent(PlaySongActivity.this,MainActivity.class));}
    //onClick from Toolbar(settings) to Settings Activity
    public void teleportToSettings(View view){startActivity(new Intent(PlaySongActivity.this,SettingsActivity.class));}
    //onClick from Toolbar(collections) to Collections Activity
    public void teleportToCollections(View view){startActivity(new Intent(PlaySongActivity.this,CollectionsActivity.class));}
    //onClick from Back button to finish current Activity
    public void bacK(View view){
        finish();
    }
}

