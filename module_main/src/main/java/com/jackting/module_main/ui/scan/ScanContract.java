package com.jackting.module_main.ui.scan;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;


public interface ScanContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
//        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
