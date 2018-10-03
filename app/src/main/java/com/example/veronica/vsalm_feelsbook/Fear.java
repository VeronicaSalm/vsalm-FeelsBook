package com.example.veronica.vsalm_feelsbook;

public class Fear extends Emotion {

    Fear(int id, String comment) {
        super(id, comment);
        this.setTypeString("Fear");
        this.setEmoji(R.drawable.scared_small);
    }
}
