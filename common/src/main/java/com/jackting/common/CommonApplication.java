package com.jackting.common;

import android.app.Application;
import android.content.Context;

import com.jackting.common.base.IApplication;
import com.jackting.common.config.ModuleConfig;
import com.jackting.common.data.config.ConfigDataEngine;
import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.component.DaggerAppComponent;
import com.jackting.common.di.module.AppModule;
import com.jackting.common.util.ToastUtil;

public class CommonApplication extends Application {

    protected static Application sApplication;
    protected static boolean sIsDebug=true;
    private static AppComponent appComponent;



    public static Context getContext(){
        return sApplication;
    }

    public static boolean isDebug() {
        return sIsDebug;
    }

    public static void setIsDebug(boolean sIsDebug) {
        CommonApplication.sIsDebug = sIsDebug;
    }

    private void initInjector(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(sApplication))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        initInjector();
        modulesApplicationInit();
        //初始化配置数据引擎
        ConfigDataEngine.init(this);
        ToastUtil.init(this);
    }

    private void modulesApplicationInit(){
        for (String moduleImpl : ModuleConfig.MODULE_LIST){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IApplication){
                    ((IApplication) obj).onCreate(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
