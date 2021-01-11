package com.group.etoko.Fragment.LoginSignUp.viewmodel;

import com.group.etoko.Fragment.LoginSignUp.model.CheckUserRegisterModel;
import com.group.etoko.Fragment.LoginSignUp.model.OTPModel;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;

public interface LoginSignUPViewModel_View {
    void setUserRegisterStatus(CheckUserRegisterModel registerStatus);
    void setOtp(OTPModel otp);
    void registerStatus(UserContainer message);
    void loginUserStatus(UserContainer message);
}
