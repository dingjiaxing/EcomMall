package com.jackting.common.base;

import com.jackting.common.base.interfaces.IModel;
import com.jackting.common.base.interfaces.IPresenter;
import com.jackting.common.base.interfaces.IView;

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
        init();
    }

    @Override
    public void dropView() {
        model = null;
        view = null;
    }
}
