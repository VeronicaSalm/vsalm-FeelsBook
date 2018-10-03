package com.example.veronica.vsalm_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewStatsActivity extends AppCompatActivity {

    ArrayList<Emotion> emotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Statistics");

        emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        displayEmotionCount();


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//    }

    public Map<String, Integer> getEmotionCounts() {

        Map<String, Integer> emotion_counts = new HashMap<String, Integer>();

        for (String type : Emotion.getTypes()) {
            emotion_counts.put(type, 0);
        }

        for (Emotion e: emotions) {
            String type = e.getTypeString();
            emotion_counts.put(type, emotion_counts.get(type) + 1);
        }

        return emotion_counts;
    }

    public void displayEmotionCount() {

        Map<String, Integer> emotion_counts = getEmotionCounts();
        TextView joy = (TextView) findViewById(R.id.joyCount);
        TextView fear = (TextView) findViewById(R.id.fearCount);
        TextView anger = (TextView) findViewById(R.id.angerCount);
        TextView sadness = (TextView) findViewById(R.id.sadCount);
        TextView surprise = (TextView) findViewById(R.id.surpriseCount);
        TextView love = (TextView) findViewById(R.id.loveCount);
        joy.setText(emotion_counts.get("Joy").toString());
        fear.setText(emotion_counts.get("Fear").toString());
        anger.setText(emotion_counts.get("Anger").toString());
        sadness.setText(emotion_counts.get("Sadness").toString());
        surprise.setText(emotion_counts.get("Surprise").toString());
        love.setText(emotion_counts.get("Love").toString());


//        Toast.makeText(this, "There are " + emotion_counts.get("Joy") + " Joy emotions!", Toast.LENGTH_SHORT).show();
    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
}
