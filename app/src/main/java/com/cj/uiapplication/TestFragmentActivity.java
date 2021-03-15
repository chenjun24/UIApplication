package com.cj.uiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.cj.uiapplication.view.testfragment.BlankFragment1;

public class TestFragmentActivity extends AppCompatActivity {
    private final static String TAG = "TestFragmentActivity";
    private FrameLayout fragmentContent;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_test_fragment);
        fragmentContent = findViewById(R.id.fg_content);
        button = findViewById(R.id.btn1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    FragmentManager supportFragmentManager;
    public void click(View view) {
        Log.d(TAG, "click: ");
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fg_content, BlankFragment1.newInstance("param1","param2"),"fragment1");
        fragmentTransaction.commit();
        //fragmentTransaction.commitAllowingStateLoss();

    }
}