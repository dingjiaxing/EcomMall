package com.jackting.module_community;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.lib_router.provider.ICommunityProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.provider.base.BaseProvider;
import com.jackting.module_community.ui.main.MainCommunityFragment;

@Route(path = ICommunityProvider.COMMUNITY_MAIN_SERVICE)
public class CommunityProvider extends BaseProvider implements ICommunityProvider {
    @Override
    public void init(Context context) {

    }
}
