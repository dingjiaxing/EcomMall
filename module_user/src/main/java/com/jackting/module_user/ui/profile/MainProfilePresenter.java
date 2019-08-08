package com.jackting.module_user.ui.profile;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainProfilePresenter extends BasePresenter<MainProfileContract.Model, MainProfileContract.View> {

    @Inject
    public MainProfilePresenter(MainProfileModel model) {
        super(model);
    }

    @Override
    public void init() {
        getArticleList();
    }
    void getArticleList(){

    }
}
