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


public class ChartAirActivity extends ActionBarActivity {

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
                Intent intent = new Intent(ChartAirActivity.this, ApplianceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                intent.putExtras(bundle);
                startActivity(intent);
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

    private View drawChart(String YTitle, String[][] xy) {
        XYSeries Series = new XYSeries(YTitle);

        XYMultipleSeriesDataset Dataset = new XYMultipleSeriesDataset();
        Dataset.addSeries(Series);

        XYMultipleSeriesRenderer Renderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer yRenderer = new XYSeriesRenderer();
        Renderer.addSeriesRenderer(yRenderer);

        //Renderer.setApplyBackgroundColor(true);			//�]�w�I���C��
        //Renderer.setBackgroundColor(Color.BLACK);			//�]�w�Ϥ���I���C��
        Renderer.setMarginsColor(Color.WHITE);				//�]�w�ϥ~��I���C��
        Renderer.setTextTypeface(null, Typeface.NORMAL);		//�]�w��rstyle
        Renderer.setMargins(new int[]{50, 70, 300, 50});       //�]�w�ϥ~����ɤj�p(�W �� �U �k)
        Renderer.setShowLegend(false);                        //�]�w����ܼ���

        Renderer.setShowGrid(true);							//�]�w����
        Renderer.setGridColor(Color.GRAY);					//�]�w�����C��

        //Renderer.setChartTitle(chartTitle);					//�]�w���Y��r
        Renderer.setLabelsColor(Color.argb(255, 84, 107, 122));				    //�]�w���Y��r�C��
        //Renderer.setChartTitleTextSize(20);					//�]�w���Y��r�j�p
        Renderer.setAxesColor(Color.BLACK);					//�]�w���b�C��
        Renderer.setBarSpacing(4);						        //�]�wbar�����Z��

        //Renderer.setXTitle(XTitle);						//�]�wX�b��r
        Renderer.setYTitle(YTitle);						   //�]�wY�b��r
        Renderer.setAxisTitleTextSize(40);
        Renderer.setXLabelsColor(Color.argb(255, 84, 107, 122));				//�]�wX�b��r�C��
        Renderer.setYLabelsColor(0, Color.argb(255, 84, 107, 122));			//�]�wY�b��r�C��
        Renderer.setLabelsTextSize(40);                     //�]�w��r�j�p
        Renderer.setXLabelsAlign(Paint.Align.CENTER);				//�]�wX�b��r�m��
        Renderer.setYLabelsAlign(Paint.Align.CENTER);				//�]�wY�b��r�m��
        Renderer.setXLabelsAngle(65); 						//�]�wX�b��r�ɱ׫�

        Renderer.setXLabels(0); 							//�]�wX�b����ܼƦr, ��H�{���]�w��r
        Renderer.setYAxisMin(0);							//�]�wY�b��̤p��

        yRenderer.setColor(Color.argb(255, 249, 205, 5));    //�]�wSeries�C��
        //yRenderer.setDisplayChartValues(true);			//�i�{Series�ƭ�

        Series.add(0, 0);
        Renderer.addXTextLabel(0, "");
        for(int r=0; r<xy.length; r++) {
            //Log.i("DEBUG", (r+1)+" "+xy[r][0]+"; "+xy[r][1]);
            Renderer.addXTextLabel(r+1, xy[r][0]);
            Series.add(r+1, Integer.parseInt(xy[r][1]));
        }
        Series.add(11, 0);
        Renderer.addXTextLabel(xy.length+1, "");

        View view = ChartFactory.getBarChartView(getBaseContext(), Dataset, Renderer, BarChart.Type.DEFAULT);
        return view;
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
