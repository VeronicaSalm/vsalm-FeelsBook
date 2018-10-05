package com.example.veronica.vsalm_feelsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/* Class: EmotionAdapter

A custom adapter class for an ArrayList of emotions.
Allows updating the listview of objects whenever the emotions
in the list change.

Contains a nested ViewHolder class used to define the view of
each emotion (row) in the ListView.

 */
public class EmotionAdapter extends ArrayAdapter<Emotion> {

    private ArrayList<Emotion> emotions;
    private Context context;

    // initialize the adapter and set the emotionList and context
    EmotionAdapter(Context context, ArrayList<Emotion> emotions) {
        super(context, R.layout.emotion_entry, emotions);
        this.emotions = emotions;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder myViewHolder = null;
        Emotion emotion = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emotion_entry, parent, false);
            ViewHolder viewHolder = new ViewHolder();

            // set the viewHolder attribute for each field of the emotion
            // in the listview
            viewHolder.type = convertView.findViewById(R.id.type);
            viewHolder.date = convertView.findViewById(R.id.date);
            viewHolder.comment = convertView.findViewById(R.id.comment);
            viewHolder.emoji = convertView.findViewById(R.id.emoji);

            // now set the values of the views to the emotion's values
            viewHolder.type.setText(emotion.getTypeString());
            viewHolder.date.setText(emotion.getTimestampString());
            viewHolder.comment.setText(emotion.getComment());
            viewHolder.emoji.setImageResource(emotion.getEmoji());

            // set up listener for the delete button
            viewHolder.delete = convertView.findViewById(R.id.delete);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    // when the delete button is pressed, remove the emotion from
                    // the list
                    EditEmotion edit = new EditEmotion(context, parent, emotions, position);
                    edit.deleteEmotion();
                }
            });

            // set up listener for the edit button
            viewHolder.edit = (ImageButton) convertView.findViewById(R.id.edit);
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    // when the edit button is pressed, create an instance
                    // of EditEmotion to allow the user to make necessary changes
                    // in a popup window
                    EditEmotion edit = new EditEmotion(context, parent, emotions, position);
                    edit.editEmotion();

                }
            });

            // set the tag so that next time we can access this view holder
            convertView.setTag(viewHolder);
        }

        else {

            // if the viewHolder is defined, get the
            myViewHolder = (ViewHolder) convertView.getTag();
            myViewHolder.type.setText(emotion.getTypeString());
            myViewHolder.date.setText(emotion.getTimestampString());
            myViewHolder.comment.setText(emotion.getComment());
            myViewHolder.emoji.setImageResource(emotion.getEmoji());
        }

        return convertView;
    }

    /* ViewHolder class (nested):

        Stores the fields needed for each row in the ListView.
    */
    public class ViewHolder {
        ImageButton edit;
        TextView comment;
        TextView date;
        TextView type;
        ImageButton delete;
        ImageView emoji;
    }

}