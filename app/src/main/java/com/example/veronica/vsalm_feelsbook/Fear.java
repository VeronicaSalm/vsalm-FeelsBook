package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

/* Emotion Subclass: Fear

Implements the constructors needed to set the
type string and appropriate emoji.

 */
public class Fear extends Emotion {

    /* Comment only constructor. Date will be set to
    the current date by default in the super class.*/
    Fear(String comment) {
        super(comment);
        this.setTypeString("Fear");
        this.setEmoji(R.drawable.scared_small);
    }

    /* Date and comment constructor, used when
    instantiating new emotions after editing. */
    Fear(String c, Date d) {
        super(c, d);
        this.setTypeString("Fear");
        this.setEmoji(R.drawable.scared_small);
    }
}
