package com.jackting.common_http.okhttp.interceptor;


import android.text.TextUtils;

import com.devin.util.AppInfo;
import com.devin.util.DeviceInfo;
import com.devin.util.NetworkUtils;
import com.jackting.common_http.manager.HttpManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>Description:
 * <p>Created by Devin Sun on 2017/3/29.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder()
                .addHeader("imei", DeviceInfo.getIMEI())
                .addHeader("osName", "android")
                .addHeader("osVersion", DeviceInfo.getSysVersionName())
                .addHeader("versionCode", AppInfo.getVersionCode() + "")
                .addHeader("networkType", NetworkUtils.isWifi() ? "WIFI" : "4G");
        if (!TextUtils.isEmpty(HttpManager.getToken())) {
            builder.addHeader("accessToken", HttpManager.getToken());
        }
        // add header...
        Request request = builder.build();

        return chain.proceed(request);
    }
}
