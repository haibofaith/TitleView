package com.simple.myviews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.simple.myviews.Views.TitleView;

public class MainActivity extends Activity {
    private TitleView title_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setOnClick();
    }

    private void initView() {
        //1.自定义view的第一个应用：复用标题栏
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitleText("主页");
        title_view.setLeftButtonText("返回");
    }

    public void chartview(View view){
        startActivity(new Intent(this,ChartViewActivity.class));
    }


    private void setOnClick() {
        //2.此处默认返回上一级，但也可重写该监听方法
//        title_view.setLeftButtonListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

}