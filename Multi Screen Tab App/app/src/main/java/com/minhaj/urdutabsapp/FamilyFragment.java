package com.minhaj.urdutabsapp;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {


    public FamilyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.word_list, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Father", "باپ", R.drawable.family_father));
        words.add(new Word("Mother", "ماں", R.drawable.family_mother));
        words.add(new Word("Son", "بیٹا", R.drawable.family_son));
        words.add(new Word("Daughter", "بیٹی", R.drawable.family_daughter));
        words.add(new Word("Older Brother", "بڑا بھائی", R.drawable.family_older_brother));
        words.add(new Word("Younger Brother", "چھوٹا بھائی", R.drawable.family_younger_brother));
        words.add(new Word("Older Sister", "بڑی بہن", R.drawable.family_older_sister));
        words.add(new Word("Younger Sister", "چھوٹی بہن", R.drawable.family_younger_sister));
        words.add(new Word("Grandmother", "نانی/دادی", R.drawable.family_grandmother));
        words.add(new Word("Grandfather", "نانا/دادا", R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

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
                        // Toast.makeText(NumbersActivity.this, "English is speaking..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
        return rootview;
    }
}
