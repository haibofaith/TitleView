package com.simple.myviews;

import android.app.Activity;
import android.os.Bundle;

import com.simple.myviews.Views.DataChartsView;
import com.simple.myviews.Views.TitleView;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/11.
 */

public class ChartViewActivity extends Activity {
    private TitleView title_view;
    private DataChartsView chart_view;
    ArrayList<String> xMonths = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    ArrayList<Double> yPercents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view_layout);
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("柱状图");
        chart_view = (DataChartsView) findViewById(R.id.chart_view);
        xMonths.add("1月");
        xMonths.add("2月");
        xMonths.add("3月");
        xMonths.add("4月");
        text.add("30");
        text.add("40");
        text.add("80");
        text.add("10");
        yPercents.add(0.3);
        yPercents.add(0.4);
        yPercents.add(0.8);
        yPercents.add(0.1);
        chart_view.setViewData(xMonths,text,yPercents);
    }
}
