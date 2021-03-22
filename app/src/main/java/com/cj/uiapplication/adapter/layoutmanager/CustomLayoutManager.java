package com.cj.uiapplication.adapter.layoutmanager;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

public class CustomLayoutManager extends RecyclerView.LayoutManager {
    private final static String TAG = "CustomLayoutManager";
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 默认实现
     * @param recycler
     * @param state
     * @param widthSpec
     * @param heightSpec
     */
    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }

    int mPendingPosition = RecyclerView.NO_POSITION;
    /**
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        Log.d(TAG, "onLayoutChildren: start");
        // layout algorithm:
        // 1) by checking children and other variables, find an anchor coordinate and an anchor
        //  item position.
        // 2) fill towards start, stacking from bottom
        // 3) fill towards end, stacking from top
        // 4) scroll to fulfill requirements like stack from bottom.
        // create layout state

        detachAndScrapAttachedViews(recycler);
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getPaddingRight();
        int bottom = getPaddingBottom();
        int totalSpace = getHeight()-top-bottom;
        int currentPosition = 0;
        if (mPendingPosition!=RecyclerView.NO_POSITION){
            currentPosition = mPendingPosition;
        }
        while (totalSpace>0 && currentPosition<state.getItemCount()){
           // Log.d(TAG, "onLayoutChildren: totalSpace:"+totalSpace);
            View viewChild = recycler.getViewForPosition(currentPosition);
            addView(viewChild);
            measureChildWithMargins(viewChild,left,right);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewChild);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewChild);
           // Log.d(TAG, "onLayoutChildren: decoratedMeasuredHeight--"+decoratedMeasuredHeight);
           // Log.d(TAG, "onLayoutChildren: decoratedMeasuredWidth--"+decoratedMeasuredWidth);
            layoutDecorated(viewChild,left,top,
                    left+decoratedMeasuredWidth,top+decoratedMeasuredHeight);
            currentPosition++;
            top+=decoratedMeasuredHeight;
            totalSpace-=decoratedMeasuredHeight;
        }
       // Log.d(TAG, "onLayoutChildren end  scrap list size:"+recycler.getScrapList().size()+"  child count:"+getChildCount());

    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
       Log.d(TAG, "scrollVerticallyBy: start");
        //填充
        int finalDy = fill(dy,recycler);
        Log.d(TAG, "scrollVerticallyBy: dy--"+dy+"  finalDy-"+finalDy);
        //移动
        offsetChildrenVertical(-finalDy);
        //回收
        recycle(finalDy,recycler);
        Log.d(TAG, "scrollVerticallyBy scrap list size:"+recycler.getScrapList().size()+"  child count:"+getChildCount());
        Log.d(TAG, "scrollVerticallyBy: end");
        return finalDy;
    }

    private int fill(int dy, RecyclerView.Recycler recycler) {
        Log.d(TAG, "fill: start");
        int fillPosition = RecyclerView.NO_POSITION;
        int availableSpace  = Math.abs(dy);
        int absDelta = Math.abs(dy);

        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        if (dy>0){//要在底部填充
            View childAtLast = getChildAt(getChildCount() - 1);//找到目前最底部的view
            int currentLastPosition = getPosition(childAtLast);//在适配器中的位置
            int decoratedBottom = getDecoratedBottom(childAtLast);//
            Log.d(TAG, "fill: dy>0  currentLastPosition--"+currentLastPosition);
            top = decoratedBottom;
            fillPosition = currentLastPosition+1;

            //已滑动到底部了，如果此时手指滑动的距离大于最后一个item 底部 减去recycler view的底部了
            // 那就是实际滑动 最后一个item 底部 减去recycler view的底部就可以了
            if (fillPosition >= getItemCount() && decoratedBottom-absDelta<getHeight()){
                return decoratedBottom-getHeight();
            }
            //如果尾部位置减去recycler view的底部 还大于recycler view的底部   不用填充
            if (decoratedBottom-absDelta>getHeight()){
                return dy;
            }

        }

        if(dy<0){
            View childAtFirst = getChildAt(0);//找到目前最顶部的view
            int currentFirstPosition = getPosition(childAtFirst);//在适配器中的位置
            int decoratedTop = getDecoratedTop(childAtFirst);
            Log.d(TAG, "fill: dy<0  currentFirstPosition--"+currentFirstPosition);
            bottom = decoratedTop;
            fillPosition = currentFirstPosition-1;

            //如果已经滑到顶部了 如果此时手指滑动的距离 将顶部的item向下滑动 要大于recycler vie 顶部了  那实际滑动的距离就是 顶部item超出的部分
            if (fillPosition<0 && decoratedTop + absDelta > 0){
                return decoratedTop;
            }
            //无需填充
            if (decoratedTop+absDelta < 0){
                return dy;
            }

        }

        while (availableSpace >0 && fillPosition>=0 && fillPosition<getItemCount()){
            View viewForPosition = recycler.getViewForPosition(fillPosition);
            if (dy>0){
                addView(viewForPosition);
            }else {
                addView(viewForPosition,0);
            }

            measureChild(viewForPosition,0,0);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);//填充item的高度
            if(dy>0){
                bottom = top+decoratedMeasuredHeight;
            }else {
                top = bottom-decoratedMeasuredHeight;
            }
            right=left+getDecoratedMeasuredWidth(viewForPosition);
            layoutDecorated(viewForPosition,left,top,right,bottom);

            if (dy>0){
                top+=decoratedMeasuredHeight;
                fillPosition++;
            }else {
                bottom-= decoratedMeasuredHeight;
                fillPosition--;
            }
            if (fillPosition>=0 && fillPosition<getItemCount()){
                availableSpace -= decoratedMeasuredHeight;
            }
        }
        Log.d(TAG, "fill: end");
        return dy;
    }
    private HashSet<View> set = new HashSet<>();
    private void recycle(int dy, RecyclerView.Recycler recycler) {
        set.clear();
        if (dy>0){//从下往上滑
            for (int i=0;i<getChildCount();i++){
                View child = getChildAt(i);
                int decoratedBottom = getDecoratedBottom(child);
                if (decoratedBottom<0){//如果item的底部已经超过上方显示空间了了
                    set.add(child);
                    break;
                }

            }
        }

        if (dy<0){//从上往下滑
            for (int i=getChildCount()-1;i>=0;i--){
                View child = getChildAt(i);
                int decoratedTop = getDecoratedTop(child);
                if (decoratedTop>getHeight()){//如果item的顶部已经在显示空间下方了
                    set.add(child);
                    break;
                }
            }
        }
        for (View view:set){
            removeAndRecycleView(view,recycler);
        }
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
        if (position<0 || position>= getItemCount()){
            return;
        }
        mPendingPosition = position;
        requestLayout();
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
//        LinearSmoothScroller linearSmoothScroller =
//                new LinearSmoothScroller(recyclerView.getContext());
//        linearSmoothScroller.setTargetPosition(position);
//        startSmoothScroll(linearSmoothScroller);
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        //mPendingPosition = RecyclerView.NO_POSITION;
    }

}
