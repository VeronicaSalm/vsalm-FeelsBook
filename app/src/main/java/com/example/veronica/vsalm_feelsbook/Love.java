package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Love

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Love extends Emotion {

    Love(String comment) {
        super(comment);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

    Love(String c, Date d) {
        super(c, d);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

}
