package com.jackting.lib_router.provider.base;


import androidx.fragment.app.Fragment;

public interface IFragmentProvider extends IBaseProvider {

    Fragment newInstance(String type, Object obj);
}
