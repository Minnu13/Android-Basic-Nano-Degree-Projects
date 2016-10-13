package com.minhaj.reportcard;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Minhaj on 02-Oct-16.
 */

public class ReportActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grading_report);

//        reports = new ArrayList<>();
//
//        reports.add(new Reports(100, 80, 70, 60, 10, 'A', 80));
////        reports.add(new Reports(100, 80, 70, 60, 10, 'B', 70));
////        reports.add(new Reports(100, 80, 70, 60, 10, 'C', 60));
////        reports.add(new Reports(100, 80, 70, 60, 10, 'D', 50));
////        reports.add(new Reports(100, 80, 70, 60, 10, 'E', 40));
////        reports.add(new Reports(100, 80, 70, 60, 10, 'F', 35));
//
//        ReportAdapter adapter = new ReportAdapter(this, reports);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(adapter);
    }
}