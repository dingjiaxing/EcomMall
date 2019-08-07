package com.jackting.module_user.ui.acccount.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.jackting.common.base.BaseActivity;
import com.jackting.common.base.BasePresenter;
import com.jackting.common.di.component.AppComponent;
import com.jackting.module_user.R;
import com.jackting.module_user.R2;
import com.jackting.module_user.di.component.DaggerUserComponent;

import org.xml.sax.helpers.ParserAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R2.id.btn_login)
    Button btnLogin;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_login;
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
