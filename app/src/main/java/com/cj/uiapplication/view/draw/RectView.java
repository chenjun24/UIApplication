package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RectView extends View {
    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Paint paint;
    private void init() {
        paint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);//
        //paint.setStyle(Paint.Style.FILL);//默认是这个
        paint.setStrokeWidth(20);//线条宽度
        paint.setAntiAlias(true);//抗锯齿
        canvas.drawRect(30,30,200,200,paint);

        paint.setStrokeWidth(40);//线条宽度
        canvas.drawPoint(250,250,paint);//Paint.Cap.BUTT默认

        paint.setStrokeWidth(40);//线条宽度
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(250,320,paint);

        paint.setStrokeWidth(40);//线条宽度
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(250,400,paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);//线条宽度
        float[] points = {0,0,50,50,50,200,200,50,100,100,150,50,150,100};
        canvas.drawPoints(points,2,8,paint);//跳过前面两个点 绘制8个数(即四个点)
    }
}
