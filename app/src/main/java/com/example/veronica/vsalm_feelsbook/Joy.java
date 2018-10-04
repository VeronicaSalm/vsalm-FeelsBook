package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Joy

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Joy extends Emotion {

    // need to record associated emoji icon
    // colour
    // associated string/type?

    Joy(String comment) {
        super(comment);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }

    Joy(String c, Date d) {
        super(c, d);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }
}
