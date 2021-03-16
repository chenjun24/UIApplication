package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundRectView extends View {
    public RoundRectView(Context context) {
        super(context);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        paint.setStrokeWidth(5);//线条宽度
        paint.setAntiAlias(true);//抗锯齿
        canvas.drawRoundRect(30,30,500,300,30,60,paint);
        //水平方向半径  竖直方向半径
        canvas.drawRoundRect(30,370,500,640,60,30,paint);

    }
}
