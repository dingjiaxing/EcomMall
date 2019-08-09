package com.jackting.lib_router.module.community;

import com.jackting.lib_router.provider.ICommunityProvider;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.router.ModuleManager;

public class CommunityService {

    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(ICommunityProvider.COMMUNITY_MAIN_SERVICE);
    }

}
