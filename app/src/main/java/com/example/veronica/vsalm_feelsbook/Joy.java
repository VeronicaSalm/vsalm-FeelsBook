package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public class Joy extends Emotion {

    // need to record associated emoji icon
    // colour
    // associated string/type?

    Joy(int id, String comment) {
        super(id, comment);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }

    Joy(int id, String c, Date d) {
        super(id, c, d);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }
}
