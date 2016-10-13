package com.minhaj.reportcard;

/**
 * Created by Minhaj on 02-Oct-16.
 */

public class Student {
    private String mName;
    private int mId;

    public Student(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }
}