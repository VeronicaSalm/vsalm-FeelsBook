package com.example.veronica.vsalm_feelsbook;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        toolbar.setTitle("History");

        ArrayList<Emotion> emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        adapter=new EmotionAdapter(this, emotions);

        emotionView = (ListView) findViewById(R.id.emotionList);
        emotionView.setAdapter(adapter);

        emotionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ViewHistoryActivity.this, "You clicked on an item!", Toast.LENGTH_SHORT).show();

            }
        });

        adapter.notifyDataSetChanged();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
