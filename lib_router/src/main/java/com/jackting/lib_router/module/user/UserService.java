package com.jackting.lib_router.module.user;


import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ModuleManager;

/**
 * Created by djx on 2017/4/21.
 */

public class UserService {

    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IUserProvider.USER_MAIN_SERVICE);
    }

//    public static Fragment getModule3Fragment(Object... args) {
//        if (!hasModule()) return null;
//        return ServiceManager.getInstance().getOrderProvider().newInstance(args);
//    }
}
