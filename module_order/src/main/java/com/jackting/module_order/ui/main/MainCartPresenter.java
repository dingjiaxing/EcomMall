package com.jackting.module_order.ui.main;

import com.jackting.common.base.BasePresenter;

import javax.inject.Inject;

public class MainCartPresenter extends BasePresenter<MainCartContract.Model, MainCartContract.View> {

    @Inject
    public MainCartPresenter(MainCartModel model) {
        super(model);
    }

    @Override
    public void init() {

    }

    void getCartList(){
        // todo 通过接口获取数据，demo为从json中拿数据

    }

    void deleteCart(String recIds){

    }

    void getShippingFee(String recIds){

    }
}
