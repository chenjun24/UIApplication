package com.cj.uiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.cj.uiapplication.adapter.DrawItemAdapter;
import com.cj.uiapplication.decoration.DrawItemDecoration;
import com.cj.uiapplication.model.bean.DrawItemInfo;
import java.util.ArrayList;

public class DrawActivity extends AppCompatActivity {
    private RecyclerView menuRv;
    private FrameLayout fragmentContent;
    private DrawItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        initRecyclerView();
        fragmentContent = findViewById(R.id.fragment_content);
    }

    private void initRecyclerView(){
        menuRv = findViewById(R.id.menu_rv);
        adapter = new DrawItemAdapter(this,getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        menuRv.addItemDecoration(new DrawItemDecoration());
        menuRv.setLayoutManager(layoutManager);
        menuRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new DrawItemAdapter.onItemClickListener() {
            @Override
            public void onClick(DrawItemInfo drawItemInfo) {
                switch (drawItemInfo.drawType){
                    case COLOR:
                        break;
                    case BITMAP:
                        break;
                    case CIRCLE:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public ArrayList<DrawItemInfo> getData(){
        ArrayList<DrawItemInfo> arrayList = new ArrayList<>();
        DrawItemInfo color = new DrawItemInfo();
        color.text = "color";
        color.drawType = DrawItemInfo.DrawType.COLOR;

        DrawItemInfo circle = new DrawItemInfo();
        circle.text = "circle";
        circle.drawType = DrawItemInfo.DrawType.CIRCLE;

        DrawItemInfo bitmap = new DrawItemInfo();
        bitmap.text = "bitmap";
        bitmap.drawType = DrawItemInfo.DrawType.BITMAP;

        arrayList.add(color);
        arrayList.add(circle);
        arrayList.add(bitmap);
        return arrayList;
    }
}