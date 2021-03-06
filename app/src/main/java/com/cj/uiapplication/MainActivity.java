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
import com.cj.uiapplication.view.bigview.BigViewActivity;
import com.cj.uiapplication.view.constrait.ConstraintActivity;
import com.cj.uiapplication.view.dagger2.Dagger2Activity;
import com.cj.uiapplication.view.draw.DrawActivity;
import com.cj.uiapplication.view.handler.HandlerActivity;
import com.cj.uiapplication.view.layout.LayoutActivity;
import com.cj.uiapplication.view.layoutmanager.LayoutManagerActivity;
import com.cj.uiapplication.view.measure.MeasureActivity;
import com.cj.uiapplication.view.mmkv.MMKVActivity;
import com.cj.uiapplication.view.testfragment.TestFragmentActivity;
import com.cj.uiapplication.view.vector.VectorDrawableActivity;

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

        MainItemInfo dagger2 = new MainItemInfo();
        dagger2.text = "dagger2";
        dagger2.mClass = Dagger2Activity.class;

        MainItemInfo bigView = new MainItemInfo();
        bigView.text = "bigView";
        bigView.mClass = BigViewActivity.class;

        MainItemInfo vectorDrawable = new MainItemInfo();
        vectorDrawable.text = "vectorDrawable";
        vectorDrawable.mClass = VectorDrawableActivity.class;

        MainItemInfo constraint = new MainItemInfo();
        constraint.text = "constraint";
        constraint.mClass = ConstraintActivity.class;

        arrayList.add(measure);
        arrayList.add(layout);
        arrayList.add(draw);
        arrayList.add(handler);
        arrayList.add(testFragment);
        arrayList.add(annotation);
        arrayList.add(layoutManager);
        arrayList.add(mmkv);
        arrayList.add(dagger2);
        arrayList.add(bigView);
        arrayList.add(vectorDrawable);
        arrayList.add(constraint);
        return arrayList;
    }
}