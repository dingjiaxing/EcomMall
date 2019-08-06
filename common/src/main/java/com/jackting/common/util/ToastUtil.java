package com.jackting.common.util;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * toast tool
 */
public class ToastUtil {

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    /**
     * 弹出短暂的toast
     *
     * @param msg
     */
    public static void showToast(String msg) {
        if(mContext == null){
            throw new NullPointerException("ToastUtil context is null");
        }
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * 弹出短暂的toast
     *
     * @param stringId 要发出的信息的 stringId
     */
    public static void showToast(@StringRes int stringId) {
        if(mContext == null){
            throw new NullPointerException("ToastUtil context is null");
        }
        Toast.makeText(mContext, stringId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长时间的toast
     *
     * @param msg
     */
    public static void showLongToast(String msg) {
        if(mContext == null){
            throw new NullPointerException("ToastUtil context is null");
        }
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG)
                .show();
    }
}
