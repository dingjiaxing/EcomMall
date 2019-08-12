package com.jackting.ecommall;

import android.app.Activity;
import android.app.Application;

import com.jackting.common.CommonApplication;
import com.jackting.common.di.module.AppModule;
import com.jackting.module_user.UserApplication;

import javax.inject.Inject;


public class AppApplication extends CommonApplication  {


    @Override
    public void onCreate() {
        setIsDebug(BuildConfig.DEBUG);
        super.onCreate();
    }


}
