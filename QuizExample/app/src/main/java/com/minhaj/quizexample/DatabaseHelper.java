package com.minhaj.quizexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "quizGame";
    // tasks table name
    private static final String TABLE_QUESTION = "question";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "ques";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTION_A = "option_a"; //option a
    private static final String KEY_OPTION_B = "option_b"; //option b
    private static final String KEY_OPTION_C = "option_c"; //option c

    private SQLiteDatabase sqLiteDatabase;

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDatabase = db;
        String sql =
                "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION +
                        " ( " + KEY_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION +
                        " TEXT, " + KEY_ANSWER +
                        " TEXT, " + KEY_OPTION_A +
                        " TEXT, " + KEY_OPTION_B +
                        " TEXT, " + KEY_OPTION_C +
                        " TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    private void addQuestions() {
        //Question class objects..
        Question question1, question2, question3, question4, question5, question6;

        question1 = new Question(" 50 - 15", "35", "39", "38", "35");
        this.addQuestion(question1);

        question2 = new Question(" What is the nature of this word 'Talkative'?", "Noun", "Adjective", "Adverb", "Adjective");
        this.addQuestion(question2);

        question3 = new Question(" Who was the founder of Pakistan", "Allama Iqbal", "Quaid e Azam", "Chaudry Rehmat Ali", "Quaid e Azam");
        this.addQuestion(question3);

        question4 = new Question(" Where is Karachi ?", "Pakistan", "Iraq", "Sindh", "Sindh");
        this.addQuestion(question4);

        question5 = new Question(" What is the meaning of word 'Grumpy'? ", "bad tempered", "wretch", "pious", "bad tempered");
        this.addQuestion(question5);

        question6 = new Question(" What is the synonym of 'Suffocate'? ", "stifle", "dia", "bless", "stifle");
        this.addQuestion(question6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_QUESTION, quest.getmQUESTION());
        values.put(KEY_ANSWER, quest.getmANSWER());
        values.put(KEY_OPTION_A, quest.getmOptionA());
        values.put(KEY_OPTION_B, quest.getmOptionB());
        values.put(KEY_OPTION_C, quest.getmOptionC());
        // Inserting Row
        sqLiteDatabase.insert(TABLE_QUESTION, null, values);
    }

    public List<Question> getAllQuestions() {

        List<Question> questionList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();

                question.setID(cursor.getInt(0));
                question.setmQUESTION(cursor.getString(1));
                question.setmANSWER(cursor.getString(2));
                question.setmOptionA(cursor.getString(3));
                question.setmOptionB(cursor.getString(4));
                question.setmOptionC(cursor.getString(5));

                questionList.add(question);
            } while (cursor.moveToNext());
        }
        // return quest list
        return questionList;
    }

    public int rowcount() {
        int row = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
    }
}
