package com.jackting.lib_router.module.user;


import com.jackting.lib_router.config.ModuleBundle;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ModuleRouter;

import static com.jackting.lib_router.provider.IUserProvider.USER_PROFILE_LOGIN_ACTIVITY;

/**
 * Created by djx on 2017/4/21.
 */

public class UserIntent {

    public static void launchOrderUser(int tabType) {
        ModuleBundle bundle=new ModuleBundle();
//        bundle.put(IOrderProvider.,tabType);
        ModuleRouter.newInstance(USER_PROFILE_LOGIN_ACTIVITY)
            .withBundle(bundle)
            .navigation();
    }

    public static void startLoginActivity() {
        ModuleRouter.newInstance(USER_PROFILE_LOGIN_ACTIVITY).navigation();
//        ARouter.getInstance().build(USER_PROFILE_LOGIN_ACTIVITY).navigation();
    }
}
