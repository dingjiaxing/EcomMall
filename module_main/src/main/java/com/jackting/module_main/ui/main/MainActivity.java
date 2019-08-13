package com.jackting.module_main.ui.main;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.jackting.common.base.BaseActivity;
import com.jackting.lib_router.provider.ICommunityProvider;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ServiceManager;
import com.jackting.module_main.R;
import com.jackting.module_main.R2;
import com.jackting.module_main.di.DaggerMainComponent;
import com.jackting.module_main.ui.main.home.MainHomeFragment;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = IMainProvider.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R2.id.bottom_view)
    BottomNavigationView bottomBar;

    FragmentTransaction ft;
    @Inject
    MainHomeFragment homeFragment;

    Fragment categoryFragment;
    Fragment communityFragment;
    Fragment cartFragment;
    Fragment profileFragment;

    private int lastSelectIndex=0;

    @Override
    public int getContentViewResId() {
        return R.layout.main_activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        bottomBar=findViewById(R.id.bottom_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            disableShiftMode(bottomBar);
        }

        initFragment();

        bottomBar.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int i = menuItem.getItemId();
                if (i == R.id.menu_home) {
                    selectFragment(0);
                    lastSelectIndex=0;
                } else if (i == R.id.menu_category) {
                    selectFragment(1);
                    lastSelectIndex=1;
                }else if (i == R.id.menu_community) {
                    selectFragment(2);
                    lastSelectIndex=2;
                }else if (i == R.id.menu_cart) {
                    selectFragment(3);
                    lastSelectIndex=3;
                }else if (i == R.id.menu_profile) {
                    selectFragment(4);
                    lastSelectIndex=4;
                }
                return true;
            }
        });
    }

    void initFragment() {
//        profileFragment = ServiceManager.getInstance().getUserProvider().newInstance(IUserProvider.USER_PROFILE_FRAGMENT,null);
        communityFragment = ServiceManager.getInstance().getCommunityProvider().newInstance(ICommunityProvider.MAIN_COMMUNITY_FRAGMENT);
        profileFragment = ServiceManager.getInstance().getUserProvider().newInstance(IUserProvider.USER_PROFILE_FRAGMENT);
        categoryFragment = ServiceManager.getInstance().getGoodsProvider().newInstance(IGoodsProvider.GOODS_CATEGORY_FRAGMENT);
        cartFragment = ServiceManager.getInstance().getOrderProvider().newInstance(IOrderProvider.ORDER_CART_FRAGMENT);

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container,categoryFragment);
        ft.add(R.id.main_container, profileFragment);
        ft.add(R.id.main_container, communityFragment);
        ft.add(R.id.main_container,cartFragment);

        ft.hide(categoryFragment);
        ft.hide(profileFragment);
        ft.hide(communityFragment);
        ft.hide(cartFragment);

        ft.add(R.id.main_container, homeFragment);
        ft.commit();
        selectFragment(0);
    }

    private void selectFragment(int tabId) {
        ft = getSupportFragmentManager().beginTransaction();
        hideFragment(lastSelectIndex);
        switch (tabId) {
            case 0:
                homeFragment.onResume();
                ft.show(homeFragment);
                break;
            case 1:
                categoryFragment.onResume();
                ft.show(categoryFragment);
                break;
            case 2:
                communityFragment.onResume();
                ft.show(communityFragment);
                break;
            case 3:
                cartFragment.onResume();
                ft.show(cartFragment);
                break;
            case 4:
                profileFragment.onResume();
                ft.show(profileFragment);
                break;
        }
        ft.commit();
    }
    void hideFragment(int index) {
        switch (index) {
            case 0:
                if (homeFragment != null) {
                    ft.hide(homeFragment);
                }
                break;
            case 1:
                if (categoryFragment != null) {
                    ft.hide(categoryFragment);
                }
                break;
            case 2:
                if (communityFragment != null) {
                    ft.hide(communityFragment);
                }
                break;
            case 3:
                if (cartFragment != null) {
                    ft.hide(cartFragment);
                }
                break;
            case 4:
                if (profileFragment != null) {
                    ft.hide(profileFragment);
                }
                break;
        }
    }



    @Override
    public void daggerInit() {
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
    }
}
