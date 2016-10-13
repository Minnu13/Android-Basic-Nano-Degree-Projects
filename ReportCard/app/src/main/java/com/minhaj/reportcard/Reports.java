package com.minhaj.reportcard;

/**
 * Created by Minhaj on 02-Oct-16.
 */
public class Reports {

    private int mEng;
    private int mMath;
    private int mJava;
    private int mAndroid;
    private int mHtml;

    private char mGrades;
    private int mPercentage;

    public Reports(int eng, int math, int java, int android, int html, char grades, int percentage) {
        mEng = eng;
        mMath = math;
        mJava = java;
        mAndroid = android;
        mHtml = html;
        mGrades = grades;
        mPercentage = percentage;
    }

    public int getmAndroid() {
        return mAndroid;
    }

    public int getmEng() {
        return mEng;
    }

    public char getmGrades() {
        return mGrades;
    }

    public int getmHtml() {
        return mHtml;
    }

    public int getmJava() {
        return mJava;
    }

    public int getmMath() {
        return mMath;
    }

    public int getmPercentage() {
        return mPercentage;
    }
}