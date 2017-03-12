package com.simple.myviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by user on 16/9/1.
 */
public class KpiDataLineView extends View {

    private int width;
    private int height;

    private Paint xLinePaint;// 坐标轴 轴线 画笔：
    private Paint paintBac;//画背景白色圆
    private Paint paintLine;//画线
    private Paint paintText;//画文字
    private Paint paint;//画折线
    private Paint titlePaint;// 绘制月份的画笔
    private Paint paintYText;//画Y轴百分比
    private Paint paintOval;

    private int touchMonth = 0;

    private int xLineColor = Color.parseColor("#EEEEEE");
    //折线颜色
    private int circleColor = Color.parseColor("#50CF9F");
    //月份颜色
    private int titleColor = Color.parseColor("#545471");
    //Y轴百分比颜色
    private int yTextColor = Color.parseColor("#7E9CB4");


    public void setxMonths(ArrayList<String> xMonths) {
        this.xMonths = xMonths;
    }

    public void setyPercents(ArrayList<Double> yPercents) {
        this.yPercents = yPercents;
    }

    public void setDetailText(ArrayList<String> text) {
        this.text = text;
    }

    // 坐标轴底部的月份
    private ArrayList<String> xMonths;
    private ArrayList<Double> yPercents;
    private ArrayList<String> text;
    private String[] strings = {"100%", "80%", "60%", "40%", "20%", "0"};

    public KpiDataLineView(Context context) {
        super(context);
        init();
    }

    public KpiDataLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        xMonths = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            xMonths.add("--月");
        }
        yPercents = new ArrayList<>();
        for (int i=0;i<6;i++){
            yPercents.add(0.0);
        }
        //此处为假数据

        text = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
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
        titlePaint.setTextSize(sp2px(12));

        paint.setColor(circleColor);
        paint.setAntiAlias(true);
        paintBac.setColor(Color.WHITE);
        paintBac.setTextSize(sp2px(12));
        paintBac.setAntiAlias(true);
        paintLine.setColor(circleColor);
        paintLine.setStrokeWidth(dp2px(1));
        paintLine.setAntiAlias(true);
        paintText = new Paint();
        paintText.setColor(circleColor);
        paintText.setAntiAlias(true);
        paintText.setTextSize(sp2px(16));
        //Y轴
        paintYText = new Paint();
        paintYText.setColor(yTextColor);
        paintYText.setAntiAlias(true);
        paintYText.setTextSize(sp2px(12));
        //椭圆
        paintOval = new Paint();
        paintOval.setColor(circleColor);
        paintOval.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        super.onDraw(canvas);
        //        绘制纵向百分比
        for (int i = 1; i < 7; i++) {
            canvas.drawText(strings[i - 1], dp2px(2), height * i / 7 - sp2px(12), paintYText);
        }
        //        绘制横向线条
        for (int i = 1; i < 7; i++) {
            canvas.drawLine(0, height * i / 7, width, height * i / 7
                    , xLinePaint);
        }

        // 设置底部的月份
        for (int i = 0; i < 6; i++) {
            float textWidth = titlePaint.measureText(xMonths.get(i) + "");
            canvas.drawText(xMonths.get(i), width * (i + 1) / 7 - textWidth / 2, height - height / 24
                    , titlePaint);
        }
        //绘制折线
        for (int i = 0; i < 6; i++) {
            if (i + 1 < 6) {
                canvas.drawLine(width * (i + 1) / 7,
                        (int) (height * 6 / 7 - yPercents.get(i) * (height * 5 / 7)),
                        width * (i + 2) / 7,
                        (int) (height * 6 / 7 - yPercents.get(i + 1) * (height * 5 / 7)), paintLine);
            }
        }
        //根据触摸事件重绘
        if (touchMonth != 0) {
            int i = touchMonth;
            float textWidth = paintBac.measureText(xMonths.get(i - 1) + "");
            //垂直线
            canvas.drawLine(width * i / 7, height - height / 24, width * i / 7, (int) (height * 6 / 7 - yPercents.get(i - 1) * (height * 5 / 7)), paintLine);
            //白底绿圆
            canvas.drawCircle(width * i / 7, (int) (height * 6 / 7 - yPercents.get(i - 1) * (height * 5 / 7)), dp2px(5), paintBac);
            canvas.drawCircle(width * i / 7, (int) (height * 6 / 7 - yPercents.get(i - 1) * (height * 5 / 7)), dp2px(4), paintLine);
            //圆角矩形
            RectF oval = new RectF(width * (touchMonth) / 7 - textWidth / 2 - dp2px(2), height - height / 24 - sp2px(12), width * (touchMonth) / 7 + textWidth / 2 + dp2px(2), height - height / 24 + dp2px(2));
            canvas.drawRoundRect(oval, dp2px(4), dp2px(4), paintOval);
            canvas.drawText(xMonths.get(i - 1), width * (touchMonth) / 7 - textWidth / 2, height - height / 24
                    , paintBac);
            float percentWidth = paintYText.measureText(text.get(i-1) + "%");
            float IllDataWidth = paintYText.measureText("--");
            //百分比文字显示
            if ("-1".equals(text.get(i-1))){
                canvas.drawText("--", width * (touchMonth) / 7 - IllDataWidth / 2, (int) (height * 6 / 7 - yPercents.get(i - 1) * (height * 5 / 7) - sp2px(12)), paintYText);
            }else{
                canvas.drawText(text.get(i-1) + "%", width * (touchMonth) / 7 - percentWidth / 2, (int) (height * 6 / 7 - yPercents.get(i - 1) * (height * 5 / 7) - sp2px(12)), paintYText);
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x > width / 14 && x < width * 3 / 14) {
                    touchMonth = 1;
                    invalidate();
                }
                if (x > width * 3 / 14 && x < width * 5 / 14) {
                    touchMonth = 2;
                    invalidate();
                }
                if (x > width * 5 / 14 && x < width * 7 / 14) {
                    touchMonth = 3;
                    invalidate();
                }
                if (x > width * 7 / 14 && x < width * 9 / 14) {
                    touchMonth = 4;
                    invalidate();
                }
                if (x > width * 9 / 14 && x < width * 11 / 14) {
                    touchMonth = 5;
                    invalidate();
                }
                if (x > width * 11 / 14 && x < width * 13 / 14) {
                    touchMonth = 6;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (x > width / 14 && x < width * 3 / 14) {
                    touchMonth = 1;
                    invalidate();
                }
                if (x > width * 3 / 14 && x < width * 5 / 14) {
                    touchMonth = 2;
                    invalidate();
                }
                if (x > width * 5 / 14 && x < width * 7 / 14) {
                    touchMonth = 3;
                    invalidate();
                }
                if (x > width * 7 / 14 && x < width * 9 / 14) {
                    touchMonth = 4;
                    invalidate();
                }
                if (x > width * 9 / 14 && x < width * 11 / 14) {
                    touchMonth = 5;
                    invalidate();
                }
                if (x > width * 11 / 14 && x < width * 13 / 14) {
                    touchMonth = 6;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
//                    touchMonth = 0;
//                    invalidate();
                break;
        }
        return true;

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
