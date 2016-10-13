package com.minhaj.miwok;

import android.speech.tts.TextToSpeech;

/**
 * Created by Minhaj on 05-Aug-16.
 */
public class Word {

    private String mDefaultTranslation;

    private String mUrduTranslation;

    private int mImageResourceId = No_IMAGE_PROVIDED;

    private static final int No_IMAGE_PROVIDED = -1;
//
//    private int mAudioResourcesId;


    //Used two parameter cons cuz of phrases has no images
    public Word(String defaultTranslation, String urduTranslation) {
        mDefaultTranslation = defaultTranslation;
        mUrduTranslation = urduTranslation;
    }

    //Used becuz 2 textview and 1 image we have, call this con for those activities who has 2 tv, and 1 img
    public Word(String defaultTranslation, String urduTranslation, int imageResourceId) {

        mDefaultTranslation = defaultTranslation;
        mUrduTranslation = urduTranslation;
        mImageResourceId = imageResourceId;
    }

    public String getmUrduTranslation() {
        return mUrduTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /*Return the image resource ID of this word*/
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /*Returns whether or not there is an image for this word*/
    public boolean hasImage() {
        return mImageResourceId != No_IMAGE_PROVIDED;
    }

//    public int getmAudioResourcesId() {
//        return mAudioResourcesId;
//    }
}
