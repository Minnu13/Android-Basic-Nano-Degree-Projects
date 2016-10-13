package com.minhaj.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeam_A(0);
        displayForTeam_B(0);
    }

    //reset function..
    public void resetScore(View view) {
        scoreTeamA = 0;
        displayForTeam_A(scoreTeamA);
        scoreTeamB = 0;
        displayForTeam_B(scoreTeamB);
    }


    //display for team A
    public void displayForTeam_A(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_team_A);
        scoreView.setText(String.valueOf(score));
    }

    public void pointThreeFor_TeamA(View view) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeam_A(scoreTeamA);
    }

    public void pointTwoFor_TeamA(View view) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeam_A(scoreTeamA);
    }

    public void pointFreeThrowFor_TeamA(View view) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeam_A(scoreTeamA);
    }

    //display for team B
    public void displayForTeam_B(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_team_B);
        scoreView.setText(String.valueOf(score));
    }

    public void pointThreeFor_TeamB(View view) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeam_B(scoreTeamB);
    }

    public void pointTwoFor_TeamB(View view) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeam_B(scoreTeamB);
    }

    public void pointFreeThrowFor_TeamB(View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeam_B(scoreTeamB);
    }
}