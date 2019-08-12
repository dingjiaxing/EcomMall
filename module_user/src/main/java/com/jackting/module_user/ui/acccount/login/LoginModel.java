package com.jackting.module_user.ui.acccount.login;

import com.jackting.common.base.BaseModel;
import com.jackting.common_http.result.HttpRespResult;
import com.jackting.lib_router.module.user.bean.UserEntity;
import com.jackting.module_user.data.UserDataRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class LoginModel extends BaseModel implements LoginContract.Model{

    @Inject
    public LoginModel() {

    }

    public Observable<HttpRespResult<UserEntity>> login(String name, String pwd){
        return UserDataRepository.getApi().login(name,pwd);
    }

}
