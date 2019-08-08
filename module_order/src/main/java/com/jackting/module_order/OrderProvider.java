package com.jackting.module_order;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.base.BaseProvider;

@Route(path = IOrderProvider.ORDER_MAIN_SERVICE)
public class OrderProvider extends BaseProvider implements IOrderProvider {

    @Override
    public void init(Context context) {

    }
}
