package com.minhaj.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class PhraseActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

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
        // words.add(new Word("", "", R.raw.audio_number_one));


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the Word obj at the given position the user click on
                Word word = words.get(position);
                textToSpeech.speak(word.getmDefaultTranslation(), TextToSpeech.QUEUE_FLUSH, null);

                //Create and setup the audio resource associated with th current word
//                mMediaPlayer = MediaPlayer.create(PhraseActivity.this, word.getmAudioResourcesId());
//                mMediaPlayer.start();

            }

            TextToSpeech textToSpeech = new TextToSpeech(PhraseActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        Toast.makeText(PhraseActivity.this, "English is speaking..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
