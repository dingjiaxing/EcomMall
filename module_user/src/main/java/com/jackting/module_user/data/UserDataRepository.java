package com.jackting.module_user.data;

import com.jackting.common_http.retrofit.RetrofitHelper;
import com.jackting.module_user.data.http.UserApiService;

import javax.inject.Inject;

public class UserDataRepository {

    private static UserApiService userApiService;

    @Inject
    public UserDataRepository() {
    }

    public static UserApiService getApi(){
        if(userApiService ==null){
            synchronized (UserDataRepository.class){
                userApiService = RetrofitHelper.getRetrofit().create(UserApiService.class);
            }
        }
        return userApiService;
    }

}
