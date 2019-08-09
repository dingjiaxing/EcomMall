package com.jackting.module_user.ui.acccount.register;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.module_user.R;
import com.jackting.module_user.di.component.DaggerUserComponent;

@Route(path = IUserProvider.USER_REGISTER_ACTIVITY)
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {


    @Override
    public int getContentViewResId() {
        return R.layout.user_fragment_main_profile;
    }

    @Override
    public void init(Bundle savedInstanceState) {


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void loginSuccess() {
        Log.i(TAG,"loginSuccess");
        MainIntent.startMainActivity();
        finish();
    }

    @Override
    public void daggerInit() {
        DaggerUserComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
