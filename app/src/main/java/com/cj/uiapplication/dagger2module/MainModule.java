package com.cj.uiapplication.dagger2module;

import com.cj.uiapplication.contract.Dagger2ActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private  Dagger2ActivityContract.View mView;
     public MainModule(Dagger2ActivityContract.View view){
         mView = view;
     }

     @Provides
     public Dagger2ActivityContract.View provideMainView(){
         return mView;
     }
}
