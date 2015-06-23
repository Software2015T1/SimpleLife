package com.example.user.simplelife;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by User on 2015/6/23.
 */
public class Chart extends Activity{

    protected View drawChart(String YTitle, String[][] xy) {
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
}
