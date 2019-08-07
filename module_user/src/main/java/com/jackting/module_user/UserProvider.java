package com.jackting.module_user;

import android.content.Context;

import androidx.fragment.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.lib_router.provider.IUserProvider;

import javax.inject.Inject;

@Route(path = IUserProvider.USER_MAIN_SERVICE)
public class UserProvider   implements IUserProvider {

//    MainProfileFragment profileFragment;

    @Override
    public Fragment newInstance(String type, Object obj) {
        Fragment fragment = null;
        switch (type){
            case IUserProvider.USER_PROFILE_FRAGMENT:
//                fragment = MainProfileFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public void init(Context context) {

    }
}
