package com.simple.myviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by user on 16/9/1.
 */
public class DataLineView extends View {
    private Paint xLinePaint;// 坐标轴 轴线 画笔：
    private Paint titlePaint;// 绘制文本的画笔
    private Paint paint;//画圆
    private Paint paintBac;//画背景白色圆
    private Paint paintLine;//画线
    private Paint paintText;//画文字


    private int xLineColor = Color.parseColor("#e4e4e4");
    private int titleColor = Color.parseColor("#444a54");
    private int circleColor = Color.parseColor("#47cab0");
    private int circleRedColor = Color.parseColor("#ff8585");
    private int setPadding = 30;
    // 坐标轴底部的月份
    private ArrayList<String> xMonths;
    private ArrayList<Double> yPercents;
    private ArrayList<String> text;

    public DataLineView(Context context) {
        super(context);
        init();
    }

    public DataLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        xMonths = new ArrayList<>();
        for (int i=0;i<4;i++){
            xMonths.add("－月");
        }
        yPercents = new ArrayList<>();
        for (int i=0;i<4;i++){
            yPercents.add(0.0);
        }
        text = new ArrayList<>();
        for (int i=0;i<4;i++){
            text.add("--");
        }
        xLinePaint = new Paint();
        titlePaint = new Paint();
        paint = new Paint();
        paintBac = new Paint();
        paintLine = new Paint();
        // 给画笔设置颜色
        xLinePaint.setColor(xLineColor);
        xLinePaint.setAntiAlias(true);
        titlePaint.setColor(titleColor);
        titlePaint.setAntiAlias(true);
        titlePaint.setTextSize(sp2px(16));
        paint.setColor(circleColor);
        paint.setAntiAlias(true);
        paintBac.setColor(Color.WHITE);
        paintBac.setAntiAlias(true);
        paintLine.setColor(circleColor);
        paintLine.setStrokeWidth(dp2px(5));
        paintLine.setAntiAlias(true);
        paintText = new Paint();
        paintText.setColor(circleColor);
        paintText.setAntiAlias(true);
        paintText.setTextSize(sp2px(16));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        super.onDraw(canvas);
        //        绘制横向线条
        for (int i=1;i<6;i++){

            canvas.drawLine(dp2px(setPadding), height*i/6, width - dp2px(setPadding), height*i/6
                    , xLinePaint);
        }

        // 设置底部的月份
        for (int i = 0; i < 4; i++) {
            // text, baseX, baseY, textPaint
            float textWidth = titlePaint.measureText(xMonths.get(i)+"");
            canvas.drawText(xMonths.get(i), dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20 - textWidth/2, height-height/24
                    , titlePaint);
        }
        //绘制折线
        for (int i=0;i<4;i++){
            if (i+1<4) {
                canvas.drawLine(dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20,(int)(height*5/6-yPercents.get(i)*(height*4/6)),dp2px(setPadding) + (width - dp2px(setPadding * 2)) * 3 * (i + 1) / 10 + (width - dp2px(setPadding * 2)) / 20, (int) (height * 5 / 6 - yPercents.get(i+1) * (height * 4 / 6)),paintLine);
            }
        }

        //绘制圆
        for(int i = 0; i < 4; i++){
            canvas.drawCircle(dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20,(int)(height*5/6-yPercents.get(i)*(height*4/6)),dp2px(8),paintBac);
            if (i==3){
                paint.setColor(circleRedColor);
            }else{
                paint.setColor(circleColor);
            }
            canvas.drawCircle(dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20,(int)(height*5/6-yPercents.get(i)*(height*4/6)),dp2px(7),paint);
            float textWidth = paintText.measureText(text.get(i)+"%");
            float textWidth_null = paintText.measureText("NULL");
            if (text.get(i)!=null&&!text.get(i).equals("NULL")){
                canvas.drawText(text.get(i)+"%",dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20-textWidth/2,(int)(height*5/6-yPercents.get(i)*(height*4/6)-dp2px(10)),paintText);
            }else if(text.get(i).equals("NULL")){
                canvas.drawText("NULL",dp2px(setPadding) + (width-dp2px(setPadding*2))*3*i/10 +(width-dp2px(setPadding*2))/20-textWidth_null/2,(int)(height*5/6-yPercents.get(i)*(height*4/6)-dp2px(10)),paintText);
            }
        }

    }

    public void setViewData(ArrayList<String> xMonths, ArrayList<Double> yPercents){
        this.xMonths = xMonths;
//        this.text = text;
        this.yPercents = yPercents;
        invalidate();
    }
    public void setViewData(ArrayList<String> xMonths, ArrayList<String> text, ArrayList<Double> yPercents){
        this.xMonths = xMonths;
        this.text = text;
        this.yPercents = yPercents;
        invalidate();
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
