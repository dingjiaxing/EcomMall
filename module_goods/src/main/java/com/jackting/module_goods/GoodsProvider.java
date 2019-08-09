package com.jackting.module_goods;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.base.BaseProvider;

@Route(path = IGoodsProvider.GOODS_MAIN_SERVICE)
public class GoodsProvider extends BaseProvider implements IGoodsProvider {

    @Override
    public void init() {

    }
}
