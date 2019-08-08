package com.jackting.module_user;

import android.content.Context;

import androidx.fragment.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.provider.base.BaseProvider;
import com.jackting.lib_router.router.ModuleRouter;
import com.jackting.module_user.ui.profile.MainProfileFragment;

import javax.inject.Inject;

@Route(path = IUserProvider.USER_MAIN_SERVICE)
public class UserProvider extends BaseProvider implements IUserProvider {

    @Override
    public void init(Context context) {

    }

    @Override
    public void startLoginActivity() {
        ModuleRouter.newInstance(USER_PROFILE_LOGIN_ACTIVITY).navigation();
//        ARouter.getInstance().build(USER_PROFILE_LOGIN_ACTIVITY).navigation();
    }
}
