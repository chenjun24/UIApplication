package com.cj.uiapplication.adapter.layoutmanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class MyDiffCallback extends DiffUtil.Callback {
    List<Person> oldList;
    List<Person> newList;

    public MyDiffCallback(List<Person> oldList, List<Person> newList) {
        this.oldList = oldList;
        this.newList = newList;
        Log.d("junchen", "MyDiffCallback: "+oldList.size()+"  new size:"+newList.size());
    }

    @Override
    public int getOldListSize() {
        return oldList==null?0:oldList.size();
    }

    @Override
    public int getNewListSize() {
        return  newList==null?0:newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Person oldPerson = oldList.get(oldItemPosition);
        Person newPerson = newList.get(newItemPosition);
        Log.d("junchen", "areItemsTheSame: oldItemPosition-"+oldItemPosition+" newItemPosition-"+newItemPosition);
        return oldPerson.id == newPerson.id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Person oldPerson = oldList.get(oldItemPosition);
        Person newPerson = newList.get(newItemPosition);
        Log.d("junchen", "areContentsTheSame: oldItemPosition-"+oldItemPosition+" newItemPosition-"+newItemPosition);
        return oldPerson.name.equals(newPerson.name) && oldPerson.number.equals(newPerson.number) ;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Person oldPerson = oldList.get(oldItemPosition);
        Person newPerson = newList.get(newItemPosition);
        Log.d("junchen", "getChangePayload: oldItemPosition-"+oldItemPosition+" newItemPosition-"+newItemPosition);
        Bundle bundle = new Bundle();
        if (!TextUtils.equals(oldPerson.name,newPerson.name)){
            bundle.putString("name",newPerson.name);
        }
        if (!TextUtils.equals(oldPerson.number,newPerson.number)){
            bundle.putString("number",newPerson.number);
        }
        return bundle;
    }
}
