package com.jackting.module_order.ui.main;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainCartPresenter extends BasePresenter<MainCartContract.Model, MainCartContract.View> {

    @Inject
    public MainCartPresenter(MainOrderModel model) {
        super(model);
    }

    @Override
    public void init() {
        getArticleList();
    }
    void getArticleList(){

    }
}
