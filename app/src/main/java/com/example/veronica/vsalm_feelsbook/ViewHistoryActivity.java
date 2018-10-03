package com.example.veronica.vsalm_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewHistoryActivity extends AppCompatActivity {
    EmotionAdapter adapter;
    ListView emotionView;
    ArrayList<Emotion> emotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        adapter=new EmotionAdapter(this, emotions);

        emotionView = (ListView) findViewById(R.id.emotionList);
        emotionView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Emotions", emotions);
        setResult(Activity.RESULT_OK, resultIntent);
//        finish();
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        long viewId = view.getId();
//
//        Toast.makeText(this, "Something happened!", Toast.LENGTH_SHORT).show();
//
//        if (viewId == R.id.edit) {
//            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
//        } else if (viewId == R.id.delete) {
//            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "ListView clicked" + id, Toast.LENGTH_SHORT).show();
//        }
//    }



    public void deleteEmotion(int position) {
        emotions.remove(position);
    }

    public void editEmotion(int position, View view) {
        Emotion e = emotions.get(position);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.edit_comment, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        e.setComment("Default comment set!");

        emotions.set(position, e);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
