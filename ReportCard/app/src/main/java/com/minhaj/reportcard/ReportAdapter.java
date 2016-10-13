package com.minhaj.reportcard;

/**
 * Created by Minhaj on 02-Oct-16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Minhaj on 02-Oct-16.
 */

public class ReportAdapter extends ArrayAdapter<Reports> {
    public ReportAdapter(Context context, ArrayList<Reports> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grading_report, parent, false);
        }
        //get object located at this position in the list
        Reports currentStudent = getItem(position);

        TextView eng = (TextView) listItemView.findViewById(R.id.english);
        eng.setText(String.valueOf("English          : " + currentStudent.getmEng()));

        TextView math = (TextView) listItemView.findViewById(R.id.math);
        math.setText(String.valueOf("Math            : " + currentStudent.getmMath()));

        TextView java = (TextView) listItemView.findViewById(R.id.java);
        java.setText(String.valueOf("Java            : " + currentStudent.getmJava()));

        TextView android = (TextView) listItemView.findViewById(R.id.android);
        android.setText(String.valueOf("Android       : " + currentStudent.getmAndroid()));

        TextView html = (TextView) listItemView.findViewById(R.id.html);
        html.setText(String.valueOf("HTML             : " + currentStudent.getmHtml()));

        TextView grade = (TextView) listItemView.findViewById(R.id.grades);
        grade.setText(String.valueOf("Grade           : " + currentStudent.getmGrades()));

        TextView percentage = (TextView) listItemView.findViewById(R.id.percentage);
        percentage.setText(String.valueOf("Percentage : " + currentStudent.getmPercentage() + "%"));

        return listItemView;
    }
}