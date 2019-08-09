package com.jackting.module_msg.ui.msg;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;


public interface MsgListContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
//        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
