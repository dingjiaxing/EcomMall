package com.jackting.common.data.config;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tencent.mmkv.MMKV;

import java.util.Set;

/**
 * ConfigDataEngine: 配置数据引擎
 * 用门面模式封装一下，当前版本使用mmkv，后续方便修改为其他引擎
 */
public class ConfigDataEngine {

    private static MMKV mmkv = null;

    public static void init(Context context){
        MMKV.initialize(context);
        mmkv = MMKV.defaultMMKV();
    }

    public static int getInt(final String key, final int defValue) {
        return mmkv.getInt(key,defValue);
    }

    public static boolean getBoolean(final String key, final boolean defValue) {
        return mmkv.getBoolean(key,defValue);
    }

    public static long getLong(final String key, final long defValue) {
        return mmkv.getLong(key,defValue);
    }
    public static double getDouble(final String key, final double defValue) {
        return mmkv.decodeDouble(key,defValue);
    }
    public static float getFloat(final String key, final float defValue) {
        return mmkv.getFloat(key,defValue);
    }
    public static String getString(final String key, final String defValue) {
        return mmkv.getString(key,defValue);
    }
    public static <T> T getObject(final String key, final Class<T> classOfT) {
        String json = getString(key,null);
        if(json == null){
            return null;
        }
        return JSON.parseObject(json,classOfT);
    }
    public static Set<String> getStringSet(final String key, final Set<String> defSet){
        return mmkv.getStringSet(key,defSet);
    }

    public static void putLong(final String key, final long value) {
        mmkv.putLong(key,value);
    }

    public static void putInt(final String key, final int value) {
        mmkv.putInt(key,value);
    }

    public static void putDouble(final String key, final double value) {
        mmkv.encode(key,value);
    }

    public static void putFloat(final String key, final float value) {
        mmkv.putFloat(key,value);
    }
    public static void putBoolean(final String key, final boolean value) {
        mmkv.putBoolean(key,value);
    }
    public static void putString(final String key, final String value) {
        mmkv.putString(key,value);
    }
    public static void putObject(final String key, final Object object) {
        putString(key,JSON.toJSONString(object));
    }
    public static void putStringSet(final String key, final Set<String> value) {
        mmkv.putStringSet(key,value);
    }

    public static void remove(final String key){
        mmkv.remove(key);
    }

    public static boolean contains(final String key){
        return mmkv.contains(key);
    }
}
