package com.cj.uiapplication.adapter.layoutmanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class MyItemCallback extends DiffUtil.ItemCallback<Person> {
    @Override
    public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
        return oldItem.name.equals(newItem.name) && oldItem.number.equals(newItem.number);
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull Person oldItem, @NonNull Person newItem) {
        Log.d("junchen", "getChangePayload: ");
        Bundle bundle = new Bundle();
        if (!TextUtils.equals(oldItem.name,newItem.name)){
            bundle.putString("name",newItem.name);
        }
        if (!TextUtils.equals(oldItem.number,newItem.number)){
            bundle.putString("number",newItem.number);
        }
        return bundle;
    }
}
