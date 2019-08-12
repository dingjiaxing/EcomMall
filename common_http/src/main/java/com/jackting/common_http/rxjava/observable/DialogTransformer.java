package com.jackting.common_http.rxjava.observable;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Company:
 * Email:
 * Created by Devin Sun on 2017/4/3.
 */
public class DialogTransformer {

    private static final String DEFAULT_MSG = "数据加载中...";

    private Activity activity;
    private String msg;
    private boolean cancelable;

    public DialogTransformer(Activity activity) {
        this(activity, DEFAULT_MSG);
    }

    public DialogTransformer(Activity activity, String msg) {
        this(activity, msg, false);
    }

    public DialogTransformer(Activity activity, int msgStringId) {
        this(activity,activity.getString(msgStringId),false);
    }

    public DialogTransformer(Activity activity, int msgStringId, boolean cancelable) {
        this(activity,activity.getString(msgStringId),cancelable);
    }

    public DialogTransformer(Activity activity, String msg, boolean cancelable) {
        this.activity = activity;
        this.msg = msg;
        this.cancelable = cancelable;
    }

    public <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {
            private ProgressDialog progressDialog;
            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {

                return  upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull final Disposable disposable) throws Exception {
                        progressDialog = ProgressDialog.show(activity, null, msg, true, cancelable);
                        progressDialog.setProgressStyle(ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
                        if (cancelable) {
                            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    disposable.dispose();
                                }
                            });
                        }
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                    }
                });
            }
        };
    }
}
