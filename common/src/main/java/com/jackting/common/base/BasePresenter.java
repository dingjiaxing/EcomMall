package com.jackting.common.base;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IPresenter;
import com.jackting.common.base.interfaces.IView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected M model;
    protected V view;

    public BasePresenter(M model) {
        this.model=model;
    }


    public void takeView(V view) {
        this.view =  view;
        if(useEventBus()){
            EventBus.getDefault().register(this);
        }
        init();
    }

    @Override
    public void dropView() {
        model = null;
        view = null;
        if(useEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }
    //默认不使用EventBus，如果要使用请重写该方法并返回true
    public boolean useEventBus() {
        return false;
    }
}
