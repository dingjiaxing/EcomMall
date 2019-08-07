package com.jackting.lib_router.router;

import android.app.Activity;
import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jackting.lib_router.config.ModuleBundle;

/**
 * Created by wxmylife on 2017/4/21.
 */

public class ModuleRouter {

    private Postcard postcard;


    public ModuleRouter(Postcard postcard) {
        this.postcard = postcard;
    }

    public static ModuleRouter newInstance(String path){
        return new ModuleRouter(ARouter.getInstance().build(path));
    }

    private boolean checkPostcard() {
        if (postcard == null)
            throw new IllegalArgumentException("ModuleRouter.class  postcard can not be null");
        return true;
    }

    public ModuleRouter withBundle(ModuleBundle bundle){
        if (bundle==null) return this;
        checkPostcard();
        postcard.with(bundle.build());
        return this;
    }

    public ModuleRouter addFlag(int flag){
        checkPostcard();
        postcard.withFlags(flag);
        return this;
    }

    public Object navigation() {
        return navigation(null);
    }

    public Object navigation(Context context) {
        return navigation(context, null);
    }

    public void navigation(Activity activity, int requestCode) {
        navigation(activity, requestCode, null);
    }

    public Object navigation(Context context, NavigationCallback callback) {
        checkPostcard();
        return postcard.navigation(context, callback);
    }


    public void navigation(Activity activity, int requestCode, NavigationCallback callback) {
        postcard.navigation(activity, requestCode, callback);
    }
}
