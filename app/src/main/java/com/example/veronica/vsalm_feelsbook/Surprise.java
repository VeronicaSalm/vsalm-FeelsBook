package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public class Surprise extends Emotion {

    Surprise(int id, String comment) {
        super(id, comment);
        this.setTypeString("Surprise");
        this.setEmoji(R.drawable.surprised_small);
    }

    Surprise(int id, String c, Date d) {
        super(id, c, d);
        this.setTypeString("Surprise");
        this.setEmoji(R.drawable.surprised_small);
    }

}
