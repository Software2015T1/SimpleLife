package com.example.user.simplelife;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class ChartAirActivity extends Chart {

    private LinearLayout chartLayout;
    private View vChart;
    private String[][] airUsage = { {"ADFU1","20"},{"MBPW2","19"},{"ABCDE","17"} };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_air);
        ImageButton btnBack = (ImageButton) findViewById(R.id.ibtnBack_chartAir);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chartLayout = (LinearLayout) findViewById(R.id.layout_lightChart);

        try{
            vChart = drawChart("Hours", airUsage);
            chartLayout.removeAllViews();
            //llBarChart.addView(vChart);
            chartLayout.addView(vChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1300));

        }catch(Exception e){

        }
    }


    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chart_air, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
