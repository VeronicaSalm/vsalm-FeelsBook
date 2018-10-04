package com.example.veronica.vsalm_feelsbook;

import java.util.Date;


/* Emotion Subclass: Sadness

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Sadness extends Emotion {

    Sadness(String comment) {
        super(comment);
        this.setTypeString("Sadness");
        this.setEmoji(R.drawable.sad_small);
    }

    Sadness(String c, Date d) {
        super(c, d);
        this.setTypeString("Sadness");
        this.setEmoji(R.drawable.sad_small);
    }
}
