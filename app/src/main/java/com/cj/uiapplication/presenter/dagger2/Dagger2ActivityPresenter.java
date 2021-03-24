package com.cj.uiapplication.presenter.dagger2;

import com.cj.uiapplication.contract.Dagger2ActivityContract;

import javax.inject.Inject;

public class Dagger2ActivityPresenter {
    private Dagger2ActivityContract.View mView;
    @Inject
    public Dagger2ActivityPresenter(Dagger2ActivityContract.View view){
         mView = view;
    }

    public void loadData(){

        mView.updateUI();
    }

}
