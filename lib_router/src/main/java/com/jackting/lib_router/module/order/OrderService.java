package com.jackting.lib_router.module.order;


import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.router.ModuleManager;

/**
 * Created by wxmylife on 2017/4/21.
 */

public class OrderService {
    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IOrderProvider.ORDER_MAIN_SERVICE);
    }

//    public static Fragment getModule3Fragment(Object... args) {
//        if (!hasModule()) return null;
//        return ServiceManager.getInstance().getOrderProvider().newInstance(args);
//    }
}
