package com.jackting.module_user.di.component;


import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.ActivityScope;
import com.jackting.module_user.di.module.LoginModule;
import com.jackting.module_user.ui.account.about_us.AboutUsActivity;
import com.jackting.module_user.ui.account.login.LoginActivity;
import com.jackting.module_user.ui.account.login.LoginContract;
import com.jackting.module_user.ui.account.register.RegisterActivity;
import com.jackting.module_user.ui.profile.MainProfileFragment;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(LoginActivity activity);
    void inject(AboutUsActivity activity);
    void inject(RegisterActivity activity);

    void inject(MainProfileFragment profileFragment);


//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        UserComponent.Builder view(LoginContract.View view);
//        UserComponent.Builder appComponent(AppComponent component);
//        UserComponent build();
//    }
}
