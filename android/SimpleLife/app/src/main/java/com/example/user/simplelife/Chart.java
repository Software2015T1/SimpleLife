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

        //Renderer.setApplyBackgroundColor(true);			//設定背景顏色
        //Renderer.setBackgroundColor(Color.BLACK);			//設定圖內圍背景顏色
        Renderer.setMarginsColor(Color.WHITE);				//設定圖外圍背景顏色
        Renderer.setTextTypeface(null, Typeface.NORMAL);		//設定文字style
        Renderer.setMargins(new int[]{50, 70, 300, 50});       //設定圖外圍邊界大小(上 左 下 右)
        Renderer.setShowLegend(false);                        //設定不顯示標籤

        Renderer.setShowGrid(true);							//設定網格
        Renderer.setGridColor(Color.GRAY);					//設定網格顏色

        //Renderer.setChartTitle(chartTitle);					//設定標頭文字
        Renderer.setLabelsColor(Color.argb(255, 84, 107, 122));				    //設定標頭文字顏色
        //Renderer.setChartTitleTextSize(20);					//設定標頭文字大小
        Renderer.setAxesColor(Color.BLACK);					//設定雙軸顏色
        Renderer.setBarSpacing(4);						        //設定bar間的距離

        //Renderer.setXTitle(XTitle);						//設定X軸文字
        Renderer.setYTitle(YTitle);						   //設定Y軸文字
        Renderer.setAxisTitleTextSize(40);
        Renderer.setXLabelsColor(Color.argb(255, 84, 107, 122));				//設定X軸文字顏色
        Renderer.setYLabelsColor(0, Color.argb(255, 84, 107, 122));			//設定Y軸文字顏色
        Renderer.setLabelsTextSize(40);                     //設定文字大小
        Renderer.setXLabelsAlign(Paint.Align.CENTER);				//設定X軸文字置中
        Renderer.setYLabelsAlign(Paint.Align.CENTER);				//設定Y軸文字置中
        Renderer.setXLabelsAngle(65); 						//設定X軸文字傾斜度

        Renderer.setXLabels(0); 							//設定X軸不顯示數字, 改以程式設定文字
        Renderer.setYAxisMin(0);							//設定Y軸文最小值

        yRenderer.setColor(Color.argb(255, 249, 205, 5));    //設定Series顏色
        //yRenderer.setDisplayChartValues(true);			//展現Series數值

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
