package com.minhaj.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Minhaj on 05-Aug-16.
 */
public class WordAdapter extends ArrayAdapter<Word> {


    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get object located at this position in the list
        Word currentWord = getItem(position);

        TextView urduTextView = (TextView) listItemView.findViewById(R.id.urdu_text_view);
        //Get Urdu translation from the currentWord obj and
        // set this text on the Urdu TextView
        urduTextView.setText(currentWord.getmUrduTranslation());


        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_TextView);
        //Get default translation from the currentWord obj and
        // set this text on the Default TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }


        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //set the background color of the text container View
        textContainer.setBackgroundColor(color);


        //return the whole list item layout containning 2 TextViews
        return listItemView;
    }
}
