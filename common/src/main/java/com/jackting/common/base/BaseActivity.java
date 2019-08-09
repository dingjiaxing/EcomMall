package com.jackting.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.InflateException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jackting.common.CommonApplication;
import com.jackting.common.base.interfaces.IActivity;
import com.jackting.common.base.interfaces.IPresenter;
import com.jackting.common.base.interfaces.IView;
import com.jackting.common.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, IView {

    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;
    @Inject
    @Nullable
    protected P presenter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResId = getContentViewResId();
            if(layoutResId != 0){
                setContentView(getContentViewResId());
                mUnbinder = ButterKnife.bind(this);
            }
            daggerInit();
            if(presenter != null && presenter instanceof BasePresenter){
                ((BasePresenter)presenter).takeView(this);
            }
            if(useEventBus()){
                EventBus.getDefault().register(this);
            }
        }catch (Exception e){
            if(e instanceof InflateException) throw e;
            e.printStackTrace();
        }
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(useEventBus()){
            EventBus.getDefault().unregister(this);
        }
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
        mUnbinder = null;
    }



    protected AppComponent getAppComponent(){
        return CommonApplication.getAppComponent();
    }

//    public abstract BasePresenter createPresenter();

    //默认不使用EventBus，如果要使用请重写该方法并返回true
    public boolean useEventBus() {
        return false;
    }
}
