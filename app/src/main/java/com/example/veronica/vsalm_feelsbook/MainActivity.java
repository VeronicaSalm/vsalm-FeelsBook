package com.example.veronica.vsalm_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
//    public static final String EXTRA_MESSAGE = "com.example.vsalm_feelsbook.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);
        toolbar.setTitle(R.string.app_name);

        final ImageButton joy_button = findViewById(R.id.joyButton);
        joy_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion joy = new Joy();
                addEmotion(v, joy);
            }
        });

        final ImageButton fear_button = findViewById(R.id.fearButton);
        fear_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion fear = new Fear();
                addEmotion(v, fear);
            }
        });

        final ImageButton anger_button = findViewById(R.id.angerButton);
        anger_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion anger = new Anger();
                addEmotion(v, anger);
            }
        });

        final ImageButton sad_button = findViewById(R.id.sadButton);
        sad_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion sad = new Sadness();
                addEmotion(v, sad);
            }
        });

        final ImageButton surprised_button = findViewById(R.id.surprisedButton);
        surprised_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion surprised = new Surprise();
                addEmotion(v, surprised);
            }
        });

        final ImageButton love_button = findViewById(R.id.loveButton);
        love_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion love = new Love();
                addEmotion(v, love);
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
//        EditText editText = (EditText) findViewById(R.id.editText);
//        ImageButton love = (ImageButton) findViewById(R.id.loveButton);
//        String message = editText.getText().toString();
        intent.putExtra("Emotion", e);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
