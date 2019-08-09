package com.jackting.module_user.ui.acccount.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.common.util.ToastUtil;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ModuleRouter;
import com.jackting.lib_router.router.ServiceManager;
import com.jackting.module_user.R;
import com.jackting.module_user.R2;
import com.jackting.module_user.di.component.DaggerUserComponent;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = IUserProvider.USER_PROFILE_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.et_user_login_username)
    EditText etName;
    @BindView(R2.id.et_user_login_pwd)
    EditText etPwd;

    @Override
    public int getContentViewResId() {
        return R.layout.user_activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {


    }

    @OnClick({R2.id.btn_login})
    public void doLogin(){
        presenter.login(etName.getText().toString(),etPwd.getText().toString());
    }

    @OnClick(R2.id.ll_user_login_wechat)
    public void clickWechat(){
        ToastUtil.showToast("敬请期待");
    }

    @OnClick(R2.id.ll_base_title_left)
    public void doBack(){
        finish();
    }

    @OnClick(R2.id.tv_base_right)
    public void doRegister(){
        ModuleRouter.newInstance(IUserProvider.USER_REGISTER_ACTIVITY).navigation();
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
