package com.jackting.module_main.ui.scan;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.module_main.R;
import com.jackting.module_main.di.DaggerMainComponent;

@Route(path = IMainProvider.SCAN_ACTIVITY)
public class ScanActivity extends BaseActivity<ScanPresenter> implements ScanContract.View {


    @Override
    public int getContentViewResId() {
        return R.layout.main_fragment_scan;
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
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
