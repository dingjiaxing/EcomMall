package com.jackting.module_user.ui.acccount.register;

import com.jackting.common.base.BaseModel;

import javax.inject.Inject;


public class RegisterModel extends BaseModel implements RegisterContract.Model{

    @Inject
    public RegisterModel() {

    }

//    public Observable<HttpRespResult<UserEntity>> login(String name, String pwd){
//        return UserDataRepository.getApi().login(name,pwd);
//    }

}
