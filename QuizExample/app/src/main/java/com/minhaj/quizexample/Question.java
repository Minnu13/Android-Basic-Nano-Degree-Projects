package com.minhaj.quizexample;

public class Question {
    private int ID;
    private String mQUESTION;
    private String mOptionA;
    private String mOptionB;
    private String mOptionC;
    private String mANSWER;

    public Question() {
        ID = 0;
        mQUESTION = "";
        mOptionA = "";
        mOptionB = "";
        mOptionC = "";
        mANSWER = "";
    }

    public Question(String QUESTION, String optionA, String optionB, String optionC, String ANSWER) {

        mQUESTION = QUESTION;
        mOptionA = optionA;
        mOptionB = optionB;
        mOptionC = optionC;
        mANSWER = ANSWER;
    }

    public int getID() {
        return ID;
    }

    //setter...
    public void setID(int id) {
        ID = id;
    }

    public void setmQUESTION(String mQUESTION) {
        this.mQUESTION = mQUESTION;
    }

    public void setmANSWER(String mANSWER) {
        this.mANSWER = mANSWER;
    }

    public void setmOptionB(String mOptionB) {
        this.mOptionB = mOptionB;
    }

    public void setmOptionA(String mOptionA) {
        this.mOptionA = mOptionA;
    }

    public void setmOptionC(String mOptionC) {
        this.mOptionC = mOptionC;
    }


    //getter..
    public String getmQUESTION() {
        return mQUESTION;
    }

    public String getmANSWER() {
        return mANSWER;
    }

    public String getmOptionA() {
        return mOptionA;
    }

    public String getmOptionB() {
        return mOptionB;
    }

    public String getmOptionC() {
        return mOptionC;
    }
}
