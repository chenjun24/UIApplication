package com.cj.uiapplication.view.bigview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cj.uiapplication.R;

import java.io.IOException;
import java.io.InputStream;

public class BigViewActivity extends AppCompatActivity {
   private BigImageView bigView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_view);
        bigView = findViewById(R.id.bigView);
        try {
            InputStream inputStream = getAssets().open("xx.png");
            bigView.setImage(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}