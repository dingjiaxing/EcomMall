package com.jackting.module_user.ui.acccount.login;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IPresenter;
import com.jackting.common.base.interfaces.IView;
import com.jackting.common_http.result.HttpRespResult;
import com.jackting.lib_router.module.user.bean.UserEntity;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface LoginContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{
        Observable<HttpRespResult<UserEntity>> login(String name, String pwd);
    }

}
