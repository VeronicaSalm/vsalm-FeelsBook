package com.example.veronica.vsalm_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
//    public static final String EXTRA_MESSAGE = "com.example.vsalm_feelsbook.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewEmotion(View view) {
        Intent intent = new Intent(this, ViewEmotionActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        ImageButton love = (ImageButton) findViewById(R.id.loveButton);
//        String message = editText.getText().toString();
//        intent.putExtra("Hello World", helloworld);
        startActivity(intent);
    }
}
