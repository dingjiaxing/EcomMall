package com.jackting.common.base.interfaces;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.jackting.common.base.BasePresenter;
import com.jackting.common.di.component.AppComponent;

public interface IActivity {

    @LayoutRes
    int getContentViewResId();

    void init(Bundle savedInstanceState);

//    void setupActivityComponent(@NonNull AppComponent appComponent);

    void daggerInit();

}
