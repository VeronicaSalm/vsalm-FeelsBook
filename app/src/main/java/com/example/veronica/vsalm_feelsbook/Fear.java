package com.example.veronica.vsalm_feelsbook;

import java.util.Date;

public class Fear extends Emotion {

    Fear(int id, String comment) {
        super(id, comment);
        this.setTypeString("Fear");
        this.setEmoji(R.drawable.scared_small);
    }

    Fear(int id, String c, Date d) {
        super(id, c, d);
        this.setTypeString("Fear");
        this.setEmoji(R.drawable.scared_small);
    }
}
