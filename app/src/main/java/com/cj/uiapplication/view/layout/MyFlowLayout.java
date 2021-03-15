package com.cj.uiapplication.view.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.cj.uiapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyFlowLayout extends ViewGroup {
    private final static String TAG = "MyFlowLayout";
    int hSpace = 16;//水平间距
    int vSpace = 16;//竖直间距
    public MyFlowLayout(Context context) {
        this(context,null);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyFlowLayout);
        if (typedArray!= null){
            hSpace = (int) typedArray.getDimension(R.styleable.MyFlowLayout_horizontalGap,hSpace);
            vSpace = (int) typedArray.getDimension(R.styleable.MyFlowLayout_verticalGap,vSpace);
           // Log.d(TAG, "MyFlowLayout: hSpace--"+hSpace+"  vSpace--"+vSpace);
        }
    }

    List<List<View>> allView;//每一行的view
    List<Integer> linesHeight;//每一行的高度

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      //  super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (allView != null){
            allView.clear();
        }
        allView = new ArrayList<>();
        linesHeight = new ArrayList<>();


        //父view传给自己的测标准 可以获取参考值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        List<View> lineViews = new ArrayList<>();
        int lineWithUsed = paddingLeft+paddingRight;//一行已经使用多少了
        int lineHeight = 0;//每一行的高度 取行中最高的那一个view的高度
        int childCount = getChildCount();

        int measuredWidthTemp = 0;//
        int measuredHeightTemp = 0;//根据子view计算得到
       // Log.d(TAG, "onMeasure: childCount-"+childCount);
        for (int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            LayoutParams layoutParams = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,paddingLeft+paddingRight,layoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,paddingTop+paddingBottom,layoutParams.height);
             //子view  测量标准  由自己在布局文件中定义的layout_width layout_height值(即LayoutParams) 加和 父view MeasureSpec 以及父view已经占用的部分(padding部分)决定
             //子view在布局文件的宽度可以设置三种值 确定值 ，MATCH_PARENT -1 WRAP_CONTENT -2
             //MeasureSpec 是由mode(高两位)加大小构成(低32位) mode 值有EXACTLY   AT_MOST  UNSPECIFIED
            //关键怎么获取child的  measureSpe
            childView.measure(childWidthMeasureSpec,childHeightMeasureSpec);
            int measuredHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            //Log.d(TAG, "onMeasure: i--"+i+" measuredWidth-"+measuredWidth+"  widthSize--"+widthSize);
            //Log.d(TAG, "onMeasure: lineWithUsed--"+lineWithUsed);
            if (measuredWidth+lineWithUsed+hSpace>widthSize){//大于参考的宽度 需要换行
              //  Log.d(TAG, "onMeasure: 11");
                allView.add(lineViews);
                linesHeight.add(lineHeight);

                measuredWidthTemp = Math.max(measuredWidthTemp,lineWithUsed+hSpace);//每换行 就找出当前所有行中 最大的那一个赋值
                measuredHeightTemp = measuredHeightTemp+lineHeight+vSpace;
                lineWithUsed=0;
                lineViews = new ArrayList<>();
                lineHeight = 0;
            }
           // Log.d(TAG, "onMeasure: 2222");
            lineViews.add(childView);
            lineWithUsed = lineWithUsed+measuredWidth+hSpace;
            lineHeight = Math.max(lineHeight,measuredHeight);//取每一行的最大高度
        }
       if (lineViews.size()>0){
           allView.add(lineViews);//添加最后一行
           linesHeight.add(lineHeight);
           measuredHeightTemp = measuredHeightTemp+lineHeight+vSpace;
       }

        //怎么确定自己为多大 那要看我们这个流式布局的目的  目的是让child view 一行行排满 一行宽度不够 自动换行
        //所以这个布局的大小 宽度是由 宽度最宽的那一行作为宽度  高度则所有行高之和
        //当然还要看 父view传递过来的widthMeasureSpec heightMeasureSpec 的mode是EXACTLY? 指定确定则用确定值

        int realMeasuredWidth = widthMode==MeasureSpec.EXACTLY?widthSize:measuredWidthTemp;
        int realMeasuredHeight = heightMode==MeasureSpec.EXACTLY?heightSize:measuredHeightTemp;
       // Log.d(TAG, "onMeasure: realMeasuredWidth--"+realMeasuredWidth+" realMeasuredHeight--"+realMeasuredHeight);
        setMeasuredDimension(realMeasuredWidth,realMeasuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curLeft = getPaddingLeft();
        int curTop = getPaddingTop();
        int line = allView.size();
        //Log.d(TAG, "onLayout: line--"+line);
        for (int i=0;i<line;i++){//遍历所有行
            List<View> views = allView.get(i);
            int lineHeight = linesHeight.get(i);
            for (int j=0;j<views.size();j++){//每一行View的布局
                View view = views.get(j);
                int left = curLeft;
                int top = curTop;
                int right = view.getMeasuredWidth()+left;
                int bottom = view.getMeasuredHeight()+top;
                view.layout(left,top,right,bottom);
                curLeft = right+hSpace;
            }
            curLeft = getPaddingLeft();
            curTop += lineHeight+vSpace;
        }
    }
}
