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

/* Class: MainActivity

The homepage of the app. Responds to button presses
to record new emotions and records the comment as needed.

Will also pass the emotion list to the history and statistics
activities when they are instantiated.

 */
public class MainActivity extends AppCompatActivity {

    // the main activity tracks a list of emotions
    private ArrayList<Emotion> emotions;
    // needed for retrieving modified emotion list from the history view
    private int EMOTIONS_BACK = 1;
    private FileIO fio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the emotions from file (persistent data)
        this.fio = new FileIO();
        emotions = fio.loadFromFile(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

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
                addEmotion(joy);
            }
        });

        final ImageButton fear_button = findViewById(R.id.fearButton);
        fear_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion fear = new Fear(comment.getText().toString());
                addEmotion(fear);
            }
        });

        final ImageButton anger_button = findViewById(R.id.angerButton);
        anger_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion anger = new Anger(comment.getText().toString());
                addEmotion(anger);
            }
        });

        final ImageButton sad_button = findViewById(R.id.sadButton);
        sad_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion sad = new Sadness(comment.getText().toString());
                addEmotion(sad);
            }
        });

        final ImageButton surprised_button = findViewById(R.id.surprisedButton);
        surprised_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion surprised = new Surprise(comment.getText().toString());
                addEmotion(surprised);
            }
        });

        final ImageButton love_button = findViewById(R.id.loveButton);
        love_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comment = (EditText) findViewById(R.id.enterComment);
                Emotion love = new Love(comment.getText().toString());
                addEmotion(love);
            }
        });
    }

    @Override
    // ensure that the emotions are properly saved when the app is
    // closed or the MainActivity is paused
    protected void onPause() {
        fio.saveInFile(emotions, this);
        super.onPause();
    }

    /* Allows adding an emotion to the emotion list.
    First, the comment length is checked using a validating
    function from the EditEmotion class. If the comment is not
    too long, the emotion is added to the list.

    Otherwise, a Toast message is printed to the screen and the
    user must edit their comment and try again.
     */
    public void addEmotion(Emotion e) {

        if (EditEmotion.validCommentLength(e.getComment())) {
            emotions.add(e);
            emotions = EditEmotion.sortEmotions(emotions);
            Toast.makeText(this, "Added " + e.getTypeString() + " emotion", Toast.LENGTH_SHORT).show();
        }

        // if the comment is invalid, print a message to the user
        // do not add the emotion to the list
        else {
            Toast.makeText(this, "Comment must be at most 100 characters, not " + e.getComment().length(), Toast.LENGTH_LONG).show();
        }


    }

    // invokes the ViewHistoryActivity and passes the emotion list
    public void viewHistory() {
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        intent.putExtra("Emotions", emotions);
        startActivityForResult(intent, EMOTIONS_BACK);
    }

    // invokes the ViewStatsActivity and passes the emotion list
    public void viewStats() {
        Intent intent = new Intent(this, ViewStatsActivity.class);
        intent.putExtra("Emotions", emotions);
        startActivity(intent);
    }

    @Override
    // allow selection of items in the toolbar (the buttons to
    // view history and to view the emotion counts)
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            // if the item is the history button
            case R.id.hist_menu:
                // invoke the appropriate method
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
    // allows getting
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check that we are responding to the correct request to retrieve the emotions
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EMOTIONS_BACK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // if so, extract the emotion list
                emotions = (ArrayList<Emotion>) data.getSerializableExtra("Emotions");
            }
        }
    }

    @Override
    // inflate the options menu to view the buttons
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
