package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public class Love extends Emotion {

    Love(int id, String comment) {
        super(id, comment);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

    Love(int id, String c, Date d) {
        super(id, c, d);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

}
