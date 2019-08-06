package com.jackting.module_main.ui.main;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.jackting.common.base.BaseActivity;
import com.jackting.common.base.interfaces.IPresenter;
import com.jackting.module_main.R;
import com.jackting.module_main.R2;
import com.jackting.module_main.di.DaggerMainComponent;
import com.jackting.module_main.ui.main.home.MainHomeFragment;
import com.jackting.module_main.ui.main.profile.MainProfileFragment;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R2.id.bottom_view)
    BottomNavigationView bottomBar;

    FragmentTransaction ft;
    @Inject
    MainHomeFragment homeFragment;
    @Inject
    MainProfileFragment profileFragment;

    private int lastSelectIndex=0;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main2;
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
                } else if (i == R.id.menu_profile) {
                    selectFragment(1);
                    lastSelectIndex=1;
                }
                return true;
            }
        });
//        bottomBar
//                .addItem(new BottomNavigationItem(R.drawable.selector_main_home,getString(R.string.tab_home)))
//                .addItem(new BottomNavigationItem(R.drawable.selector_main_user,getString(R.string.tab_profile)))
//                .setFirstSelectedPosition(0)
//                .initialise();
//
//        bottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position) {
//                selectFragment(position);
//            }
//
//            @Override
//            public void onTabUnselected(int position) {
//                hideFragment(position);
//            }
//            @Override
//            public void onTabReselected(int position) {
//            }
//        });
    }

    void initFragment() {
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container, profileFragment);
        ft.hide(profileFragment);
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
