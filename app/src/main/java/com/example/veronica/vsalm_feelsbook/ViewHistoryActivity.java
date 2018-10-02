package com.example.veronica.vsalm_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewHistoryActivity extends AppCompatActivity {
    EmotionAdapter adapter;
    ListView emotionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);
        toolbar.setTitle(R.string.app_name);

        ArrayList<Emotion> emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

//        for (Emotion e: emotions) {
//            adapter.add(e.getEmotionString());
//        }


        adapter=new EmotionAdapter(this,
                emotions);

        emotionView = (ListView) findViewById(R.id.emotionList);
        emotionView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
