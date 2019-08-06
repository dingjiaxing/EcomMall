package com.jackting.module_main.ui.main.home;

import android.util.Log;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainHomePresenter extends BasePresenter<MainHomeContract.Model,MainHomeContract.View> {

    @Inject
    public MainHomePresenter(MainHomeModel model) {
        super(model);
    }

    @Override
    public void init() {
        getArticleList();
        Log.i(TAG,"MainHomePresenter init");
        view.showLoading();
    }
    void getArticleList(){

    }
}
