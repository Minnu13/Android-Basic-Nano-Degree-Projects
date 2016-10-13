package com.minhaj.quizexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    int score = 0;
    private int questionId = 0;

    List<Question> questionsList;
    Question currentQuestion;
    RadioGroup radioGroup;
    TextView txtQuestion;
    RadioButton radioButton1, radioButton2, radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        questionsList = databaseHelper.getAllQuestions();

        currentQuestion = questionsList.get(questionId);

        initializing();

        setQuestionView();

        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initializing() {
        txtQuestion = (TextView) findViewById(R.id.question_textView);
        radioButton1 = (RadioButton) findViewById(R.id.radio0);
        radioButton2 = (RadioButton) findViewById(R.id.radio1);
        radioButton3 = (RadioButton) findViewById(R.id.radio2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
    }


    public void nextButton(View view) {

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        Log.d("Right Answer is : ", currentQuestion.getmANSWER() + " " + " Your Answer : " + answer.getText());
        if (currentQuestion.getmANSWER().equals(answer.getText())) {
            score++;
            Log.d("score", "Your score: " + score);
        }
        if (questionId < 5) {
            currentQuestion = questionsList.get(questionId);
            setQuestionView();

        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("score", score); //Your score
            intent.putExtras(bundle); //Put your score to your next Intent
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

//        RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
//
//        if (currentQuestion.getmANSWER().equals(answer.getText())) {
//            Toast.makeText(getApplicationContext(), "Right answer", Toast.LENGTH_SHORT).show();
//            score++;
//        } else {
//            Toast.makeText(getApplicationContext(), "wrong answer", Toast.LENGTH_SHORT).show();
//        }
    }

    private void setQuestionView() {
        //setting question
        txtQuestion.setText(currentQuestion.getmQUESTION());
        //setting options
        radioButton1.setText(currentQuestion.getmOptionA());
        radioButton2.setText(currentQuestion.getmOptionB());
        radioButton3.setText(currentQuestion.getmOptionC());
        //incrementing
        questionId++;
    }
}
