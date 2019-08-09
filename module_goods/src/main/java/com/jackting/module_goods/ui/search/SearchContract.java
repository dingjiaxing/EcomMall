package com.jackting.module_goods.ui.search;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;
import com.jackting.lib_router.module.user.bean.UserEntity;


public interface SearchContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
//        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
