package com.simple.myviews;

import android.app.Activity;
import android.os.Bundle;

import com.simple.myviews.Views.DataLineView;
import com.simple.myviews.Views.RadarView;
import com.simple.myviews.Views.TitleView;

import java.util.ArrayList;

/**
 * Created by user on 2017/3/12.
 */

public class RadarViewActivity extends Activity {
    private TitleView title_view;
    private RadarView radarview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radar_view_layout);
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("雷达图");
        radarview = (RadarView) findViewById(R.id.radarview);
    }
}
