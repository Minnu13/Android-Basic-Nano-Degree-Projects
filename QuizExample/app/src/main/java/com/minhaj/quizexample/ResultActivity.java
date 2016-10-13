package com.minhaj.quizexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        Button againButton = (Button) findViewById(R.id.button2);


        //get rating bar object
        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
        bar.setNumStars(6);
        bar.setStepSize(1.0f);
        //get text view
        TextView t = (TextView) findViewById(R.id.textResult);
        //get score
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        //display score
        bar.setRating(score);
//        t.setText(score);
        switch (score) {
            case 0:
            case 1:
                t.setText("You score 1 star");
                break;
            case 2:
                t.setText("You score 2 stars");
                break;
            case 3:
                t.setText("You score 3 stars");
                break;
            case 4:
                t.setText("You score 4 stars");
                break;
            case 5:
                t.setText("You score 5 stars");
                break;
        }
    }

    public void backToMainActivity(View view) {
        startActivity(new Intent(this, QuizActivity.class));
    }
}
