package com.cj.uiapplication.view.layoutmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.cj.uiapplication.R;
import com.cj.uiapplication.adapter.layoutmanager.LayoutManagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LayoutManagerActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager_main);
        fragments.clear();
        strings.clear();
        fragments.add(LinearLayoutFragment.newInstance("linear","linear"));
        strings.add("linear");
        fragments.add(GridLayoutFragment.newInstance("grid","grid"));
        strings.add("grid");
        fragments.add(StaggeredGridLayoutFragment.newInstance("staggered","staggered"));
        strings.add("staggered");
        fragments.add(CustomLayoutFragment.newInstance("custom","custom"));
        strings.add("custom");
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);
        LayoutManagerAdapter adapter = new LayoutManagerAdapter(getSupportFragmentManager(),fragments,strings);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}