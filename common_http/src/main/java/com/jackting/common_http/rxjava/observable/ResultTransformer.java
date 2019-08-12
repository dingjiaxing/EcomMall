package com.jackting.common_http.rxjava.observable;

import com.jackting.common_http.okhttp.CommonRespCode;
import com.jackting.common_http.result.HttpRespException;
import com.jackting.common_http.result.HttpRespResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/29.
 */

public class ResultTransformer {

    public static <T> ObservableTransformer<HttpRespResult<T>, T> transformer() {
        return new ObservableTransformer<HttpRespResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpRespResult<T>> upstream) {
                return upstream
                        .flatMap(ResultTransformer.<T>flatMap())
                        .compose(SchedulerTransformer.<T>transformer());
            }
        };
    }

    private static <T> Function<HttpRespResult<T>, ObservableSource<T>> flatMap() {
        return new Function<HttpRespResult<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NonNull final HttpRespResult<T> tHttpResponseResult) throws Exception {
                return new Observable<T>() {

                    @Override
                    protected void subscribeActual(Observer<? super T> observer) {

                        if (tHttpResponseResult.isSuccess()) {

                            if (tHttpResponseResult.getData() != null) {
                                observer.onNext(tHttpResponseResult.getData());
                            }
                            observer.onComplete();

                        } else {
                            observer.onError(new HttpRespException(tHttpResponseResult.getMessage(), CommonRespCode.HTTP_RESP_SUCCESS_CODE, tHttpResponseResult.getCode()));
                        }
                    }
                };
            }
        };
    }


}
