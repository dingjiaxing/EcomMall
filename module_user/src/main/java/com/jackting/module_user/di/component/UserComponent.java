package com.jackting.module_user.di.component;


import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.ActivityScope;
import com.jackting.module_user.di.module.LoginModule;
import com.jackting.module_user.ui.acccount.login.LoginActivity;
import com.jackting.module_user.ui.acccount.login.LoginContract;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(LoginActivity activity);


//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        UserComponent.Builder view(LoginContract.View view);
//        UserComponent.Builder appComponent(AppComponent component);
//        UserComponent build();
//    }
}
