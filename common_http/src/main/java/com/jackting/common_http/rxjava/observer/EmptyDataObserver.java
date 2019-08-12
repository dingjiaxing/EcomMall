package com.jackting.common_http.rxjava.observer;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:
 * <p>@author:Created by Devin Sun on 2018/2/28.
 */
public abstract class EmptyDataObserver<T> extends BaseObserver<T> {
    @Override
    public void onComplete() {
        super.onComplete();
        onSuccess();
    }

    public abstract void onSuccess();
}
