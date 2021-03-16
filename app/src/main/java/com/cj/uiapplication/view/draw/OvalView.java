package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class OvalView extends View {
    public OvalView(Context context) {
        super(context);
        init();
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        paint.setStrokeWidth(10);//线条宽度
        paint.setAntiAlias(true);//抗锯齿

        canvas.drawOval(50,50,350,200,paint);

        paint.setStyle(Paint.Style.FILL);//默认是这个
        canvas.drawOval(150,220,350,300,paint);//椭圆

        paint.setColor(Color.BLACK);
       // paint.setStyle(Paint.Style.FILL);没有影响  不是封闭的
        canvas.drawLine(50,50,300,300,paint);//线段
         float[] points = {10,300,200,300,100,300,100,400,10,400,200,400};
        canvas.drawLines(points,paint);//绘制多条线段
    }
}
