package com.jackting.lib_router.module.msg;

import com.jackting.lib_router.provider.IMsgProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.router.ModuleManager;

public class MsgService {
    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IMsgProvider.MSG_MAIN_SERVICE);
    }
}
