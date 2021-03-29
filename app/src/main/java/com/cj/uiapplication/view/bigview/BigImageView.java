package com.cj.uiapplication.view.bigview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * 加载大长图
 */
public class BigImageView extends View implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private final Rect mRect;
    private final BitmapFactory.Options mOptions;
    private final Scroller mScroller;
    private GestureDetector mGestureDetector;
    private int mViewWidth;
    private int mViewHeight;
    private float mScale;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Bitmap mBitmap;
    private BitmapRegionDecoder mBitmapRegionDecoder;
    private Matrix mMatrix;

    public BigImageView(Context context) {
        this(context,null);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRect = new Rect();
        mOptions = new BitmapFactory.Options();
        mGestureDetector = new GestureDetector(context,this);
        mScroller = new Scroller(context);
        mMatrix = new Matrix();
        setOnTouchListener(this);
    }

    //
    public void setImage(InputStream is){
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, mOptions);
        mBitmapWidth = mOptions.outWidth;
        mBitmapHeight = mOptions.outHeight;

        mOptions.inMutable = true;//内存复用
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        mOptions.inJustDecodeBounds = false;
        //区域解码器
        try {
            mBitmapRegionDecoder = BitmapRegionDecoder.newInstance(is, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestLayout();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mBitmapWidth;
        mScale = mViewWidth/(float)mBitmapWidth;
        mRect.bottom = (int)(mViewHeight/mScale);
        //这个rect 是图片的宽高 缩放前的宽高  缩放后刚好是view的宽高
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmapRegionDecoder==null){
            return;
        }
        mOptions.inBitmap = mBitmap;
        mBitmap = mBitmapRegionDecoder.decodeRegion(mRect,mOptions);
        mMatrix.reset();
        mMatrix.setScale(mScale,mScale);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //如果滑动还没停止  则强制停止
        if (!mScroller.isFinished()){
            mScroller.forceFinished(true);
        }
        return true;
    }

    /**
     *
     * @param e1 开始事件
     * @param e2 即时事件
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //直接改变rect
        mRect.offset(0, (int) distanceY);
        //判断到边界位置
        if (mRect.bottom>mBitmapHeight){
            mRect.bottom = mBitmapHeight;
            mRect.top = mBitmapHeight-(int)(mViewHeight/mScale);
        }
        if (mRect.top<0){
            mRect.top = 0;
            mRect.bottom = (int) (mViewHeight/mScale);
        }
        invalidate();
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(0,mRect.top,(int)velocityX,(int)-velocityY,0,0,0, mBitmapHeight-(int)(mViewHeight/mScale));
        return false;
    }

    @Override
    public void computeScroll() {
       if (mScroller.isFinished()){
           return;
       }
       if (mScroller.computeScrollOffset()){
           mRect.top = mScroller.getCurrY();
           mRect.bottom = mRect.top+(int)(mViewHeight/mScale);
           invalidate();
       }

    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    @Override
    public void onLongPress(MotionEvent e) {

    }




}
