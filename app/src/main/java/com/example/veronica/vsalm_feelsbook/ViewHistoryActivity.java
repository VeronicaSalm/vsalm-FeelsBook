package com.example.veronica.vsalm_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;

public class ViewHistoryActivity extends AppCompatActivity {
    EmotionAdapter adapter;
    ListView emotionView;
    ArrayList<Emotion> emotions;
    FileIO fio = new FileIO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("History");
    }

    @Override
    protected void onPause() {
        fio.saveInFile(emotions, this);
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        adapter = new EmotionAdapter(this, emotions);

        emotionView = findViewById(R.id.emotionList);
        emotionView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Emotions", emotions);
        setResult(Activity.RESULT_OK, resultIntent);
    }

    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

}
