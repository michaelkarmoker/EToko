package com.group.etoko.Fragment.LoginSignUp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.LoginSignUp.model.CheckUserRegisterModel;
import com.group.etoko.Fragment.LoginSignUp.model.OTPModel;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;
import com.group.etoko.Fragment.LoginSignUp.repo.LoginSignUPRepository;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.databinding.FragmentLoginSignUpBinding;
import com.group.etoko.util.MySharedPreference;

import static com.group.etoko.util.Constants.OTP_CODE;

public class LoginSignUPViewModel  extends AndroidViewModel implements LoginSignUPViewModel_View{

    private LoginSignUPRepository repository;
    private MutableLiveData<String>isNumberValid=new MutableLiveData<>();
    private MutableLiveData<CheckUserRegisterModel>userRegisterStatus=new MutableLiveData<>();
    private MutableLiveData<Integer> isOTPSent=new MutableLiveData<>();
    private MutableLiveData<UserContainer> registerUser=new MutableLiveData<>();
    private MutableLiveData<UserContainer>loginUser=new MutableLiveData<>();
    private UserDao userDao;

    public LoginSignUPViewModel(@NonNull Application application) {
        super(application);
        repository=new LoginSignUPRepository();
        repository.viewModel=this;
        LocalDatabase database=LocalDatabase.getReferences(application);
        userDao=database.userDao();
    }

    public MutableLiveData<UserContainer> getLoginUser() {
        return loginUser;
    }

    public MutableLiveData<UserContainer> getRegisterUser() {
        return registerUser;
    }

    public MutableLiveData<Integer> getIsOTPSent() {
        return isOTPSent;
    }

    public MutableLiveData<CheckUserRegisterModel> getUserRegisterStatus() {
        return userRegisterStatus;
    }

    public MutableLiveData<String> getIsNumberValid() {
        return isNumberValid;
    }

    public void loginUser(Context context, String code, String number){
        int otp=MySharedPreference.getInstance(context).getInt(OTP_CODE,-1);
        if (code.equals(String.valueOf(otp))){
            repository.loginUser(userDao,number);
        }else {
            isNumberValid.postValue("Invalid OTP");
        }
    }

    public void registerUser(Context  context,String number, String otp, String fullName, String email, String currentDate,String referralCode){
        if (number ==null || number.length() != 11 || !number.matches("^[0-9-]+$")){
            isNumberValid.postValue("Invalid Number");
        }
        else if (otp ==null || otp.length() !=6){
            isNumberValid.postValue("Invalid OTP");
        }
        else if (fullName == null || fullName.equals("")){
            isNumberValid.postValue("Invalid Name");
        }
        else if (currentDate == null){
            isNumberValid.postValue("Error");
        }
        else if (!otp.equals(String.valueOf(MySharedPreference.getInstance(context).getInt(OTP_CODE,-1)))){
            isNumberValid.postValue("Invalid OTP");
        }
        else if (referralCode == null){
            repository.registerUser(userDao,number,fullName,email,currentDate);
        }
        else {
            repository.registerUser(userDao,number,fullName,email,currentDate,referralCode);
        }
    }

    public void sendOTP(String number){
        if (number !=null && number.length()==11&& number.matches("^[0-9-]+$")){
            repository.sendOTP(number);
        }else {
            isNumberValid.postValue("Invalid Number");
        }
    }

    public void setLayout(FragmentLoginSignUpBinding binding, boolean isUserAlreadyRegister){
        if (isUserAlreadyRegister){
            binding.continueButton.setVisibility(View.GONE);
            binding.loginButton.setVisibility(View.VISIBLE);
            binding.OTPLayout.setVisibility(View.VISIBLE);
        }else {
            binding.continueButton.setVisibility(View.GONE);
            binding.registerButton.setVisibility(View.VISIBLE);
            binding.OTPLayout.setVisibility(View.VISIBLE);
            binding.FullNameLayout.setVisibility(View.VISIBLE);
            binding.emailLayout.setVisibility(View.VISIBLE);
            binding.referralLayout.setVisibility(View.VISIBLE);
        }
    }

    public void isUserAlreadyRegister(String number){
        if (number !=null && number.length()==11&& number.matches("^[0-9-]+$")){
            repository.checkUserAlreadyRegister(number);
        }else {
            isNumberValid.postValue("Invalid Number");
        }
    }

    @Override
    public void setUserRegisterStatus(CheckUserRegisterModel registerStatus) {
        if (registerStatus !=null){
            userRegisterStatus.postValue(registerStatus);
        }else {
            isNumberValid.postValue("Error");
        }
    }

    @Override
    public void setOtp(OTPModel otp) {
        if (otp != null){
            if (otp.getStatus()){
                isOTPSent.postValue(otp.getData());
            }else {
                isNumberValid.postValue("OTP Not Sent");
            }
        }else {
            isNumberValid.postValue("OTP Not Sent");
        }
    }

    @Override
    public void registerStatus(UserContainer data) {
        if (data !=null){
            isNumberValid.postValue(data.getMessage());
            registerUser.postValue(data);
        }else {
            isNumberValid.postValue("Error");
        }
    }

    @Override
    public void loginUserStatus(UserContainer message) {
        if (message != null){
            if (message.getStatus()){
                isNumberValid.postValue("Login Success");
            }else {
                isNumberValid.postValue("Login Failed");
            }
            loginUser.postValue(message);
        }else {
            isNumberValid.postValue("Error");
        }
    }
}
