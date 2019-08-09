package com.jackting.lib_router.module.goods;

import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.router.ModuleManager;

public class GoodsService {

    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IGoodsProvider.GOODS_MAIN_SERVICE);
    }

}
