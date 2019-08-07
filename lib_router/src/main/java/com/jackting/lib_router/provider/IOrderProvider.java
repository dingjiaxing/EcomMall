package com.jackting.lib_router.provider;

import com.jackting.lib_router.provider.base.IFragmentProvider;

public interface IOrderProvider extends IFragmentProvider {
    //服务
    String ORDER_MAIN_SERVICE = "/order/main/service";

    //个人中心的订单列表
    String ORDER_USER_CENTER="/order/user/center";

}
