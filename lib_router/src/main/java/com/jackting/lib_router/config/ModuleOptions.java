package com.jackting.lib_router.config;

import android.content.Context;

/**
 * Created by wxmylife on 2017/4/20.
 */

public class ModuleOptions {

    private ModuleBuilder builder;

    private ModuleOptions(ModuleBuilder builder) {
        this.builder = builder;
    }

    public boolean hasModule(String key) {
        return builder.containModule(key);
    }

    public String getModule(String key) {
        return builder.getModuleEntrance(key);
    }

    public Context getContext() {
        return builder.mContext;
    }

    public static class ModuleBuilder {

        private Context mContext;

        private ImmutableMap mModules;


        public ModuleBuilder(Context context) {
            mContext = context;
            mModules=new ImmutableMap();
        }

        public ModuleBuilder addModule(String key, String value){
            mModules.addPath(key,value);
            return this;
        }

        public boolean containModule(String key) {
            return mModules.containsKey(key);
        }

        public String getModuleEntrance(String key) {
            return mModules.get(key);
        }

        public ModuleOptions build() {
            return new ModuleOptions(this);
        }



    }
}
