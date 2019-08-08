package com.jackting.module_goods.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = MainGoodsModule.class)
public interface GoodsComponent {
//    void inject(MainActivity loginActivity);
}
