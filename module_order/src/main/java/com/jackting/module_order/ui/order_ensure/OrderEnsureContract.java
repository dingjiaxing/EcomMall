package com.jackting.module_order.ui.order_ensure;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;


public interface OrderEnsureContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
//        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
