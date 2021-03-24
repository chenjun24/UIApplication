package com.cj.uiapplication.dagger2Component;

import com.cj.uiapplication.dagger2module.MainModule;
import com.cj.uiapplication.view.dagger2.Dagger2Activity;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    //不能用多态
    void inject(Dagger2Activity activity);
}
