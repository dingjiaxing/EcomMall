package com.jackting.module_user.ui.account.login;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;
import com.jackting.common_http.result.HttpRespResult;
import com.jackting.lib_router.module.user.bean.UserEntity;

import io.reactivex.Observable;

public interface LoginContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
