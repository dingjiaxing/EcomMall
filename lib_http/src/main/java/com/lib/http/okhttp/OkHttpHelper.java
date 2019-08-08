package com.lib.http.okhttp;


import com.lib.http.okhttp.interceptor.PrintLoggingInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/24.
 */

public class OkHttpHelper {

    private static OkHttpClient okHttpClient;

    /**
     * 连接超时
     */
    private static final int CONNECT_TIMEOUT = 30;
    /**
     * 读取超时
     */
    private static final int READ_TIMEOUT = 50;
    /**
     * 写入超时
     */
    private static final int WRITE_TIMEOUT = 50;

    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    public  static  void  clearCookie() {
        cookieStore.clear();
    }

    private OkHttpHelper() {
    }

    static {

//        CustomHttpsTrust customHttpsTrust = new CustomHttpsTrust();
//        CustomHttpsTrust customHttpsTrust = new CustomHttpsTrust(CertificateStreamFactory.trustedCertificatesInputStream("xxx"));

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

                .addInterceptor(new PrintLoggingInterceptor(true, true))  //请求信息的打印 ，可在 release 时关闭
//                .sslSocketFactory(customHttpsTrust.sSLSocketFactory, customHttpsTrust.x509TrustManager)// https 配置
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(),cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies==null?new ArrayList<Cookie>():cookies;
                    }
                })
                .build();
    }

    public static OkHttpClient getClient() {
        return okHttpClient;
    }

}
