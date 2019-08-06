package com.jackting.module_user.ui.acccount.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;
import com.jackting.module_user.ui.acccount.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = LoginModule.class)
public interface UserComponent {
    void inject(LoginActivity loginActivity);
}
