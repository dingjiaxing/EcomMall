package com.jackting.module_user.ui.acccount.about_us;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.module_user.R;
import com.jackting.module_user.di.component.DaggerUserComponent;

@Route(path = IUserProvider.USER_ABOUT_US_ACTIVITY)
public class AboutUsActivity extends BaseActivity<AboutUsPresenter> implements AboutUsContract.View {


    @Override
    public int getContentViewResId() {
        return R.layout.user_fragment_about_us;
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
