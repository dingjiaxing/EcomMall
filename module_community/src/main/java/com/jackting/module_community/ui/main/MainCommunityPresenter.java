package com.jackting.module_community.ui.main;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainCommunityPresenter extends BasePresenter<MainCommunityContract.Model, MainCommunityContract.View> {

    @Inject
    public MainCommunityPresenter(MainCommunityModel model) {
        super(model);
    }

    @Override
    public void init() {
        getArticleList();
    }
    void getArticleList(){

    }
}
