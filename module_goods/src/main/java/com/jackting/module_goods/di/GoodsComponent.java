package com.jackting.module_goods.di;

import com.jackting.common.di.component.AppComponent;
import com.jackting.common.di.scope.PerActivity;
import com.jackting.module_goods.ui.goods.GoodsDetailActivity;
import com.jackting.module_goods.ui.main.MainCategoryFragment;
import com.jackting.module_goods.ui.search.SearchActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = MainGoodsModule.class)
public interface GoodsComponent {
    void inject(SearchActivity activity);
    void inject(GoodsDetailActivity activity);
    void inject(MainCategoryFragment categoryFragment);
}
