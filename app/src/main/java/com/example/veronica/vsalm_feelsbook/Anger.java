package com.example.veronica.vsalm_feelsbook;

public class Anger extends Emotion {

    Anger(int id, String comment) {
        super(id, comment);
        this.setTypeString("Anger");
        this.setEmoji(R.drawable.mad_small);
    }
}
