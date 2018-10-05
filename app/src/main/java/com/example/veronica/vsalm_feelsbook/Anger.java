package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Anger

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Anger extends Emotion {

    /* Comment only constructor. Date will be set to
    the current date by default in the super class.*/
    Anger(String comment) {
        super(comment);
        this.setTypeString("Anger");
        this.setEmoji(R.drawable.mad_small);
    }

    /* Date and comment constructor, used when
    instantiating new emotions after editing. */
    Anger(String c, Date d) {
        super(c, d);
        this.setTypeString("Anger");
        this.setEmoji(R.drawable.mad_small);
    }
}
