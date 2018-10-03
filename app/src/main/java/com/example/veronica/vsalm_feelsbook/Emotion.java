package com.example.veronica.vsalm_feelsbook;

import android.media.Image;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class Emotion implements Serializable {

    private Date timestamp;
    private String comment;
    private String type;
    private int id;
    private int emoji;

    Emotion(Date t, String c, int id) {
        this.timestamp = t;
        this.comment = c;
        this.id = id;
        this.emoji = R.drawable.joy_small;
    }

    Emotion(String c, int id) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = c;
        this.id = id;
        this.emoji = R.drawable.joy_small;

    }

    Emotion(int id, String comment) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = comment;
        this.id = id;

    }

    String getComment() { return this.comment; }
    Date getTimestamp() { return this.timestamp; }
    void setComment(String new_c) { this.comment = new_c; }
    void setTimestamp(Date t) { this.timestamp = t; }
    String getTimestampString() {
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        return df.format(this.timestamp);
    }
    String getTypeString() { return this.type; }
    void setTypeString(String t) { this.type = t; }
    void setId(int id) {this.id = id;}
    int getId() { return this.id; }
    void setEmoji(int imgid) {this.emoji = imgid; }
    int getEmoji() {return this.emoji;}

    public String toString() {
        return this.type + " | " + this.timestamp.toString()
                                + " | " + this.comment;
    }

}
