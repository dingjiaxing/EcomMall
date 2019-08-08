package com.lib.http.rxjava.observer;

import android.widget.Toast;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:
 * <p>@author:Created by Devin Sun on 2018/2/28.
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);


}
