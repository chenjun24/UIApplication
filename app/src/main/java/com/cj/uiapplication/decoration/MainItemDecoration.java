package com.cj.uiapplication.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 首先要知道绘制顺序
 * 1.RecyclerView 的背景
 * 2.onDraw()函数绘制的内容
 * 3.item的内容
 * 4.onDrawOver()函数绘制的内容
 * --------------------------------
 * getItemOffsets在每次测量item尺寸时被调用，将decoration的尺寸计算到item的尺寸中
 * 屏幕上item宽度 是ItemOffsets加上真正显示的item宽高
 */
public class MainItemDecoration extends RecyclerView.ItemDecoration {
    private final static String TAG = "MainItemDecoration";
    private Paint drawPaint;
    private Paint drawOverPaint;
    public MainItemDecoration(){
        drawPaint = new Paint();
        drawPaint.setColor(Color.RED);
        drawOverPaint = new Paint();
        drawOverPaint.setColor(Color.GREEN);
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.d(TAG, "onDraw: ");
        //c.drawRect(0,0,800,200,drawPaint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.d(TAG, "onDrawOver: ");

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        Log.d(TAG, "getItemOffsets: ");
        outRect.set(5, 5, 5, 5);
    }
}
