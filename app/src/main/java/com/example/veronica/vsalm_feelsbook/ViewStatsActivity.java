package com.example.veronica.vsalm_feelsbook;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewStatsActivity extends AppCompatActivity {

    ArrayList<Emotion> emotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);

        // set up the toolbar with the appropriate subtitle
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Statistics");

        // get the emotions from the intent
        emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        displayStats();
    }

    public void displayStats() {
        // compute necessary values
        Map<String, Integer> emotion_counts = getEmotionCounts();

        // display the emotion counts on the stats page
        displayEmotionCounts(emotion_counts);
    }

    // Count all emotions in the emotion list
    // store them by type in a HashMap
    public Map<String, Integer> getEmotionCounts() {

        Map<String, Integer> emotion_counts = new HashMap<String, Integer>();

        // initialize all types to have a count of 0
        for (String type : Emotion.getTypes()) {
            emotion_counts.put(type, 0);
        }

        // do a simple frequency count on all emotions in the list
        for (Emotion e: emotions) {
            String type = e.getTypeString();
            emotion_counts.put(type, emotion_counts.get(type) + 1);
        }

        // return the map of counts for display
        return emotion_counts;
    }

    /* Given the map of emotion type to count, display the emotion counts. */
    public void displayEmotionCounts(Map<String, Integer> emotion_counts) {

        // initialize the six textviews where the counts will display
        TextView joy = findViewById(R.id.joyCount);
        TextView fear = findViewById(R.id.fearCount);
        TextView anger = findViewById(R.id.angerCount);
        TextView sadness = findViewById(R.id.sadCount);
        TextView surprise = findViewById(R.id.surpriseCount);
        TextView love = findViewById(R.id.loveCount);

        // set the text of the views to the given count
        joy.setText(emotion_counts.get("Joy").toString());
        fear.setText(emotion_counts.get("Fear").toString());
        anger.setText(emotion_counts.get("Anger").toString());
        sadness.setText(emotion_counts.get("Sadness").toString());
        surprise.setText(emotion_counts.get("Surprise").toString());
        love.setText(emotion_counts.get("Love").toString());

    }
}