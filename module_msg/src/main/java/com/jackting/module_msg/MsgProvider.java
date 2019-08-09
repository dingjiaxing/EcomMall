package com.jackting.module_msg;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.lib_router.provider.IMsgProvider;
import com.jackting.lib_router.provider.base.BaseProvider;

@Route(path = IMsgProvider.MSG_MAIN_SERVICE)
public class MsgProvider extends BaseProvider implements IMsgProvider {

    @Override
    public void init() {

    }
}
