package com.jackting.lib_router.module.main;

import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.router.ModuleManager;

public class MainService {

    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IMainProvider.MAIN_MAIN_SERVICE);
    }


}
