package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Joy

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Joy extends Emotion {

    /* Comment only constructor. Date will be set to
    the current date by default in the super class.*/
    Joy(String comment) {
        super(comment);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }

    /* Date and comment constructor, used when
    instantiating new emotions after editing. */
    Joy(String c, Date d) {
        super(c, d);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }
}
