package com.jackting.module_user.ui.acccount.login;

import com.jackting.common.base.BasePresenter;
import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;
import com.jackting.common.di.scope.ActivityScope;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> {

    @Inject
    public LoginPresenter(LoginModel model) {
        super(model);
    }



    @Override
    public void init() {

    }

    public void login(String username, String pwd){
        view.loginSuccess();
    }

}
