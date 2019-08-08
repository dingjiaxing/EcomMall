package com.jackting.module_community.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = MainCommunityModule.class)
public interface CommunityComponent {
//    void inject(MainActivity loginActivity);
}
