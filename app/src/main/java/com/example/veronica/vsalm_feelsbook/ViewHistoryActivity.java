package com.example.veronica.vsalm_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import java.util.ArrayList;


/* Class: ViewHistoryActivity

Creates and presents the listview for the emotions list,
with much of the work being done by an EmotionAdapter.

Is also responsible for saving the emotion list
whenever the activity pauses.

 */
public class ViewHistoryActivity extends AppCompatActivity {

    EmotionAdapter adapter;
    ListView emotionView;
    ArrayList<Emotion> emotions;
    FileIO fio = new FileIO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        // initialize the toolbar with title History
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("History");
    }

    @Override
    protected void onPause() {
        // make sure emotions are saved if the user quits the app
        fio.saveInFile(emotions, this);
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        // create a new emotion adapter for the listview
        adapter = new EmotionAdapter(this, emotions);

        // allow the adapter to display the emotion list and update when
        // it changes
        emotionView = findViewById(R.id.emotionList);
        emotionView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // prepare to pass the emotion list back to the main activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Emotions", emotions);
        setResult(Activity.RESULT_OK, resultIntent);
    }

    // A function that can be called from EditEmotion to notify the adapter
    // this allows only the ViewHistoryActivity to have direct access to
    // the private adapter
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

}
