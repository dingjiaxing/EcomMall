package com.jackting.module_goods.ui.goods;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.module_goods.R;
import com.jackting.module_goods.di.DaggerGoodsComponent;

@Route(path = IGoodsProvider.GOODS_DETAIL_ACTIVITY)
public class GoodsDetailActivity extends BaseActivity<GoodsDetailPresenter> implements GoodsDetailContract.View {


    @Override
    public int getContentViewResId() {
        return R.layout.goods_activity_goods_detail;
    }

    @Override
    public void init(Bundle savedInstanceState) {


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void loginSuccess() {
        Log.i(TAG,"loginSuccess");
        MainIntent.startMainActivity();
        finish();
    }

    @Override
    public void daggerInit() {
        DaggerGoodsComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
