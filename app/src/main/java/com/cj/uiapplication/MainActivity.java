package com.cj.uiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.cj.uiapplication.adapter.MainAdapter;
import com.cj.uiapplication.decoration.MainItemDecoration;
import com.cj.uiapplication.model.bean.MainItemInfo;
import com.cj.uiapplication.view.annotation.AnnotationActivity;
import com.cj.uiapplication.view.draw.DrawActivity;
import com.cj.uiapplication.view.handler.HandlerActivity;
import com.cj.uiapplication.view.layout.LayoutActivity;
import com.cj.uiapplication.view.layoutmanager.LayoutManagerActivity;
import com.cj.uiapplication.view.measure.MeasureActivity;
import com.cj.uiapplication.view.mmkv.MMKVActivity;
import com.cj.uiapplication.view.testfragment.TestFragmentActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView(){
        rv = findViewById(R.id.rv);
        adapter = new MainAdapter(this,getData());
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        //使用GridLayoutManager布局  item layout的根布局layout_width值要设置成match_parent
        //不然item的宽度 不会填充分配的宽度 还是从左边开始布局
        rv.addItemDecoration(new MainItemDecoration());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new MainAdapter.onItemClickListener() {
            @Override
            public void onClick(MainItemInfo mainItemInfo) {
                startActivity(new Intent(MainActivity.this, mainItemInfo.mClass));
            }
        });
    }

    public ArrayList<MainItemInfo> getData(){
        ArrayList<MainItemInfo> arrayList = new ArrayList<>();
        MainItemInfo measure = new MainItemInfo();
        measure.text = "measure";
        measure.mClass = MeasureActivity.class;

        MainItemInfo layout = new MainItemInfo();
        layout.text = "layout";
        layout.mClass = LayoutActivity.class;

        MainItemInfo draw = new MainItemInfo();
        draw.text = "draw";
        draw.mClass = DrawActivity.class;

        MainItemInfo handler = new MainItemInfo();
        handler.text = "handler";
        handler.mClass = HandlerActivity.class;

        MainItemInfo testFragment = new MainItemInfo();
        testFragment.text = "testFragment";
        testFragment.mClass = TestFragmentActivity.class;

        MainItemInfo annotation = new MainItemInfo();
        annotation.text = "annotation";
        annotation.mClass = AnnotationActivity.class;

        MainItemInfo layoutManager = new MainItemInfo();
        layoutManager.text = "layoutManager";
        layoutManager.mClass = LayoutManagerActivity.class;

        MainItemInfo mmkv = new MainItemInfo();
        mmkv.text = "mmkv";
        mmkv.mClass = MMKVActivity.class;

        arrayList.add(measure);
        arrayList.add(layout);
        arrayList.add(draw);
        arrayList.add(handler);
        arrayList.add(testFragment);
        arrayList.add(annotation);
        arrayList.add(layoutManager);
        arrayList.add(mmkv);
        return arrayList;
    }
}