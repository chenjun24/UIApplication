package com.cj.uiapplication.view.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cj.uiapplication.R;

public class BitmapView extends View {
    public BitmapView(Context context) {
        super(context);
        init(context);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    Paint paint;
    Bitmap bitmap;
    Bitmap bitmap1;
    Bitmap bitmap2;
    private void init(Context context) {
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.voice_focus);
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.biu);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.boy);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

       // paint.setColor(Color.GREEN);
       // paint.setStyle(Paint.Style.STROKE);//
        //paint.setStyle(Paint.Style.FILL);//默认是这个
        //paint.setStrokeWidth(20);//线条宽度
        //paint.setAntiAlias(true);//抗锯齿

        canvas.drawBitmap(bitmap,10,10,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(0,800,500,800,paint);
        canvas.drawLine(300,0,300,1000,paint);


        canvas.save();
        canvas.clipRect(0,0,bitmap1.getWidth(),bitmap1.getHeight());
        canvas.drawBitmap(bitmap1,0,0,paint);
        canvas.restore();

        //这个确定从顶点位置开始
        Shader shader = new BitmapShader(bitmap2, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        paint.setShader(shader);
        //以宽高中 小的中心点为中心
        canvas.drawCircle(bitmap2.getWidth()/2,bitmap2.getHeight()/2,bitmap2.getHeight()/2,paint);

       // paint.setColorFilter()
       // paint.setXfermode()
        //二位变换
//        canvas.translate();
//        canvas.rotate();
//        canvas.scale();
        //matrix
        //camera
    }
}
