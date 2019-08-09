package com.jackting.module_goods.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseActivity;
import com.jackting.common.util.ToastUtil;
import com.jackting.lib_router.module.main.MainIntent;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.module_goods.R;
import com.jackting.module_goods.di.DaggerGoodsComponent;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = IGoodsProvider.GOODS_SEARCH_ACTIVITY)
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {


    @Override
    public int getContentViewResId() {
        return R.layout.goods_fragment_search;
    }

    @Override
    public void init(Bundle savedInstanceState) {


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void loginSuccess() {
        Log.i(TAG,"loginSuccess");
        MainIntent.startMainActivity();
        finish();
    }

    @Override
    public void daggerInit() {
        DaggerGoodsComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }
}
