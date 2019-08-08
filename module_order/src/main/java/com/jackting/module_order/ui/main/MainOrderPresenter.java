package com.jackting.module_order.ui.main;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainOrderPresenter extends BasePresenter<MainOrderContract.Model, MainOrderContract.View> {

    @Inject
    public MainOrderPresenter(MainOrderModel model) {
        super(model);
    }

    @Override
    public void init() {
        getArticleList();
    }
    void getArticleList(){

    }
}
