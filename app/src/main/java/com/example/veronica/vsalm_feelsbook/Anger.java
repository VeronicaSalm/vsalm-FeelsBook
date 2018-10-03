package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public class Anger extends Emotion {

    Anger(int id, String comment) {
        super(id, comment);
        this.setTypeString("Anger");
        this.setEmoji(R.drawable.mad_small);
    }

    Anger(int id, String c, Date d) {
        super(id, c, d);
        this.setTypeString("Anger");
        this.setEmoji(R.drawable.mad_small);
    }
}
