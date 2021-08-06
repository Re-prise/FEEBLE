package com.example.musicstream;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Albums {

    public int id;
    public String albumname;
    public Object[][] songs;

    public Albums(int id, String albumname, Object[][] songs){
        this.id = id;
        this.albumname = albumname;
        this.songs = songs;

    }
    public int getId() {return id;}
    public String getAlbumname() {return albumname;}
}


class AlbumCollection extends AppCompatActivity {
    Albums albums[] = new Albums[2];

    public AlbumCollection() {
        Albums first1 = new Albums(1,
                "当你",
                new Object[][]{
                        {
                            "nested title1",
                            "picture link1",
                            "nested artist1"

                        },
                        {
                            "nested title2",
                            "picture link2",
                            "nested artist2"
                        },
                        {
                            "nested title3",
                            "picture link3",
                            "nested artist3"
                        }
                }
        );

        Albums second2 = new Albums(1,
                "SampleList",
                new Object[][]{
                        {
                                "nested title1",
                                "picture link1",
                                "nested artist1"

                        },
                        {
                                "nested title2",
                                "picture link2",
                                "nested artist2"
                        },
                        {
                                "nested title3",
                                "picture link3",
                                "nested artist3"
                        }
                }
        );
        //Add Songs When Needed
        albums[0] = first1;
        albums[1] = second2;

        //Fill Array number
    }
    public Albums getCurrentSong(int currentAlbumId) {
        return albums[currentAlbumId];
    }
    public int SearchSongById(int id){
        for(int index=0; index < albums.length; index++){
            Albums tempalbum = albums[index];
            if(tempalbum.getId() == id){return index;} }
        return -1;}
    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaylistActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);}
//    public void handleSelection(View myView){
//        String resourceId = getResources().getResourceEntryName(myView.getId());
//        SongCollection CAI = new SongCollection();
//        int currentArrayIndex = CAI.SearchSongById(Integer.parseInt(resourceId));
//        Log.d("temasek","The index in the array for this song is :" + currentArrayIndex);
//        sendDataToActivity(currentArrayIndex);}
//    public int getNextSong(int currentSongIndex){
//        Log.d("temasek","The index in the arris :" + albums.length);
//        if (currentSongIndex >= albums.length-1){return currentSongIndex;}
//        else{return currentSongIndex + 1;}}
//    public int getPrevSong(int currentSongIndex){
//        if(currentSongIndex <=0){return currentSongIndex;}
//        else{return currentSongIndex-1;}}
}
