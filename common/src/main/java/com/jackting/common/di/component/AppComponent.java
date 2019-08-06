package com.jackting.common.di.component;

import android.app.Application;

import com.jackting.common.CommonApplication;
import com.jackting.common.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Application providesApplication();

//    Application application();

//    void inject(Application application);

//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        Builder application(Application application);
//
//        AppComponent build();
//    }
}
