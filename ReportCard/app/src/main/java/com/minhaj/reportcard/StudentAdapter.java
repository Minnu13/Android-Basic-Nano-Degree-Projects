package com.minhaj.reportcard;

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

public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(Context context, ArrayList<Student> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.student_detail, parent, false);
        }

        //get object located at this position in the list
        Student currentStudent = getItem(position);

        TextView id = (TextView) listItemView.findViewById(R.id.id_of_student);
        id.setText(String.valueOf(currentStudent.getmId()));


        TextView name = (TextView) listItemView.findViewById(R.id.name_of_student);
        name.setText(currentStudent.getmName());

        return listItemView;
    }
}