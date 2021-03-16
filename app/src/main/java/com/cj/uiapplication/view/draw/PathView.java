package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Paint paint;
    Path path;
    Path path1;
    private void init() {
        paint = new Paint();
        path = new Path();
        path1 = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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

        path.addCircle(300,300,200, Path.Direction.CW);
        canvas.drawPath(path,paint);
        //---------------------------通过path添加其他图形绘制图形 跟直接使用canvas回图形没有区别

        paint.setColor(Color.BLACK);
        path1.lineTo(100,100);//相对当前位置的绝对值
        path1.rLineTo(100,0);//相对当前位置  相对值
        canvas.drawPath(path1,paint);

        //二次贝塞尔曲线 由三个点 起始点 控制点 和 终点

    }
}
