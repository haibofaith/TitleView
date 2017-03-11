package com.simple.myviews.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/3/9.
 */

public class PieChartView extends View {
    private int width;
    private int height;
    private int realRadius;
    private Paint paint;

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Paint paint5;

    private int pColor = Color.parseColor("#e4e4e4");

    private int color1 = Color.parseColor("#64b1e9");
    private int color2 = Color.parseColor("#77689e");
    private int color3 = Color.parseColor("#abcf00");
    private int color4 = Color.parseColor("#f7a20e");
    private int color5 = Color.parseColor("#fa6362");
    private RectF rectF;
    private double[] percent = {0, 0, 0, 0, 0};
    private int[] sweep = {0, 0, 0, 0, 0};

    public void setPercent(double[] percent) {
        this.percent = percent;
        for (int i = 0; i < 5; i++) {
            sweep[i] = (int) (percent[i]*360);
        }
        invalidate();
    }

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = getMeasuredWidth() / 2;
        height = getMeasuredHeight() / 2;
        paint = new Paint();
        paint.setColor(pColor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        paint1 = new Paint();
        paint1.setColor(color1);
        paint1.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setColor(color2);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setAntiAlias(true);

        paint3 = new Paint();
        paint3.setColor(color3);
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);
        paint3.setAntiAlias(true);

        paint4 = new Paint();
        paint4.setColor(color4);
        paint4.setStyle(Paint.Style.FILL_AND_STROKE);
        paint4.setAntiAlias(true);

        paint5 = new Paint();
        paint5.setColor(color5);
        paint5.setStyle(Paint.Style.FILL_AND_STROKE);
        paint5.setAntiAlias(true);

//        rectF = new RectF();
//        rectF.left = width - realRadius;
//        rectF.top = height - realRadius;
//        rectF.right = width + realRadius;
//        rectF.bottom = height + realRadius;

        if (height > width) {
            realRadius = width;
        } else {
            realRadius = height;
        }
        canvas.drawCircle(width, height, realRadius, paint);
        canvas.drawArc(new RectF(width - realRadius,height - realRadius,width + realRadius,height + realRadius), -90, sweep[0], true, paint1);
        canvas.drawArc(new RectF(width - realRadius,height - realRadius,width + realRadius,height + realRadius),-90+sweep[0],sweep[1],true,paint2);
        canvas.drawArc(new RectF(width - realRadius,height - realRadius,width + realRadius,height + realRadius),-90+sweep[0]+sweep[1],sweep[2],true,paint3);
        canvas.drawArc(new RectF(width - realRadius,height - realRadius,width + realRadius,height + realRadius),-90+sweep[0]+sweep[1]+sweep[2],sweep[3],true,paint4);
        if (360-sweep[0]-sweep[1]-sweep[2]-sweep[3]!=360){
        canvas.drawArc(new RectF(width - realRadius,height - realRadius,width + realRadius,height + realRadius),-90+sweep[0]+sweep[1]+sweep[2]+sweep[3],360-sweep[0]-sweep[1]-sweep[2]-sweep[3],true,paint5);
        }

    }
}
