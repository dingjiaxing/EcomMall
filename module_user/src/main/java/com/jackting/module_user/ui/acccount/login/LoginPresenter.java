package com.jackting.module_user.ui.acccount.login;

import android.app.Activity;

import com.jackting.common.CommonApplication;
import com.jackting.common.base.BasePresenter;
import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IView;
import com.jackting.common.data.config.ConfigDataEngine;
import com.jackting.common_http.result.HttpRespResult;
import com.jackting.common_http.rxjava.observable.DialogTransformer;
import com.jackting.common_http.rxjava.observable.SchedulerTransformer;
import com.jackting.common_http.rxjava.observer.CommonObserver;
import com.jackting.lib_router.module.user.bean.UserEntity;
import com.jackting.lib_router.router.ServiceManager;
import com.jackting.module_user.R;
import com.jackting.module_user.data.config.UserConfigKey;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> {

    @Inject
    public LoginPresenter(LoginModel model) {
        super(model);
    }



    @Override
    public void init() {

    }

    public void login(String username, String pwd){
        model.login(username,pwd)
                .compose(SchedulerTransformer.transformer())
//                .compose(RxLifecycle.bind(view))
                .compose(new DialogTransformer((Activity) view, R.string.user_logining,false).transformer())
                .subscribe(loginObserver);


    }

    CommonObserver loginObserver = new CommonObserver<HttpRespResult<UserEntity>>() {
        @Override
        public void onSuccess(HttpRespResult<UserEntity> respResult) {
            ConfigDataEngine.putObject(UserConfigKey.USER_ENTITY,respResult.getData());
            ServiceManager.getInstance().getUserProvider().resetUserEntity();
            view.loginSuccess();
        }

        @Override
        public void onSubscribe(Disposable d) {
            super.onSubscribe(d);

        }
    };



}
