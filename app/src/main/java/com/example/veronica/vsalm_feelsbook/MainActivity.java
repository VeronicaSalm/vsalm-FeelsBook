package com.example.veronica.vsalm_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // the main activity tracks a list of emotions
    private ArrayList<Emotion> emotions=new ArrayList<Emotion>();
    // needed for retrieving modified emotion list from the history view
    private int EMOTIONS_BACK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set xml layout to the main activity
        setContentView(R.layout.activity_main);

        // use of custom toolbar for stats and history pages
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set up button listeners for each emotion type
        // when a button is clicked, create the appropriate emotion
        // and pass it to addEmotion for comment length checking and
        // addition to the emotion list
        final ImageButton joy_button = findViewById(R.id.joyButton);
        joy_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion joy = new Joy(comment.getText().toString());
                addEmotion(v, joy);
            }
        });

        final ImageButton fear_button = findViewById(R.id.fearButton);
        fear_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion fear = new Fear(comment.getText().toString());
                addEmotion(v, fear);
            }
        });

        final ImageButton anger_button = findViewById(R.id.angerButton);
        anger_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion anger = new Anger(comment.getText().toString());
                addEmotion(v, anger);
            }
        });

        final ImageButton sad_button = findViewById(R.id.sadButton);
        sad_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion sad = new Sadness(comment.getText().toString());
                addEmotion(v, sad);
            }
        });

        final ImageButton surprised_button = findViewById(R.id.surprisedButton);
        surprised_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion surprised = new Surprise(comment.getText().toString());
                addEmotion(v, surprised);
            }
        });

        final ImageButton love_button = findViewById(R.id.loveButton);
        love_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion love = new Love(comment.getText().toString());
                addEmotion(v, love);
            }
        });

    }

    public void addEmotion(View view, Emotion e) {

        if (EditEmotion.validCommentLength(e.getComment())) {
            emotions.add(e);
//            viewHistory();
            emotions = EditEmotion.sortEmotions(emotions);
            Toast.makeText(this, "Added " + e.getTypeString() + " emotion", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Comment must be at most 100 characters, not " + e.getComment().length(), Toast.LENGTH_LONG).show();
        }


    }

    public void viewHistory() {
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        intent.putExtra("Emotions", emotions);
        startActivityForResult(intent, EMOTIONS_BACK);
    }

    public void viewStats() {
        Intent intent = new Intent(this, ViewStatsActivity.class);
        intent.putExtra("Emotions", emotions);
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
