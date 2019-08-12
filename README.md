## ECom商城
1. ECom商城是一个基于MVP+dagger+arouter实现的android商城，开发模式采用了MVP+dagger，组件间路由采用arouter，
网络通信使用了okhttp+retrofit+rxJava2，配置存储采用MMKV，数据传递采用EventBus，图片加载使用glide，
可用于android中级及以上学习此架构、android团队直接参考此架构进行开发。
2. [架构设计文档](https://blog.csdn.net/qq_23081779/article/details/99286345)

### 架构介绍
#### 架构图
![arch](/doc/img/arch.jpg)
#### 模块介绍
1. 通用组件，又称业务无关组件，根据来源分为开源库和基础组件库，开源库是我们在项目中经常会用到的第三方库，比如okhttp、rxJava等，基础组件库是我们自己定义的和业务无关的组件，主要包括BaseMvp、工具类、自定义的Widget、http等
2. 业务组件，和业务相关的组件
* lib_push: 推送组件，负责推送服务
* lib_share: 第三方分享
* lib_im: 即时聊天的服务
* lib_webview: 独立进程webview，用于和h5之间进行交互
3. 业务模块，根据业务的联系紧密程度来拆分
* module_main: 首页模块，包括启动页、欢迎页、首页等；
* module_goods: 商品模块，包括商品分类、商品详情、商品搜索等；
* module_user: 用户模块，包括个人中心、登录等；
* module_order：订单模块，包括订单确认、订单列表等；
* module_msg: 消息模块，包括客服消息等；
4. APP壳，是app的入口模块，包括manifest配置、加载各个模块、打包配置等，基于应用安全考虑，通过调用C层的加密方法将业务模块加密

## 待完成
1. 独立进程webview；
2. im;
3. UI: 购物车、首页；
4. lib_push;

## 参考
1. [知乎 Android 客户端组件化实践](https://zhuanlan.zhihu.com/p/45374964)
2. [浅谈项目架构重构之路——组件化与MVP](https://www.jianshu.com/p/1a1ddecb576d)
3. [架构师的思维，聊一聊APP组件化的那些事儿](https://mp.weixin.qq.com/s/EdfWRj7dF4XtIo_xqP6_sA) [备用](https://juejin.im/post/5d37a0e8e51d4510a37bacc5)
4. [WanAndroid架构源码](https://github.com/senonwx/WanAndroid)
5. [仿京东app](https://github.com/liu-xiao-dong/JD-Test)
6. [MVPArms](https://github.com/JessYanCoding/MVPArms)