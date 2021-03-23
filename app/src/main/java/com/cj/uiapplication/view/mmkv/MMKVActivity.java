package com.cj.uiapplication.view.mmkv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cj.uiapplication.R;
import com.tencent.mmkv.MMKV;

public class MMKVActivity extends AppCompatActivity {
    private final static String TAG = "MMKVActivity";
    private TextView spT;
    private TextView mmkvT;
    private SharedPreferences sharedPreferences;
    private MMKV kv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_m_k_v);
        spT = findViewById(R.id.tv1);
        mmkvT = findViewById(R.id.tv2);
        sharedPreferences = getSharedPreferences("testsp",MODE_PRIVATE);
        kv = MMKV.defaultMMKV();
        String kvValue = kv.decodeString("kv");
        Log.d(TAG, "onCreate: kv--"+kv);
        mmkvT.setText(kvValue);
        String string = sharedPreferences.getString("sp1", "default");
        Log.d(TAG, "onCreate: string--"+string);
        spT.setText(string);
    }

    public void setValue(View view) {
        switch (view.getId()){
            case R.id.btn1:
                Log.d(TAG, "setValue: sp");
                //sp
                SharedPreferences.Editor editor = sharedPreferences.edit().putString("sp1", "设置sp");
               //editor.remove()
                editor.commit();
                //editor.apply();
                break;
            case R.id.btn2:
                //mmkv
                kv.encode("kv","设置mmvk值");
               // kv.removeValueForKey();
                break;
            default:
                break;
        }
    }
}