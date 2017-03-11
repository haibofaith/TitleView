package com.simple.myviews.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.simple.myviews.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by user on 16/9/1.
 */
public class DataChartsView extends View {
    private int setPadding = 30;
    private Paint xLinePaint;// 坐标轴 轴线 画笔：
    private Paint titlePaint;// 绘制文本的画笔
    private Paint monthPaint;// 绘制文本的画笔
    private Paint paint;// 矩形画笔 柱状图的样式信息
    // 坐标轴底部的月份
    private ArrayList<String> xMonths;
    private ArrayList<String> text;
    // 柱状图占比
    private ArrayList<Double> yPercents;
//    private int[] text;
    private Bitmap bitmap;
    private int xLineColor = Color.parseColor("#f7f7f7");
    private int titleColor = Color.parseColor("#cccccc");
    private int monthColor = Color.parseColor("#444a54");
    private double showText;

    public DataChartsView(Context context) {
        super(context);
        init();
    }

    public DataChartsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 初始化月份
        xMonths = new ArrayList<>();
        for (int i=0;i<4;i++){
            xMonths.add("－月");
        }
        //初始化高度
        yPercents = new ArrayList<Double>();
        for (int i=0;i<4;i++){
            yPercents.add(0.0);
        }
        // 初始化数量
        text = new ArrayList<String>();
        for (int i=0;i<4;i++){
            text.add("－－");
        }
        xLinePaint = new Paint();
        titlePaint = new Paint();
        monthPaint = new Paint();
        paint = new Paint();
        // 给画笔设置颜色
        xLinePaint.setColor(xLineColor);
        xLinePaint.setAntiAlias(true);
        titlePaint.setColor(titleColor);
        titlePaint.setAntiAlias(true);
        titlePaint.setTextSize(sp2px(16));
        monthPaint.setColor(monthColor);
        monthPaint.setAntiAlias(true);
        monthPaint.setTextSize(sp2px(16));
        paint.setAntiAlias(true);// 抗锯齿效果
        // 加载画图
        bitmap = BitmapFactory
                .decodeResource(getResources(), R.drawable.bg_color_bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        //        绘制横向线条
        for (int i=1;i<6;i++){
        canvas.drawLine(dp2px(setPadding), height*i/6, width - dp2px(setPadding), height*i/6
                , xLinePaint);
        }

        // 设置底部的月份
        for (int i = 0; i < 4; i++) {
            // text, baseX, baseY, textPaint
            if (xMonths.get(i)!=null){
            float textWidth = monthPaint.measureText(xMonths.get(i)+"");
            canvas.drawText(xMonths.get(i), dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20 - textWidth/2, height-height/24
                    , monthPaint);
            }
        }

        for(int i = 0; i < 4; i++){
            showText = 0;
            Rect rect = new Rect();// 柱状图的形状
            rect.left = dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10;
            rect.right = dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 + (width-dp2px(setPadding*2))/10;
            rect.top = (int) (height*5/6 - yPercents.get(i)*(height*4/6));
            rect.bottom = height*5/6;
            canvas.drawBitmap(bitmap,null,rect, paint);
            float textWidth = titlePaint.measureText(getDatahandle(text.get(i))+"");
            float textWidth_null = titlePaint.measureText("NULL");

            try{
                showText = Double.parseDouble(text.get(i));
            }catch (Exception e){
                e.printStackTrace();
            }

            if(showText>0){
                canvas.drawText(getDatahandle(text.get(i)) + "",rect.left + (width-dp2px(setPadding*2))/20 - textWidth/2, rect.top - dp2px(10), titlePaint);
            }
            if(text.get(i)!=null&&text.get(i).equals("NULL")){
                canvas.drawText("NULL",rect.left + (width-dp2px(setPadding*2))/20 - textWidth_null/2, rect.top - dp2px(10), titlePaint);
            }
        }
    }

    public void setViewData(ArrayList<String> xMonths, ArrayList<String> text, ArrayList<Double> yPercents){
        this.xMonths = xMonths;
        this.text = text;
        this.yPercents = yPercents;
        invalidate();
    }

    private String getDatahandle(String string) {
        if ("NULL".equals(string)) {
            return "NULL";
        } else {
            // 将字符串转换成double型，去掉小数
            try {
                DecimalFormat f = new DecimalFormat(",###");
                long dbleData = (long) Math.round(Double
                        .parseDouble(string));
                return f.format(dbleData);
            } catch (Exception e) {
                e.printStackTrace();
                return "NULL";
            }
        }
    }

    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private int sp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }
}
