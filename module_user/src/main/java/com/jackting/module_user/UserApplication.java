package com.jackting.module_user;

import android.app.Application;

import com.jackting.common.CommonApplication;
import com.jackting.common.base.IApplication;
import com.jackting.common_http.manager.HttpManager;
import com.jackting.module_user.data.http.UserApiService;

public class UserApplication implements IApplication {
    public static  Application sApp = null;
    @Override
    public void onCreate(Application application) {
        sApp = application;
        initHttp();
    }

    void initHttp(){
        HttpManager.setConfigProvider(sApp, new HttpManager.ConfigProvider() {
            @Override
            public String getUserToken() {
                return null;
            }

            @Override
            public boolean isDebug() {
                return CommonApplication.isDebug();
            }

            @Override
            public String getBaseUrl() {
                return UserApiService.BASE_URL;
            }
        });
    }
}
