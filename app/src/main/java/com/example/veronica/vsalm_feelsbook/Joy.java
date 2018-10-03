package com.example.veronica.vsalm_feelsbook;

public class Joy extends Emotion {

    // need to record associated emoji icon
    // colour
    // associated string/type?

    Joy(int id, String comment) {
        super(id, comment);
        this.setTypeString("Joy");
        this.setEmoji(R.drawable.joy_small);
    }
}
