package com.jackting.lib_router;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jackting.common.CommonApplication;
import com.jackting.common.base.IApplication;
import com.jackting.lib_router.config.ModuleOptions;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IImProvider;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ModuleManager;

public class RouteApplication implements IApplication {

    Application sApp;

    @Override
    public void onCreate(Application application) {
        sApp = application;
        initRouter();
    }

    void initRouter(){
        if(CommonApplication.isDebug()){
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace(); // 打印日志的时候打印线程堆栈
        }
        ARouter.init(sApp); // 尽可能早，推荐在Application中初始化

        ModuleOptions.ModuleBuilder builder = new ModuleOptions.ModuleBuilder(sApp)

//                .addModule(IMainProvider.MAIN_MAIN_SERVICE, IMainProvider.MAIN_MAIN_SERVICE)
//                .addModule(IImProvider.IM_MAIN_SERVICE, IImProvider.IM_MAIN_SERVICE)
//                .addModule(IGoodsProvider.GOODS_MAIN_SERVICE, IGoodsProvider.GOODS_MAIN_SERVICE)
//                .addModule(IOrderProvider.ORDER_MAIN_SERVICE, IOrderProvider.ORDER_MAIN_SERVICE)
                .addModule(IUserProvider.USER_MAIN_SERVICE, IUserProvider.USER_MAIN_SERVICE);

        ModuleManager.getInstance().init(builder.build());
    }
}
