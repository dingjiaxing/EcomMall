package com.jackting.module_msg.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;
import com.jackting.module_msg.ui.msg.MsgListActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = MsgModule.class)
public interface MsgComponent {
    void inject(MsgListActivity activity);
}
