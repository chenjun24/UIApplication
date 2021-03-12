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
public class DrawItemDecoration extends RecyclerView.ItemDecoration {
    private final static String TAG = "DrawItemDecoration";
    private Paint drawPaint;
    private Paint drawOverPaint;
    public DrawItemDecoration(){
        drawPaint = new Paint();
        drawPaint.setColor(Color.RED);

        drawOverPaint = new Paint();
        drawOverPaint.setColor(Color.BLUE);
        drawOverPaint.setStyle(Paint.Style.FILL);
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
        //这里是横向的LinearLayoutManager 所有item上下没有decoration 只设置左边跟右边(第一个item左边不设置  最后一个item右边不设置)
        Log.d(TAG, "onDrawOver: ");
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i=0;i<childCount;i++){
            View childAt = parent.getChildAt(i);
            int leftDecorationWidth = layoutManager.getLeftDecorationWidth(childAt);
            int topDecorationHeight = layoutManager.getTopDecorationHeight(childAt);
            int rightDecorationWidth = layoutManager.getRightDecorationWidth(childAt);
            int bottomDecorationHeight = layoutManager.getBottomDecorationHeight(childAt);

//            Log.d(TAG, "onDrawOver: position:"+i+" leftDecorationWidth:"+leftDecorationWidth
//                    +" rightDecorationWidth:"+rightDecorationWidth
//                    +" topDecorationHeight:"+topDecorationHeight
//                    +" bottomDecorationHeight:"+bottomDecorationHeight);
            int decoratedLeft = layoutManager.getDecoratedLeft(childAt);
            int decoratedTop = layoutManager.getDecoratedTop(childAt);
            int decoratedRight = layoutManager.getDecoratedRight(childAt);
            int decoratedBottom = layoutManager.getDecoratedBottom(childAt);
//            Log.d(TAG, "onDrawOver: position:"+i+" decoratedLeft:"+decoratedLeft
//                    +" decoratedTop:"+decoratedTop
//                    +" decoratedRight:"+decoratedRight
//                    +" decoratedBottom:"+decoratedBottom);
            int left = childAt.getLeft();
            int top = childAt.getTop();
            int right = childAt.getRight();
            int bottom = childAt.getBottom();
//            Log.d(TAG, "onDrawOver: position:"+i+" left:"+left
//                    +" top:"+top
//                    +" right:"+right
//                    +" bottom:"+bottom);

            if (leftDecorationWidth!=0){
                c.drawRect(left-leftDecorationWidth,top-topDecorationHeight,
                            left,bottom+bottomDecorationHeight,drawOverPaint);
            }
//            if (topDecorationHeight!=0){
//                c.drawRect(left,top,right,top+topDecorationHeight,drawOverPaint);
//            }
            if (rightDecorationWidth!=0){
                c.drawRect(right,top-topDecorationHeight,
                        right+rightDecorationWidth,bottom+bottomDecorationHeight,drawOverPaint);
            }
//            if (bottomDecorationHeight!=0){
//                c.drawRect(left,bottom-bottomDecorationHeight,right,bottom,drawOverPaint);
//            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        Log.d(TAG, "getItemOffsets: ");
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition ==0){
            outRect.set(0, 0, 2, 0);
        }else if (childAdapterPosition == state.getItemCount()-1){
            outRect.set(2, 0, 0, 0);
        }else {
            outRect.set(2, 0, 2, 0);
        }
    }
}
