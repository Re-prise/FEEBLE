package com.example.musicstream;

public class Song {

    private int id;
    private String title;
    private String artiste;
    private String fileLink;
    private double songLength;
    private int drawable;

    //Constructor
    public Song(int id, String title, String artiste, String fileLink, double songLength, int drawable){
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.drawable = drawable;
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getArtiste() {return artiste;}
    public String getFileLink() {return fileLink;}
    public double getSongLength() {return songLength;}
    public int getDrawable() {return drawable;}

}
