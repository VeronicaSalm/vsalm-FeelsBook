package com.example.veronica.vsalm_feelsbook;

import android.graphics.Color;
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

public class ViewHistoryActivity extends AppCompatActivity{
    EmotionAdapter adapter;
    ListView emotionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ArrayList<Emotion> emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        adapter=new EmotionAdapter(this, emotions);

        emotionView = (ListView) findViewById(R.id.emotionList);
        emotionView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        long viewId = view.getId();
//
//        if (viewId == R.id.edit) {
//            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
//        } else if (viewId == R.id.delete) {
//            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "ListView clicked" + id, Toast.LENGTH_SHORT).show();
//        }
//    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
