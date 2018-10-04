package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Surprise

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Surprise extends Emotion {

    Surprise(String comment) {
        super(comment);
        this.setTypeString("Surprise");
        this.setEmoji(R.drawable.surprised_small);
    }

    Surprise(String c, Date d) {
        super(c, d);
        this.setTypeString("Surprise");
        this.setEmoji(R.drawable.surprised_small);
    }

}
