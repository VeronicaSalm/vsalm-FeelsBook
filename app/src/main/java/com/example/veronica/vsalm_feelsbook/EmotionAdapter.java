package com.example.veronica.vsalm_feelsbook;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EmotionAdapter extends ArrayAdapter<Emotion> {

    ArrayList<Emotion> emotions;
    Context context;

    public EmotionAdapter(Context context, ArrayList<Emotion> emotions) {
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
            viewHolder.type = (TextView) convertView.findViewById(R.id.type);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.comment);
            viewHolder.type.setText(emotion.getTypeString());
            viewHolder.date.setText(emotion.getTimestampString());
            viewHolder.comment.setText(emotion.getComment());

            viewHolder.delete = (ImageButton) convertView.findViewById(R.id.delete);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Toast.makeText(getContext(), "Delete " + position, Toast.LENGTH_SHORT).show();

                    ((ViewHistoryActivity) context).deleteEmotion(position);

                    EmotionAdapter.this.notifyDataSetChanged();
                }
            });
            viewHolder.edit = (ImageButton) convertView.findViewById(R.id.edit);
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Toast.makeText(getContext(), "Edit " + position, Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(viewHolder);
        }

        else {
            myViewHolder = (ViewHolder) convertView.getTag();
            myViewHolder.type.setText(emotion.getTypeString());
            myViewHolder.date.setText(emotion.getTimestampString());
            myViewHolder.comment.setText(emotion.getComment());

        }





        return convertView;
    }

    public class ViewHolder {
        ImageButton edit;
        TextView comment;
        TextView date;
        TextView type;
        ImageButton delete;
    }

}