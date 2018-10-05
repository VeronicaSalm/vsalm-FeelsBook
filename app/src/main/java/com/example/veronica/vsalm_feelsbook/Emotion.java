package com.example.veronica.vsalm_feelsbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Class: Emotion

Implements the main functionality of an emotion,
which is common among all emotion subclasses.

 */
public class Emotion implements Serializable {

    // each emotion has a timestamp, comment (which may be ""), type, and emoji
    private Date timestamp;
    private String comment;
    private String type;
    private int emoji;

    // denotes each possible type of emotion in string form
    private static String[] type_list = {"Joy", "Anger", "Fear", "Sadness", "Love", "Surprise"};

    // the base class is initialized using only a comment and date
    // emoji and type are set in the subclasses
    Emotion(String c, Date t) {
        this.timestamp = t;
        this.comment = c;
    }

    // the base class is initialized using only a comment
    // emoji and type are set in the subclasses
    Emotion(String c) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = c;
    }

    // getters for each emotion attribute
    public String getComment() { return this.comment; }
    public Date getTimestamp() { return this.timestamp; }
    public String getTypeString() { return this.type; }
    public int getEmoji() { return this.emoji; }

    // setters for each emotion attribute
    public void setComment(String new_c) { this.comment = new_c; }
    public void setTimestamp(Date t) { this.timestamp = t; }
    public void setTypeString(String t) { this.type = t; }
    public void setEmoji(int imgid) { this.emoji = imgid; }

    // return the emoji type strings
    public static String[] getTypes() { return type_list; }


    // allows retrieval of the full date in a nicely formatted string
    public  String getTimestampString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
        return df.format(this.timestamp);
    }

    // retrieval of only the time portion of the date
    public  String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(this.timestamp);
    }

    // retrieval of only the day, month, and year of the date
    public String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        return df.format(this.timestamp);
    }
}