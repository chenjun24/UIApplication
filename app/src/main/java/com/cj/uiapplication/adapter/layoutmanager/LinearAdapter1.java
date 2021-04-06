package com.cj.uiapplication.adapter.layoutmanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.uiapplication.R;

import java.util.ArrayList;
import java.util.List;

public class LinearAdapter1 extends RecyclerView.Adapter<LinearAdapter1.MyViewHolder> {
    private AsyncListDiffer<Person> differ;
    public LinearAdapter1(){
      //  this.personList = personList;
        differ = new AsyncListDiffer<Person>(this,new MyItemCallback());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.layout_manager_item_layout1,parent,false));
    }

    public void setPersonList(List<Person> personList) {
        differ.submitList(personList);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        if (position%3 ==0){
//            holder.itemView.setBackgroundColor(Color.RED);
//        }else if (position%3 ==1){
//            holder.itemView.setBackgroundColor(Color.GREEN);
//        }else {
//            holder.itemView.setBackgroundColor(Color.BLUE);
//        }
//        if (position==5){
//            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//            layoutParams.height=200;
//        }
        Person person = differ.getCurrentList().get(position);
        holder.name.setText(person.name);
        holder.number.setText(person.number);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads!=null&& payloads.size()>0){
            Log.d("junchen", "onBindViewHolder: ");
            try{
                Bundle o = (Bundle) payloads.get(0);
                String name = o.getString("name");
                String number = o.getString("number");
                Log.d("junchen", "onBindViewHolder: name--"+name+" number-- "+number);
                if (!TextUtils.isEmpty(name)){
                    holder.name.setText(name);
                }
                if (!TextUtils.isEmpty(number)){
                    holder.number.setText(number);
                }

            }catch (Exception e){
                Log.d("junchen", "onBindViewHolder: e:"+e.getMessage());
            }
        }else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }
}
