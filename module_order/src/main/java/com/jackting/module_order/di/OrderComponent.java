package com.jackting.module_order.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = OrderModule.class)
public interface OrderComponent {
//    void inject(MainActivity loginActivity);
}
