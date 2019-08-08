package com.jackting.module_main;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.base.BaseProvider;

@Route(path = IMainProvider.MAIN_MAIN_SERVICE)
public class MainProvider extends BaseProvider implements IMainProvider {
    @Override
    public void init(Context context) {

    }
}
