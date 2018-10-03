package com.example.veronica.vsalm_feelsbook;

import android.media.Image;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class Emotion implements Serializable {

    private Date timestamp;
    private String comment;
    private String type;
    private int id;
    private int emoji;
    private static String[] type_list = {"Joy", "Anger", "Fear", "Sadness", "Love", "Surprise"};

    Emotion(int id, String c, Date t) {
        this.timestamp = t;
        this.comment = c;
        this.id = id;
    }

    Emotion(String c, int id) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = c;
        this.id = id;

    }

    Emotion(int id, String comment) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = comment;
        this.id = id;

    }

    public String getComment() { return this.comment; }
    public Date getTimestamp() { return this.timestamp; }
    public void setComment(String new_c) { this.comment = new_c; }
    public void setTimestamp(Date t) { this.timestamp = t; }
    public  String getTimestampString() {
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        return df.format(this.timestamp);
    }
    public String getTypeString() { return this.type; }
    public void setTypeString(String t) { this.type = t; }
    public void setId(int id) {this.id = id;}
    public int getId() { return this.id; }
    public void setEmoji(int imgid) {this.emoji = imgid; }
    public int getEmoji() {return this.emoji;}

    public String toString() {
        return this.type + " | " + this.timestamp.toString()
                                + " | " + this.comment;
    }

    public static String[] getTypes() {
        return type_list;
    }

}
