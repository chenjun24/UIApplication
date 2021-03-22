package com.cj.uiapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {
    private final static String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: ");
    }
}
