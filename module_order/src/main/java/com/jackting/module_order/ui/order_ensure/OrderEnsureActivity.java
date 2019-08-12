package com.jackting.module_order.ui.order_ensure;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.module_order.R;
import com.jackting.module_order.di.DaggerOrderComponent;

@Route(path = IOrderProvider.ORDER_ENSURE_ACTIVITY)
public class OrderEnsureActivity extends BaseActivity<OrderEnsurePresenter> implements OrderEnsureContract.View {

    public static String ORDER_INFO_KEY = "order_info_key";
    public static String ORDER_GOODS_PT_MODEL= "order_goods_pt_model";

    @Override
    public int getContentViewResId() {
        return R.layout.order_activity_order_ensure;
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
        DaggerOrderComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
