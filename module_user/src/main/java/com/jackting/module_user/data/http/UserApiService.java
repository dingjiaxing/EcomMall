package com.jackting.module_user.data.http;

import com.jackting.lib_router.module.user.bean.UserEntity;
import com.lib.http.result.HttpRespResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 网络请求接口
 * 测试api文档: https://www.wanandroid.com/blog/show/2
 */
public interface UserApiService {

    String BASE_URL="https://www.wanandroid.com/";

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<HttpRespResult<UserEntity>> login(@Field("username")String username, @Field("password")String password);

    //登出
    @GET("user/logout/json")
    Observable<HttpRespResult> logout();
    //注册
    @POST("user/register")
    @FormUrlEncoded
    Observable<HttpRespResult<UserEntity>> register(@Field("username")String username,@Field("password")String password,@Field("repassword")String repassword);



}
