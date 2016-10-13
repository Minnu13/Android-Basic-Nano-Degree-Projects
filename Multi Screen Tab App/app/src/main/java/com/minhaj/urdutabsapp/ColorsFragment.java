package com.minhaj.urdutabsapp;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {


    public ColorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red", "لال", R.drawable.color_red));
        words.add(new Word("Mustard yellow", "پیلا", R.drawable.color_mustard_yellow));
        words.add(new Word("Dusty yellow", "پیلا", R.drawable.color_dusty_yellow));
        words.add(new Word("Green", "سبز", R.drawable.color_green));
        words.add(new Word("Brown", "بھورا", R.drawable.color_brown));
        words.add(new Word("gray", " سفید ملا سیاہی ", R.drawable.color_gray));
        words.add(new Word("Black", "کالا", R.drawable.color_black));
        words.add(new Word("White", "سفید", R.drawable.color_white));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = (ListView) rootview.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the Word obj at the given position the user click on
                Word word = words.get(position);

                textToSpeech.speak(word.getmDefaultTranslation(), TextToSpeech.QUEUE_FLUSH, null);
            }

            TextToSpeech textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        Toast.makeText(getContext(), "English is speaking..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
        return rootview;
    }
}
