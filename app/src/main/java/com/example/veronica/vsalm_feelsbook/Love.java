package com.example.veronica.vsalm_feelsbook;

public class Love extends Emotion {

    Love(int id, String comment) {
        super(id, comment);
        this.setTypeString("Love");
        this.setEmoji(R.drawable.in_love_small);
    }

}
