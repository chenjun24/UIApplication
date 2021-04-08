package com.cj.uiapplication.view.vector;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.cj.uiapplication.R;

public class VectorDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) ((ImageView) findViewById(R.id.round)).getDrawable();
        if(drawable.isRunning()){
            drawable.stop();
        }else {
            drawable.start();
        }
    }
}