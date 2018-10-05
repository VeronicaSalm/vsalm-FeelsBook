package com.example.veronica.vsalm_feelsbook;

import java.util.Date;


/* Emotion Subclass: Sadness

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Sadness extends Emotion {

    /* Comment only constructor. Date will be set to
    the current date by default in the super class.*/
    Sadness(String comment) {
        super(comment);
        this.setTypeString("Sadness");
        this.setEmoji(R.drawable.sad_small);
    }

    /* Date and comment constructor, used when
    instantiating new emotions after editing. */
    Sadness(String c, Date d) {
        super(c, d);
        this.setTypeString("Sadness");
        this.setEmoji(R.drawable.sad_small);
    }
}
