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
public class NumbersFragment extends Fragment {

    public NumbersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "ایک", R.drawable.number_one));
        words.add(new Word("Two", "دو", R.drawable.number_two));
        words.add(new Word("Three", "تین", R.drawable.number_three));
        words.add(new Word("Four", "چار", R.drawable.number_four));
        words.add(new Word("Five", "پانچ", R.drawable.number_five));
        words.add(new Word("Six", "چھ", R.drawable.number_six));
        words.add(new Word("Seven", "سات", R.drawable.number_seven));
        words.add(new Word("Eight", "آٹھ", R.drawable.number_eight));
        words.add(new Word("Nine", "نو", R.drawable.number_nine));
        words.add(new Word("Ten", "دس", R.drawable.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
        return rootView;
    }
}
