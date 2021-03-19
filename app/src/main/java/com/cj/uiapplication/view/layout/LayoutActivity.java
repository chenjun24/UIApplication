package com.cj.uiapplication.view.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.cj.uiapplication.R;
import com.cj.uiapplication.adapter.layout.LayoutFragmentPageAdapter;
import com.cj.uiapplication.view.layout.LayoutFragment1;
import com.cj.uiapplication.view.layout.LayoutFragment2;
import com.cj.uiapplication.view.layout.LayoutFragment3;

import java.util.ArrayList;

public class LayoutActivity extends AppCompatActivity {
    private final static String TAG = "LayoutActivity";
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_layout);
        viewPager = findViewById(R.id.vp);
        fragments.clear();
        fragments.add(LayoutFragment1.newInstance("aa","aa"));
        fragments.add(LayoutFragment2.newInstance("bb","bb"));
        fragments.add(LayoutFragment3.newInstance("cc","cc"));
        LayoutFragmentPageAdapter adapter = new LayoutFragmentPageAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
    }
}