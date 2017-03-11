package com.simple.myviews.Views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simple.myviews.R;

/**
 * Created by user on 2017/2/17.
 */

public class TitleView extends FrameLayout {

    private RelativeLayout back_button;
    private TextView back_button_text;
    private ImageView back_button_img;
    private ImageView right_button_img;
    private TextView title_text;
    private TextView right_button_text;
    private RelativeLayout right_button;


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title, this);
        back_button = (RelativeLayout) findViewById(R.id.back_button);
        right_button = (RelativeLayout) findViewById(R.id.right_button);
        back_button_img = (ImageView) findViewById(R.id.back_button_img);
        right_button_img = (ImageView) findViewById(R.id.right_button_img);
        back_button_text = (TextView) findViewById(R.id.back_button_text);
        right_button_text = (TextView) findViewById(R.id.right_button_text);
        title_text = (TextView) findViewById(R.id.title_text);
        //      左侧按钮 默认：返回上一级页面
        back_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

    }

    //    右侧按钮：是否可见
    public void setRightButtonVis() {
        right_button.setVisibility(View.VISIBLE);
    }

    //    设置标题文字
    public void setTitleText(String text) {
        title_text.setText(text);
    }

    //    设置左侧图片
    public void setLeftButtonImg(int res) {
        back_button_img.setBackgroundResource(res);
    }

    //    设置右侧图片
    public void setRightButtonImg(int res) {
        right_button_img.setBackgroundResource(res);
    }

    //    设置左侧按钮文案
    public void setLeftButtonText(String text) {
        back_button_text.setText(text);
    }

    //    设置左侧按钮监听事件
    public void setLeftButtonListener(OnClickListener li) {
        back_button.setOnClickListener(li);
    }

    //    设置右侧按钮监听事件
    public void setRightButtonListener(OnClickListener li){
        right_button.setOnClickListener(li);
    }

    //    设置左侧按钮：是否可见
    public void setLeftButtonImgGone() {
        back_button_img.setVisibility(GONE);
    }

    //    设置右侧按钮图片：是否可见
    public void setRightButtonImgVis() {
        right_button_img.setVisibility(VISIBLE);
    }

    //    设置右侧按钮文案
    public void setRightButtonText(String text) {
        right_button_text.setText(text);
    }

}
