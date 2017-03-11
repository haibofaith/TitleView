package com.simple.myviews;

import android.app.Activity;
import android.os.Bundle;

import com.simple.myviews.Views.PieChartView;
import com.simple.myviews.Views.TitleView;

/**
 * Created by user on 2017/3/12.
 */

public class PieChartActivity extends Activity {
    private TitleView title_view;
    private PieChartView pieChartView;
    double[] percents = {0.2,0.1,0.4,0.15,0.15};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart_view_layout);
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("饼状图");
        pieChartView = (PieChartView) findViewById(R.id.pieChartView);
        pieChartView.setPercent(percents);
    }
}
