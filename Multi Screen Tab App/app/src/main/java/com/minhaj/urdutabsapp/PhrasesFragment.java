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
public class PhrasesFragment extends Fragment {


    public PhrasesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.word_list, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Casting pearls before swine", "بندر کیا جانے ادرک کا سواد"));
        words.add(new Word("As you sow so shall you reap", "جیسی کرنی ویسی بھرنی"));
        words.add(new Word("Big difference in status or Class.", "کہاں راجہ بھوج کہاں گنگوتیلی"));
        words.add(new Word("To show anger after getting embarrassed", "کھسیانی بلی کھمبا نوچے"));
        words.add(new Word("More mouths will have more talks", "جتنے منہ اتنی باتیں"));
        words.add(new Word("To use the available opportunity", "بہتی گنگا میں ہاتھ دھونا"));
        words.add(new Word("Getting involved without having", "مان نہ مان میں تیرا مہمان"));
        words.add(new Word("A person of no principles", "گنگا گائے گنگا داس جمنا گائے جمنا داس"));
        words.add(new Word("One is afraid of his/her crime", "چور کی داڑھی میں تنکا"));
        words.add(new Word("Division is main reason for the damage", "گھر کا بھیدی لنکا ڈھائے"));
        words.add(new Word("Evidence does not need proof", "ہاتھ کنگن کو آرسی کیا"));
        words.add(new Word("A person try to be on two sides goes nowhere", "دھوبی کا کتا نہ گھر کا نہ گھاٹ کا"));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

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
