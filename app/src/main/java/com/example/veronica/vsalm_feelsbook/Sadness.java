package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public class Sadness extends Emotion {

    Sadness(int id, String comment) {
        super(id, comment);
        this.setTypeString("Sadness");
        this.setEmoji(R.drawable.sad_small);
    }

    Sadness(int id, String c, Date d) {
        super(id, c, d);
        this.setTypeString("Sadness");
        this.setEmoji(R.drawable.sad_small);
    }
}
