package com.cj.uiapplication.view.vector;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cj.uiapplication.R;

public class VectorDrawableActivity extends AppCompatActivity {
   private TextView tv;
   private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        tv = findViewById(R.id.tv);
        int width = tv.getWidth();
        int height = tv.getHeight();
        handler = new Handler();
        Log.d("junchen", "onCreate: width--"+width+"  height--"+height);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int width = tv.getWidth();
                int height = tv.getHeight();
                Log.d("junchen", "onCreate22222222: width--"+width+"  height--"+height);
            }
        });
        //attach info 之后才会发送这个runnable
        tv.post(new Runnable() {
            @Override
            public void run() {
                int width = tv.getWidth();
                int height = tv.getHeight();
                Log.d("junchen", "onCreate11111: width--"+width+"  height--"+height);
            }
        });

        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ((ImageView) findViewById(R.id.round)).getDrawable();
        if(drawable.isRunning()){
            drawable.stop();
        }else {
            drawable.start();
        }
    }
}