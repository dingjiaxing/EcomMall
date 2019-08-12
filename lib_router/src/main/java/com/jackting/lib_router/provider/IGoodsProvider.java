package com.jackting.lib_router.provider;

import com.jackting.lib_router.provider.base.IFragmentProvider;

public interface IGoodsProvider extends IFragmentProvider {
    String GOODS_MAIN_SERVICE = "/goods/main/service";

    String GOODS_CATEGORY_FRAGMENT = "/goods/fragment/category";

    String GOODS_SEARCH_ACTIVITY = "/goods/activity/search";

    String GOODS_DETAIL_ACTIVITY = "goods/activity/detail";
}
