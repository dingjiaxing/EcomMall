package com.jackting.module_user;

import android.content.Context;

import androidx.fragment.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jackting.common.data.config.ConfigDataEngine;
import com.jackting.lib_router.module.user.bean.UserEntity;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.provider.base.BaseProvider;
import com.jackting.lib_router.router.ModuleRouter;
import com.jackting.module_user.data.config.UserConfigKey;
import com.jackting.module_user.ui.profile.MainProfileFragment;

import javax.inject.Inject;

@Route(path = IUserProvider.USER_MAIN_SERVICE)
public class UserProvider extends BaseProvider implements IUserProvider {

    UserEntity userEntity;

    @Override
    public void init() {
        userEntity = ConfigDataEngine.getObject(UserConfigKey.USER_ENTITY,UserEntity.class);
    }


    @Override
    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override
    public boolean isLogin() {
        return userEntity==null?false:true;
    }

    @Override
    public String getUserId() {
        return isLogin()?String.valueOf(userEntity.id):"";
    }

    @Override
    public String getToken() {
        return isLogin()?userEntity.token:"";
    }

    @Override
    public String getUsername() {
        return isLogin()?userEntity.username:"";
    }

    @Override
    public void resetUserEntity() {
        userEntity = ConfigDataEngine.getObject(UserConfigKey.USER_ENTITY,UserEntity.class);
    }
}
