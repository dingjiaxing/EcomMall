## ECom商城
1. ECom商城是一个基于MVP+dagger+arouter实现的android商城，主要采用了MVP+组件化架构，同时
集成了retrofit+rxJava2、MMKV等，可用于android中级及以上学习此架构、android团队直接基于此架构进行开发。

### 架构介绍
#### 架构图
![arch](/doc/img/arch.jpg)
#### 模块介绍
1. 通用组件(common): 又称业务无关组件，主要包含开源库和自己的基础组件
* 开源库
* 自己的基础组件
2. 业务组件(lib)：业务相关组件
* lib_http:
* lib_push:
* lib_share:
* lib_webview:
* lib_res:
* lib_im:
3. 路由组件(router)
* arouter:
4. 业务模块(module)
* module_main:
* module_goods:
* module_order:
* module_user:
* module_im:
5. APP壳(shell)
* app_shell: app壳层，基于应用安全考虑，通过调用C层的加密方法将业务模块加密

## 参考
1. [知乎 Android 客户端组件化实践](https://zhuanlan.zhihu.com/p/45374964)
2. [浅谈项目架构重构之路——组件化与MVP](https://www.jianshu.com/p/1a1ddecb576d)
3. [架构师的思维，聊一聊APP组件化的那些事儿](https://mp.weixin.qq.com/s/EdfWRj7dF4XtIo_xqP6_sA) [备用](https://juejin.im/post/5d37a0e8e51d4510a37bacc5)
4. [WanAndroid架构源码](https://github.com/senonwx/WanAndroid)
5. [仿京东app](https://github.com/liu-xiao-dong/JD-Test)