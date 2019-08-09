package com.jackting.lib_router.module.main;

import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.router.ModuleRouter;

public class MainIntent {


    public static void startMainActivity() {
        ModuleRouter.newInstance(IMainProvider.MAIN_ACTIVITY).navigation();
    }


}
