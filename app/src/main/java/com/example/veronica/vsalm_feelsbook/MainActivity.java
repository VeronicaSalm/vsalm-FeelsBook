package com.example.veronica.vsalm_feelsbook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    public static final String EXTRA_MESSAGE = "com.example.vsalm_feelsbook.MESSAGE";
    private ArrayList<Emotion> emotions=new ArrayList<Emotion>();
    private int emotion_id;
    private int EMOTIONS_BACK = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotion_id = 0;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);
        toolbar.setTitleTextColor(Color.WHITE);

        final ImageButton joy_button = findViewById(R.id.joyButton);
        joy_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion joy = new Joy(emotion_id);
                addEmotion(v, joy);
                emotion_id += 1;
            }
        });

        final ImageButton fear_button = findViewById(R.id.fearButton);
        fear_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion fear = new Fear(emotion_id);
                addEmotion(v, fear);
                emotion_id += 1;
            }
        });

        final ImageButton anger_button = findViewById(R.id.angerButton);
        anger_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion anger = new Anger(emotion_id);
                addEmotion(v, anger);
                emotion_id += 1;
            }
        });

        final ImageButton sad_button = findViewById(R.id.sadButton);
        sad_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion sad = new Sadness(emotion_id);
                addEmotion(v, sad);
                emotion_id += 1;
            }
        });

        final ImageButton surprised_button = findViewById(R.id.surprisedButton);
        surprised_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion surprised = new Surprise(emotion_id);
                addEmotion(v, surprised);
                emotion_id += 1;
            }
        });

        final ImageButton love_button = findViewById(R.id.loveButton);
        love_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion love = new Love(emotion_id);
                addEmotion(v, love);
                emotion_id += 1;
            }
        });

    }


    public void viewEmotion(View view) {
        Intent intent = new Intent(this, ViewEmotionActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        ImageButton love = (ImageButton) findViewById(R.id.loveButton);
//        String message = editText.getText().toString();
//        intent.putExtra("Hello World", helloworld);
        startActivity(intent);
    }

    public void addEmotion(View view, Emotion e) {
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        emotions.add(e);
        intent.putExtra("Emotions", emotions);
        startActivityForResult(intent, EMOTIONS_BACK);
    }

    public void viewHistory() {
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        intent.putExtra("Emotions", emotions);
        startActivity(intent);
    }

    public void viewStats() {
        Intent intent = new Intent(this, ViewStatsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.hist_menu:
                viewHistory();
                return true;
            case R.id.stats:
                viewStats();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EMOTIONS_BACK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                emotions = (ArrayList<Emotion>) data.getSerializableExtra("Emotions");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
