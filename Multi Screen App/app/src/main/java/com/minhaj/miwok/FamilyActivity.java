package com.minhaj.miwok;

import android.media.AudioManager;
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

public class FamilyActivity extends AppCompatActivity {


    private MediaPlayer mMediaPlayer;

    /*Handles audio focus when playing a sound file*/
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {


                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //means have regained focus , we can resume playBack
                        mMediaPlayer.start();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //means we have lost audio focus and stop playBackand cleanup resources
                        releaseMediaPlayer();

                    }
                }
            };


    /*This listener get triggered when Media player has completed playing audio file*/
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

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


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the Word obj at the given position the user click on
                Word word = words.get(position);

                textToSpeech.speak(word.getmDefaultTranslation(), TextToSpeech.QUEUE_FLUSH, null);

                //Result audio focus for palyback
//                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//                        //Use the music stream
//                        AudioManager.STREAM_MUSIC,
//                        //Request permanent focus
//                        AudioManager.AUDIOFOCUS_GAIN);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    //We have audio focus now..
//
//
//                    //Release the media player if it currently exists because
//                    // we are about to play different sound file
//                    releaseMediaPlayer();
//
//                    //Create and setup the audio resource associated with th current word
//                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourcesId());
//                    mMediaPlayer.start();
//
//                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
//                }

            }

            TextToSpeech textToSpeech = new TextToSpeech(FamilyActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        Toast.makeText(FamilyActivity.this, "English is speaking..", Toast.LENGTH_SHORT).show();
                    }
                }
            });



                /*int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //Use the music stream
                        AudioManager.STREAM_MUSIC,
                        //Request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have audio focus now..


                    //Release the media player if it currently exists because
                    // we are about to play different sound file
                    releaseMediaPlayer();

                    //Create and setup the audio resource associated with th current word
                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourcesId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }*/
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped,
        // releases the mediaPlayer resources
        // because we won't bwe playing any more sounds
        releaseMediaPlayer();
    }


    //Clean up the media player by releasing its resources
    public void releaseMediaPlayer() {

        if (mMediaPlayer != null) {
            //Regardless of the current state of the media player, release its reesources
            mMediaPlayer.release();
            //Set the media again back to null.
            mMediaPlayer = null;

            //Abandon audio focus when playBack complete
            //
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
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
