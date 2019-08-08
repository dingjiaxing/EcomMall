package com.jackting.module_goods.ui.main;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainCategoryPresenter extends BasePresenter<MainCategoryContract.Model, MainCategoryContract.View> {

    @Inject
    public MainCategoryPresenter(MainCategoryModel model) {
        super(model);
    }

    @Override
    public void init() {
        getArticleList();
    }
    void getArticleList(){

    }
}
