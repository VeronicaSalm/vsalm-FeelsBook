package com.example.veronica.vsalm_feelsbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class EditEmotion {

    private Emotion e;
    private View popupView;
    private Context ctx;
    private View parentView;
    private ArrayList<Emotion> emotions;
    private int position;
    private PopupWindow popupWindow;
    private EmotionAdapter adapter;


    EditEmotion(Context ctx, View parentView,
                ArrayList<Emotion> emotions, int position) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.popupView = inflater.inflate(R.layout.edit_comment, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        this.popupWindow = new PopupWindow(popupView, width, height, focusable);

        this.e = emotions.get(position);
        this.ctx = ctx;
        this.parentView = parentView;
        this.emotions = emotions;
        this.position = position;

    }

    public Spinner setUpSpinner(View popupView, Emotion e) {
        Spinner spinner = (Spinner) popupView.findViewById(R.id.emotions_spinner);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(ctx,android.R.layout.simple_list_item_1,Emotion.getTypes());

        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);

        String[] types = Emotion.getTypes();
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(e.getTypeString())) {
                spinner.setSelection(i);
            }
        }

        return spinner;

    }

    public void deleteEmotion() {
        emotions.remove(position);
        // notify the adapter that the data has changed
        ((ViewHistoryActivity) ctx).notifyAdapter();

    }

    public void sortEmotions() {
        Collections.sort(emotions, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion e1, Emotion e2) {
                return e1.getTimestamp().compareTo(e2.getTimestamp());
            }
        });
    }

    private void editDate(Button date_button, final TextView date_text, final Calendar calendar) {
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ctx,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                Date d = calendar.getTime();
                                e.setTimestamp(d);

                                date_text.setText(e.getDateString());

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void editTime(Button time_button, final TextView time_text, final Calendar calendar) {
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(ctx,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                Date d = calendar.getTime();
                                e.setTimestamp(d);

                                time_text.setText(e.getTimeString());

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    private void setNewEmotion(final Spinner spinner, final EditText comment_box) {
        Button confirm_button = popupView.findViewById(R.id.confirm);

        confirm_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                String new_type = spinner.getSelectedItem().toString();
                String new_comment = comment_box.getText().toString();
                int id = e.getId();
                Date new_timestamp = e.getTimestamp();

                Emotion new_e;
                if (new_type.equals("Joy")) {
                    new_e = new Joy(id, new_comment, new_timestamp);
                }
                else if (new_type.equals("Fear")) {
                    new_e = new Fear(id, new_comment, new_timestamp);
                }
                else if (new_type.equals("Anger")) {
                    new_e = new Anger(id, new_comment, new_timestamp);
                }
                else if (new_type.equals("Love")) {
                    new_e = new Love(id, new_comment, new_timestamp);
                }
                else if (new_type.equals("Sadness")) {
                    new_e = new Sadness(id, new_comment, new_timestamp);
                }
                else if (new_type.equals("Surprise")) {
                    new_e = new Surprise(id, new_comment, new_timestamp);

                }
                else {
                    // TODO: Custom exception
                    throw new RuntimeException("Error! Emotion type not found.");

                }

                emotions.set(position, new_e);
                // sort here
                sortEmotions();

                // notify the adapter that the data has changed
                ((ViewHistoryActivity) ctx).notifyAdapter();
            }
        });
    }

    public void editEmotion() {

            final Spinner spinner = setUpSpinner(popupView, e);

            final EditText comment_box = popupView.findViewById(R.id.edit_comment_box);
            comment_box.setText(e.getComment());

            // date and time
            Date date = e.getTimestamp();

            final TextView date_text = popupView.findViewById(R.id.date_text);
            final TextView time_text = popupView.findViewById(R.id.time_text);
            Button date_button = popupView.findViewById(R.id.date_button);
            Button time_button = popupView.findViewById(R.id.time_button);
            date_text.setText(e.getDateString());
            time_text.setText(e.getTimeString());

            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            editDate(date_button, date_text, calendar);
            editTime(time_button, time_text, calendar);

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window token
            popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);


            setNewEmotion(spinner, comment_box);
        }
    }

