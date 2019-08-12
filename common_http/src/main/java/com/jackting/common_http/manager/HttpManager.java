package com.jackting.common_http.manager;

import android.content.Context;


/**
 * <p>Description:
 * <p>Company:
 * <p>Email:
 * <p>@author:Created by Devin Sun on 2018/1/2.
 */

public class HttpManager {

    private static ConfigProvider configProvider;
    public static Context context;

    public static void setConfigProvider(Context context, ConfigProvider configProvider) {
        HttpManager.context=context.getApplicationContext();
        HttpManager.configProvider = configProvider;
    }

    public static String getToken() {
        return configProvider == null ? "" : configProvider.getUserToken();
    }


    public static boolean isDebug() {
        return configProvider != null && configProvider.isDebug();
    }

    public static String getBaseUrl() {
        return configProvider == null ? "" : configProvider.getBaseUrl();
    }



    public interface ConfigProvider {


        /**
         * 用户的token  未登录时可传""或者null，非空时视为登录状态
         *
         * @return
         */
        String getUserToken();

        /**
         * 决定是否打印请求日志,在调试时可置为false
         *
         * @return
         */
        boolean isDebug();


        String getBaseUrl();


    }


}
