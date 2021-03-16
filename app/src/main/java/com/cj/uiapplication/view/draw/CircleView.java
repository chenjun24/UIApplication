package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {
    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //paint.setColor(Color.GREEN);
        //paint.setStyle(Paint.Style.STROKE);//
        paint.setStyle(Paint.Style.FILL);//默认是这个
       // paint.setStrokeWidth(2);//线条宽度
       // paint.setAntiAlias(true);//抗锯齿
        //
//        Shader shader = new LinearGradient(100,100,500,500,Color.parseColor("#667788"),
//                Color.parseColor("#e92244"),Shader.TileMode.CLAMP);
        Shader shader = new RadialGradient(300,300,200,Color.parseColor("#667788"),
                Color.parseColor("#e92244"),Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300,300,200,paint);
    }
}
