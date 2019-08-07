package com.jackting.lib_router.provider;

import com.jackting.lib_router.provider.base.IFragmentProvider;

public interface IUserProvider extends IFragmentProvider {

    //服务
    String USER_MAIN_SERVICE = "/user/service";
    //作为Fragment被添加时候的key
    String USER_PROFILE_FRAGMENT = "user_profile_fragment";

    //loginActivity页面
    String USER_PROFILE_LOGIN_ACTIVITY = "/user/profile/login";

    //设置
    String USER_SETTING_ACTIVITY="/user/setting/activity";


}
