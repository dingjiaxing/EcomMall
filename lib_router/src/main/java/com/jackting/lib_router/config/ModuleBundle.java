package com.jackting.lib_router.config;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by wxmylife on 2017/4/21.
 */

public class ModuleBundle {

    private Bundle bundle;

    public ModuleBundle() {
        bundle = new Bundle();
    }

    public ModuleBundle put(String key, int value) {
        bundle.putInt(key, value);
        return this;
    }

    public ModuleBundle put(String key, long value) {
        bundle.putLong(key, value);
        return this;
    }

    public ModuleBundle put(String key, String value) {
        if (TextUtils.isEmpty(value)) {
            return this;
        }
        bundle.putString(key, value);
        return this;
    }

    public ModuleBundle put(String key, Serializable value) {
        if (value == null) {
            return this;
        }
        bundle.putSerializable(key, value);
        return this;
    }

    public ModuleBundle put(String key, Parcelable value) {
        if (value == null) {
            return this;
        }
        bundle.putParcelable(key, value);
        return this;
    }

    public ModuleBundle put(String key, String[] arrays) {
        if (arrays == null) {
            return this;
        }
        bundle.putStringArray(key, arrays);
        return this;
    }

    public Bundle build() {
        return bundle;
    }
}
