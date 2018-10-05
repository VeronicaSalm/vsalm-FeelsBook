package com.example.veronica.vsalm_feelsbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/* Class: EditEmotion

Allows editing of an emotion and alteration of
the emotion list via sorting. Also contains a function used
to check that comment length is valid.

*/
public class EditEmotion {

    // allow error handling of comment length
    private static final Integer MAX_CHARS = 100;


    // class variables for this emotion
    private Emotion e;
    private View popupView;
    private Context ctx;
    private View parentView;
    private ArrayList<Emotion> emotions;
    private int position;
    private PopupWindow popupWindow;

    // constructor inflates the popup view, allowing the edit screen
    // to be visible and set to a specific emotion
    EditEmotion(Context ctx, View parentView,
                ArrayList<Emotion> emotions, int position) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.popupView = inflater.inflate(R.layout.edit_comment, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        this.popupWindow = new PopupWindow(popupView, width, height, focusable);

        // set all other attributes as necessary
        this.e = emotions.get(position);
        this.ctx = ctx;
        this.parentView = parentView;
        this.emotions = emotions;
        this.position = position;

    }

    // initialize the spinner view to allow selection of a new emotion type
    // the emotion types are extracted from a type list in the emotion class
    // and displayed using a spinner to control user input
    public Spinner setUpSpinner(View popupView, Emotion e) {
        // attach the spinner to the emotion spinner layout
        Spinner spinner = popupView.findViewById(R.id.emotions_spinner);

        // the list of items to display here are the string types from
        // Emotion.getTypes()
        ArrayAdapter<String> sAdapter = new ArrayAdapter<>(ctx,
                                                    android.R.layout.simple_list_item_1,
                                                    Emotion.getTypes());
        // set a simple drop-down display
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);

        // set the initial spinner selection to the emotion's current type
        // (so if the user does not make a change, the emotion will default
        // to the current type)
        String[] types = Emotion.getTypes();
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(e.getTypeString())) {
                spinner.setSelection(i);
            }
        }

        // return the spinner so it can be used to get the selection later
        return spinner;

    }

    /* Allow removal of an emotion from the emotion list */
    public void deleteEmotion() {
        // first, remove the emotion from the specified index
        emotions.remove(position);

        // notify the adapter that the data has changed
        ((ViewHistoryActivity) ctx).notifyAdapter();
    }

    // Sort the emotions - this is needed after the date has been changed,
    // as the order may no longer be chronological
    public static ArrayList<Emotion> sortEmotions(ArrayList<Emotion> emotions) {
        // use a custom comparator class to sort the emotions
        // defined inline because this is the only place it is needed/called
        Collections.sort(emotions, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion e1, Emotion e2) {
                return e1.getTimestamp().compareTo(e2.getTimestamp());
            }
        });
        // the emotion list is now sorted
        return emotions;
    }

    /* Check that a comment entered by the user has a correct
    length before creating the new emotion. Return either true
    if the length is valid, or false otherwise. */
    public static boolean validCommentLength(String comment) {
        if (comment.length() <= MAX_CHARS) {
            return true;
        }
        else {
            return false;
        }
    }

    /* Code to set up the date picker dialog, date buttons and textviews,
    and allow selecting a new date */
    private void editDate(Button date_button, final TextView date_text, final Calendar calendar) {
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            // when the date button is pressed, the user should be able to edit the emotion date
            public void onClick(View v){

                // calendar is the current emotion date converted to a
                // calendar object. Extract the year, month, and day
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                // create the date picker dialog to allow controlled date choosing
                DatePickerDialog datePickerDialog = new DatePickerDialog(ctx,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                // convert back to a date object and set the new timestamp
                                Date d = calendar.getTime();
                                e.setTimestamp(d);

                                // give the user visual feedback that their selection worked
                                date_text.setText(e.getDateString());

                            }
                        }, mYear, mMonth, mDay);

                // display the dialog on button press
                datePickerDialog.show();
            }
        });
    }

    /* Sets up the time editing dialog when the edit time button is pressed
    allows the user to edit hour and minute of an emotion's timestamp */
    private void editTime(Button time_button, final TextView time_text, final Calendar calendar) {
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);

                // create the time picker dialog to allow controlled hour/minute choosing
                TimePickerDialog timePickerDialog = new TimePickerDialog(ctx,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                // convert back to a date object and set the new timestamp
                                // only the time will have changed
                                Date d = calendar.getTime();
                                e.setTimestamp(d);

                                // give the user visual feedback that their selection worked
                                time_text.setText(e.getTimeString());

                            }
                        }, mHour, mMinute, false);

                // display the dialog on button press
                timePickerDialog.show();
            }
        });
    }

    /* Given the spinner and comment box, and that the date of the emotion is
    already updated if necessary by the date and time button listeners,
    update the emotion by creating a new instance of the appropriate emotion
    subclass, along with modifying the comment if necessary.

    Finally, the new list is sorted (in case the date changed the order) and
    the EmotionAdapter for the list is updated that the data has been modified. */
    private void setNewEmotion(final Spinner spinner, final EditText comment_box) {
        Button confirm_button = popupView.findViewById(R.id.confirm);

        confirm_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                // first, when the confirm button is pressed, dismiss the popup window
                popupWindow.dismiss();

                // extract the type, comment, and date
                // these could be the old ones, or new ones
                // to simplify, we will treat them as new regardless
                String new_type = spinner.getSelectedItem().toString();
                String new_comment = comment_box.getText().toString();
                Date new_timestamp = e.getTimestamp();

                // check that the comment length is valid
                // if not, print a message to the user and revert to the old comment
                if (!EditEmotion.validCommentLength(new_comment)) {
                    Toast.makeText(ctx, "Comment must be at most 100 characters, not "
                                        + new_comment.length()
                                        + ". Comment not changed.", Toast.LENGTH_LONG).show();
                    new_comment = e.getComment();
                }


                // initialize a new emotion and set it to have the appropriate values
                Emotion new_e;
                if (new_type.equals("Joy")) {
                    new_e = new Joy(new_comment, new_timestamp);
                }
                else if (new_type.equals("Fear")) {
                    new_e = new Fear(new_comment, new_timestamp);
                }
                else if (new_type.equals("Anger")) {
                    new_e = new Anger(new_comment, new_timestamp);
                }
                else if (new_type.equals("Love")) {
                    new_e = new Love(new_comment, new_timestamp);
                }
                else if (new_type.equals("Sadness")) {
                    new_e = new Sadness(new_comment, new_timestamp);
                }
                else if (new_type.equals("Surprise")) {
                    new_e = new Surprise(new_comment, new_timestamp);

                }
                else {
                    // we will never get here, as the above statements cover
                    // all emotion types
                    throw new RuntimeException("Error! Emotion type not found.");
                }

                // set the new emotion in the list
                emotions.set(position, new_e);

                // sort the emotion list in case of changes
                emotions = sortEmotions(emotions);

                // notify the adapter that the data has changed
                ((ViewHistoryActivity) ctx).notifyAdapter();
            }
        });
    }

    /* This is the main starting point for emotion editing.

    Other functions in this class are called as needed to set up
    each TextView, button, and spinner, and set up listeners
    as appropriate. Finally, the new emotion is set when the user
    has finished the selection and pressed the Confirm button. */
    public void editEmotion() {

        // initialize the spinner (select emotion type)
        final Spinner typeSpinner = setUpSpinner(popupView, e);

        // initialize the edit text to the value of the current comment
        final EditText comment_box = popupView.findViewById(R.id.edit_comment_box);
        comment_box.setText(e.getComment());

        // get date and time
        Date date = e.getTimestamp();

        // initialize the date and time texts as appropriate
        // ensure that they are set to the values of the current emotion
        final TextView date_text = popupView.findViewById(R.id.date_text);
        final TextView time_text = popupView.findViewById(R.id.time_text);
        Button date_button = popupView.findViewById(R.id.date_button);
        Button time_button = popupView.findViewById(R.id.time_button);
        date_text.setText(e.getDateString());
        time_text.setText(e.getTimeString());

        // convert the date object to a calendar object
        // this allows use of date and time pickers
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        editDate(date_button, date_text, calendar);
        editTime(time_button, time_text, calendar);

        // center the popup window on the screen
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);

        // finally, set the new emotion when the user is finished
        setNewEmotion(typeSpinner, comment_box);
        }
    }

