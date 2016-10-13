package com.minhaj.reportcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Minhaj on 02-Oct-16.
 */

public class StudentListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Student> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringArrayList = new ArrayList<Student>();
        String[] name = {"Minhaj", "Wahaj", "Faizan", "Saad", "Danish", "Ali"};

        for (int i = 0; i < name.length; i++) {
            stringArrayList.add(new Student(i + 1, name[i]));
        }
        StudentAdapter adapter = new StudentAdapter(this, stringArrayList);
        GridView gridView = (GridView) findViewById(R.id.gridlist);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Student student = stringArrayList.get(position);
        startActivity(new Intent(StudentListActivity.this,ReportActivity.class));
    }
}
