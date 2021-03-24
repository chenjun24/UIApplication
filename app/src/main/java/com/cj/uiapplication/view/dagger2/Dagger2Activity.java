package com.cj.uiapplication.view.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cj.uiapplication.R;
import com.cj.uiapplication.contract.Dagger2ActivityContract;
import com.cj.uiapplication.dagger2Component.DaggerMainComponent;
import com.cj.uiapplication.dagger2module.MainModule;
import com.cj.uiapplication.presenter.dagger2.Dagger2ActivityPresenter;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity implements Dagger2ActivityContract.View {
    @Inject
    public Dagger2ActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        presenter.loadData();
        // 多个Component 不能 注入一个对象

    }

    @Override
    public void updateUI() {

    }
}