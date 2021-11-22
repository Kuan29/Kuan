
package com.example.stepcounter;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class CombinedChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);

        //타이틀바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent graphintent = getIntent();
        Double bmigraph1 = graphintent.getDoubleExtra("bmigraph1",0.0);

        LineChart chart = findViewById(R.id.linechart);

        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < 30; i++) {

            float val = (float) (Math.random() * 10);
            values.add(new Entry(i, (int) val));
        }
        LineDataSet set1;
        set1 = new LineDataSet(values, "DataSet 1");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        // black lines and points
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        // set data
        chart.setData(data);
    }
    

}
