package com.cj.uiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.cj.uiapplication.adapter.MainAdapter;
import com.cj.uiapplication.decoration.MainItemDecoration;
import com.cj.uiapplication.model.bean.MainItemInfo;

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

        arrayList.add(measure);
        arrayList.add(layout);
        arrayList.add(draw);
        return arrayList;
    }
}