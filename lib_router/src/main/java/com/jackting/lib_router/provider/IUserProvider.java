package com.jackting.lib_router.provider;

import com.jackting.lib_router.module.user.bean.UserEntity;
import com.jackting.lib_router.provider.base.IFragmentProvider;

public interface IUserProvider extends IFragmentProvider {

    //服务
    String USER_MAIN_SERVICE = "/user/main/service";
    //作为Fragment被添加时候的key
    String USER_PROFILE_FRAGMENT = "/user/profile_fragment";

    //loginActivity页面
    String USER_PROFILE_LOGIN_ACTIVITY = "/user/profile/login";

    //设置
    String USER_SETTING_ACTIVITY="/user/setting/activity";

    //获取用户实体对象
    UserEntity getUserEntity();

    //判断是否登录
    boolean isLogin();

    //获取用户id
    String getUserId();

    //获取token
    String getToken();

    //获取用户名
    String getUsername();

    //重置用户实体，当用户状态变化时需要调用此方法
    void resetUserEntity();

}
