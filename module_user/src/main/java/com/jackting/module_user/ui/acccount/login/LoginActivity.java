package com.jackting.module_user.ui.acccount.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.module_user.R;
import com.jackting.module_user.R2;
import com.jackting.module_user.di.component.DaggerUserComponent;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = IUserProvider.USER_PROFILE_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R2.id.btn_login)
    Button btnLogin;

    @Override
    public int getContentViewResId() {
        return R.layout.user_activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        presenter.login(null,null);

    }

    @OnClick({R2.id.btn_login})
    public void doClick(View view){
        if(view.getId()==R.id.btn_login){
            presenter.login(null,null);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void loginSuccess() {
        Log.i(TAG,"loginSuccess");
    }

    @Override
    public void daggerInit() {
        DaggerUserComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
