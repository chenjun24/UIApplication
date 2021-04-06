package com.cj.uiapplication.view.measure;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


class MyView extends FrameLayout {
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private TextView textView;
    private void init(Context context) {
        textView = new TextView(context);
        textView.setText("99+");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();

        canvas.restore();
        super.dispatchDraw(canvas);
    }
}
