package com.jackting.common.base.interfaces;


import android.os.Bundle;

import androidx.annotation.LayoutRes;

public interface IFragment {

    @LayoutRes
    int getContentViewResId();

    void init(Bundle savedInstanceState);

    void daggerInit();
}
