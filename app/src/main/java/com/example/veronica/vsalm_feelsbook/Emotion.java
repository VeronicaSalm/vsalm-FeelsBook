package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public abstract class Emotion {

    private Date timestamp;
    private String comment;

    Emotion(Date t, String c) {
        this.timestamp = t;
        this.comment = c;
    }

    Emotion(String c) {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = c;

    }

    Emotion() {
        this.timestamp = new Date(System.currentTimeMillis());
        this.comment = "";

    }

    String getComment() { return this.comment; }
    Date getTimestamp() { return this.timestamp; }
    void setComment(String new_c) { this.comment = new_c; }
    void setTimestamp(Date t) { this.timestamp = t; }

}
