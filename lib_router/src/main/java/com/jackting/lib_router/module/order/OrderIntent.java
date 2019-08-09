package com.jackting.lib_router.module.order;


import com.jackting.lib_router.config.ModuleBundle;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.router.ModuleRouter;

/**
 * Created by djx on 2017/4/21.
 */

public class OrderIntent {

    public static void launchOrderUser(int tabType) {
        ModuleBundle bundle=new ModuleBundle();
//        bundle.put(IOrderProvider.,tabType);
        ModuleRouter.newInstance(IOrderProvider.ORDER_USER_CENTER)
            .withBundle(bundle)
            .navigation();
    }
}
