package com.example.veronica.vsalm_feelsbook;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmotionAdapter extends ArrayAdapter<Emotion> {
    public EmotionAdapter(Context context, ArrayList<Emotion> emotions) {
        super(context, 0, emotions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Emotion emotion = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_view_history, parent, false);
        }
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView comment = (TextView) convertView.findViewById(R.id.comment);
//        ImageView emoji = (ImageView) convertView.findViewById(R.id.emoji);
        type.setText(emotion.getTypeString());
        date.setText(emotion.getTimestampString());
        comment.setText(emotion.getComment());
        return convertView;
    }
}