package com.example.veronica.vsalm_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        toolbar.setSubtitle("History");
//        toolbar.setTitleTextColor(Color.WHITE);

        emotions = (ArrayList<Emotion>) getIntent().getSerializableExtra("Emotions");

        adapter = new EmotionAdapter(this, emotions);

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

    public void editEmotion(final int position, View view) {
        final Emotion e = emotions.get(position);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.edit_comment, null);


        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        final Spinner spinner = (Spinner) popupView.findViewById(R.id.emotions_spinner);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(ViewHistoryActivity.this,android.R.layout.simple_list_item_1,Emotion.getTypes());
////
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);

        final EditText comment_box = (EditText) popupView.findViewById(R.id.edit_comment_box);
        comment_box.setText(e.getComment());

        String[] types = Emotion.getTypes();
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(e.getTypeString())) {
                spinner.setSelection(i);
            }
        }

        Button confirm_button = (Button) popupView.findViewById(R.id.confirm);


        // date and time

        Date date = e.getTimestamp();

        TextView date_text = (TextView) popupView.findViewById(R.id.date_text);
        TextView time_text = (TextView) popupView.findViewById(R.id.time_text);
        date_text.setText(e.getDateString());
        time_text.setText(e.getTimeString());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);




//
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        confirm_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                int id = e.getId();
                String type = e.getTypeString();
                Date d = e.getTimestamp();


                String new_type = spinner.getSelectedItem().toString();
                String new_comment = comment_box.getText().toString();

                if (new_type.equals(type)) {
                    e.setComment(new_comment);
                    emotions.set(position, e);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Emotion new_e;
                    if (new_type.equals("Joy")) {
                        new_e = new Joy(id, new_comment, d);
                    }
                    else if (new_type.equals("Fear")) {
                        new_e = new Fear(id, new_comment, d);
                    }
                    else if (new_type.equals("Anger")) {
                        new_e = new Anger(id, new_comment, d);
                    }
                    else if (new_type.equals("Love")) {
                        new_e = new Love(id, new_comment, d);
                    }
                    else if (new_type.equals("Sadness")) {
                        new_e = new Sadness(id, new_comment, d);
                    }
                    else if (new_type.equals("Surprise")) {
                        new_e = new Surprise(id, new_comment, d);

                    }
                    else {
                        // we will never get here

                        new_e = new Surprise(id, new_comment, d);

                    }

                    emotions.set(position, new_e);
                    adapter.notifyDataSetChanged();
                }
            }
        });
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                popupWindow.dismiss();
//                return true;
//            }
//        });


//        e.setComment("Default comment set!");



    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
}
