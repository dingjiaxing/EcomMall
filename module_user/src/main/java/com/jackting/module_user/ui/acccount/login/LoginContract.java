package com.jackting.module_user.ui.acccount.login;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IPresenter;
import com.jackting.common.base.interfaces.IView;

public interface LoginContract {

    interface View extends IView{
        void loginSuccess();
    }

    interface Model extends IModel{

    }

}
