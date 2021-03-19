package com.cj.uiapplication.view.draw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.cj.uiapplication.R;
import com.cj.uiapplication.adapter.DrawItemAdapter;
import com.cj.uiapplication.adapter.draw.DrawFragmentPageAdapter;
import com.cj.uiapplication.decoration.DrawItemDecoration;
import com.cj.uiapplication.model.bean.DrawItemInfo;
import com.cj.uiapplication.view.draw.DrawBitmapFragment;
import com.cj.uiapplication.view.draw.DrawPathFragment;
import com.cj.uiapplication.view.draw.DrawCircleFragment;
import com.cj.uiapplication.view.draw.DrawColorFragment;
import com.cj.uiapplication.view.draw.DrawTextFragment;

import java.util.ArrayList;

public class DrawActivity extends AppCompatActivity {
    private final static String TAG = "DrawActivity";
    private RecyclerView menuRv;
    private ViewPager viewPage;
    private DrawItemAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<DrawItemInfo> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        initData();
        initRecyclerView();
        initViewPage();
    }

    private void initRecyclerView(){
        menuRv = findViewById(R.id.menu_rv);
        adapter = new DrawItemAdapter(this,arrayList);
        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        menuRv.addItemDecoration(new DrawItemDecoration());
        menuRv.setLayoutManager(layoutManager);
        menuRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new DrawItemAdapter.onItemClickListener() {
            @Override
            public void onClick(DrawItemInfo drawItemInfo) {
                setViewPagerPrimaryItem(drawItemInfo.position);
                switch (drawItemInfo.drawType){
                    case COLOR:
                        break;
                    case BITMAP:
                        break;
                    case CIRCLE:
                        break;
                    case TEXT:
                        break;
                    case PATH:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setViewPagerPrimaryItem(int position){
        if (viewPage!=null){
            viewPage.setCurrentItem(position,true);
        }
    }

    private void initViewPage(){
        viewPage = findViewById(R.id.viewPage);
        DrawFragmentPageAdapter adapter = new DrawFragmentPageAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPage.setAdapter(adapter);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setRecyclerViewPrimaryItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setRecyclerViewPrimaryItem(int position){
        if (menuRv!=null){
            menuRv.smoothScrollToPosition(position);
        }
    }

    public void initData(){

        DrawItemInfo color = new DrawItemInfo();
        color.position=0;
        color.text = "color";
        color.drawType = DrawItemInfo.DrawType.COLOR;
        DrawColorFragment drawColorFragment = DrawColorFragment.newInstance("color", "color");

        DrawItemInfo circle = new DrawItemInfo();
        circle.position=1;
        circle.text = "circle";
        circle.drawType = DrawItemInfo.DrawType.CIRCLE;
        DrawCircleFragment drawCircleFragment = DrawCircleFragment.newInstance("circle", "circle");

        DrawItemInfo text = new DrawItemInfo();
        text.position=2;
        text.text = "text";
        text.drawType = DrawItemInfo.DrawType.TEXT;
        DrawTextFragment drawTextFragment = DrawTextFragment.newInstance("text", "text");

        DrawItemInfo path = new DrawItemInfo();
        path.position=3;
        path.text = "path";
        path.drawType = DrawItemInfo.DrawType.PATH;
        DrawPathFragment drawPathFragment = DrawPathFragment.newInstance("path", "path");

        DrawItemInfo bitmap = new DrawItemInfo();
        bitmap.position=4;
        bitmap.text = "bitmap";
        bitmap.drawType = DrawItemInfo.DrawType.BITMAP;
        DrawBitmapFragment drawBitmapFragment = DrawBitmapFragment.newInstance("bitmap", "bitmap");


        arrayList.clear();
        arrayList.add(color);
        arrayList.add(circle);
        arrayList.add(text);
        arrayList.add(path);
        arrayList.add(bitmap);

        fragments.clear();
        fragments.add(drawColorFragment);
        fragments.add(drawCircleFragment);
        fragments.add(drawTextFragment);
        fragments.add(drawPathFragment);
        fragments.add(drawBitmapFragment);
    }
}