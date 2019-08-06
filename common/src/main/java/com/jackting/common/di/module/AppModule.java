package com.jackting.common.di.module;

import android.app.Application;

import com.jackting.common.CommonApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public  class AppModule {

    private Application commonApplication;

    public AppModule(Application commonApplication) {
        this.commonApplication = commonApplication;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return commonApplication;
    }
}
