package com.jackting.module_msg.ui.msg;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IMsgProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.module_msg.R;
import com.jackting.module_msg.di.DaggerMsgComponent;

@Route(path = IMsgProvider.MSG_LIST_ACTIVITY)
public class MsgListActivity extends BaseActivity<MsgListPresenter> implements MsgListContract.View {


    @Override
    public int getContentViewResId() {
        return R.layout.msg_activity_msg_list;
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
        DaggerMsgComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
