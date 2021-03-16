package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ArcView extends View {
    public ArcView(Context context) {
        super(context);
        init();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        //椭圆里面绘制弧形
        canvas.drawArc(20,20,300,200,0,-90,true,paint);
        paint.setColor(Color.BLUE);
        //
        canvas.drawArc(20,20,300,200,-90,-180,false,paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);//
        //
        canvas.drawArc(20,20,300,200,-270,-90,true,paint);
    }
}
