package com.cj.uiapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cj.uiapplication.R;
import com.cj.uiapplication.model.bean.MainItemInfo;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private final static String TAG = "MainAdapter";
    private ArrayList<MainItemInfo> strings;
    Context context;
    public MainAdapter(Context context, ArrayList<MainItemInfo> strings){
        this.strings = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.main_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainItemInfo mainItemInfo = strings.get(position);
        holder.tv.setText(mainItemInfo.text);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!= null){
                    //Log.d(TAG, "onClick: height:"+holder.itemView.getHeight()+" width:"+holder.itemView.getWidth());
                    //Log.d(TAG, "onClick: left:"+holder.itemView.getLeft()+" top:"+holder.itemView.getTop());
                    onItemClickListener.onClick(mainItemInfo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings!=null?strings.size():0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MainAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onClick(MainItemInfo mainItemInfo);
    }
}
