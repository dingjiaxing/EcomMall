package com.jackting.module_community.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;
import com.jackting.module_community.ui.main.MainCommunityFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = MainCommunityModule.class)
public interface CommunityComponent {
    void inject(MainCommunityFragment communityFragment);
}
