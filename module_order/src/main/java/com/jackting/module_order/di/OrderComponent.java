package com.jackting.module_order.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;
import com.jackting.module_order.ui.order_ensure.OrderEnsureActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = OrderModule.class)
public interface OrderComponent {
    void inject(OrderEnsureActivity loginActivity);
}
