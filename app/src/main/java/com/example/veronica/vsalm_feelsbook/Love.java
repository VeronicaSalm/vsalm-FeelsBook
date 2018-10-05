package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Love

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Love extends Emotion {

    /* Comment only constructor. Date will be set to
    the current date by default in the super class.*/
    Love(String comment) {
        super(comment);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

    /* Date and comment constructor, used when
    instantiating new emotions after editing. */
    Love(String c, Date d) {
        super(c, d);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

}
