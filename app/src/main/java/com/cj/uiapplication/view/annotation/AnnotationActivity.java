package com.cj.uiapplication.view.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.cj.ioc.ViewBinder;
import com.cj.ioc_annotation.BindView;
import com.cj.uiapplication.R;

public class AnnotationActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    public TextView tv;
    @BindView(R.id.click)
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        ViewBinder.bind(this);
        tv.setText("hello  bind view");
    }
}