package com.example.veronica.vsalm_feelsbook;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public abstract class Emotion implements Serializable {

    private Date timestamp;
    private String comment;
    private String type;
    private int id;

    Emotion(Date t, String c, int id) {
        this.timestamp = t;
        this.comment = c;
        this.id = id;
    }

    Emotion(String c, int id) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = c;
        this.id = id;

    }

    Emotion(int id) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = "";
        this.id = id;

    }

    String getComment() { return this.comment; }
    Date getTimestamp() { return this.timestamp; }
    void setComment(String new_c) { this.comment = new_c; }
    void setTimestamp(Date t) { this.timestamp = t; }
    String getTimestampString() {return this.timestamp.toString(); }
    String getTypeString() { return this.type; }
    void setTypeString(String t) { this.type = t; }
    void setId(int id) {this.id = id;}
    int getId() { return this.id; }

    public String toString() {
        String emotion_string = this.type + " | " + this.timestamp.toString()
                                + " | " + this.comment;

        return emotion_string;
    }

}
