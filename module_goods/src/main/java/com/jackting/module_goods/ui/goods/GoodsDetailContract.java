package com.jackting.module_goods.ui.goods;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;


public interface GoodsDetailContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
//        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
