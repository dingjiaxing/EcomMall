package com.jackting.common_http.retrofit;


import com.jackting.common_http.manager.HttpManager;
import com.jackting.common_http.okhttp.OkHttpHelper;
import com.jackting.common_http.retrofit.converter.string.StringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/24.
 */

public class RetrofitHelper {



    public static final String BASE_URL = HttpManager.getBaseUrl();


    private static Retrofit retrofit;

    private RetrofitHelper() {
    }

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitHelper.BASE_URL)
                .client(OkHttpHelper.getClient())
                .addConverterFactory(StringConverterFactory.create()) //String 转换
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .validateEagerly(true)
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
