package com.jackting.ecommall;

import android.app.Activity;
import android.app.Application;

import com.jackting.common.CommonApplication;
import com.jackting.common.di.module.AppModule;
import com.jackting.module_user.UserApplication;

import javax.inject.Inject;


public class AppApplication extends CommonApplication  {

    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
//        initDagger();
//        new CommonApplication().onCreate(this);
//        new UserApplication().onCreate(this);
    }

//    void initDagger(){
//        DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
//    }
//
//    @Override
//    public AndroidInjector<Activity> activityInjector() {
//        return dispatchingAndroidInjector;
//    }
}
