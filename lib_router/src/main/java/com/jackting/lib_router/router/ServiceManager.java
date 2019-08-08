package com.jackting.lib_router.router;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jackting.lib_router.provider.ICommunityProvider;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.IMsgProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.IUserProvider;


/**
 * Created by djx on 2017/4/21.
 */

public class ServiceManager {
    //服务注入看自己的具体实现
    //自动注入

    //可以不使用@Autowired，手动发现服务
    @Autowired
    IGoodsProvider goodsProvider;
    @Autowired
    IMsgProvider msgProvider;
    @Autowired
    IMainProvider mainProvider;
    @Autowired
    IOrderProvider orderProvider;
    @Autowired(name = IUserProvider.USER_MAIN_SERVICE)
    IUserProvider userProvider;
    ICommunityProvider communityProvider;

    public ServiceManager() {
        ARouter.getInstance().inject(this);
    }

    private static final class ServiceManagerHolder {
        private static final ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return ServiceManagerHolder.instance;
    }



    public IUserProvider getUserProvider() {
        return userProvider != null ? userProvider : (userProvider = ((IUserProvider) ModuleRouter.newInstance(IUserProvider.USER_MAIN_SERVICE).navigation()));
    }
    public ICommunityProvider getCommunityProvider() {
        return communityProvider != null ? communityProvider : (communityProvider = ((ICommunityProvider) ModuleRouter.newInstance(ICommunityProvider.COMMUNITY_MAIN_SERVICE).navigation()));
    }

    public IOrderProvider getOrderProvider() {
        return orderProvider != null ? orderProvider : (orderProvider = ((IOrderProvider) ModuleRouter.newInstance(IOrderProvider.ORDER_MAIN_SERVICE).navigation()));
    }

    public IMainProvider getMainProvider() {
        return mainProvider != null ? mainProvider : (mainProvider = (IMainProvider) ModuleRouter.newInstance(IMainProvider.MAIN_MAIN_SERVICE).navigation());
    }

    public IGoodsProvider getGoodsProvider() {
        return goodsProvider != null ? goodsProvider : (goodsProvider = (IGoodsProvider) ModuleRouter.newInstance(IGoodsProvider.GOODS_MAIN_SERVICE).navigation());
    }
    public IMsgProvider getImProvider(){
        return msgProvider!=null?msgProvider:(msgProvider=(IMsgProvider) ModuleRouter.newInstance(IMsgProvider.MSG_MAIN_SERVICE).navigation());
    }

}
