package com.jackting.lib_router.provider.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.jackting.lib_router.config.ModuleBundle;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ModuleRouter;

public abstract class BaseProvider implements IFragmentProvider {
    @Override
    public Fragment newInstance(String type) {
        return newInstance(type, null);
    }

    @Override
    public Fragment newInstance(String type, ModuleBundle moduleBundle) {
        Fragment fragment = null;
        if(!TextUtils.isEmpty(type)){
            fragment = (Fragment) ModuleRouter.newInstance(type).withBundle(moduleBundle).navigation();
        }
        return fragment;
    }
}
