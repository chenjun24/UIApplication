package com.cj.uiapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class MyApplication extends Application {
    private final static String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        String root = MMKV.initialize(this);
        Log.d(TAG, "onCreate: MMKV root dir--"+root);
        // MMKV.initialize("rootDir");目录自定义

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: ");
    }
}
