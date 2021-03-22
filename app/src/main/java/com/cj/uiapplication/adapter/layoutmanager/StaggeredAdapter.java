package com.cj.uiapplication.adapter.layoutmanager;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.uiapplication.R;

import java.util.List;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    private List<String> strings;
    public StaggeredAdapter(List<String> strings){
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.layout_manager_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position%5 ==0){
            holder.itemView.setBackgroundColor(Color.RED);
        }else if (position%5 ==1){
            holder.itemView.setBackgroundColor(Color.GREEN);
        }else if (position%5 ==2){
            holder.itemView.setBackgroundColor(Color.BLUE);
        }else if (position%5 ==3){
            holder.itemView.setBackgroundColor(Color.parseColor("#112288"));
        }else {
            holder.itemView.setBackgroundColor(Color.parseColor("#667788"));
        }
        if (position==4){
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height=500;
        }
         holder.tv.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings==null ? 0:strings.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
