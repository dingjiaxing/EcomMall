package com.jackting.lib_router.provider.base;


import androidx.fragment.app.Fragment;

import com.jackting.lib_router.config.ModuleBundle;

public interface IFragmentProvider extends IBaseProvider {

    Fragment newInstance(String type);

    Fragment newInstance(String type, ModuleBundle moduleBundle);
}
