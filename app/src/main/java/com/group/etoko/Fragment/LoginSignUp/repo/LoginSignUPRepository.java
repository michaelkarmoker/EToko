package com.group.etoko.Fragment.LoginSignUp.repo;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.LoginSignUp.model.CheckUserRegisterModel;
import com.group.etoko.Fragment.LoginSignUp.model.OTPModel;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;
import com.group.etoko.Fragment.LoginSignUp.viewmodel.LoginSignUPViewModel_View;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSignUPRepository {

    private Api api;
    public LoginSignUPViewModel_View viewModel;

    public LoginSignUPRepository(){
        api= RetrofitClient.noInterceptor().create(Api.class);
    }

    public void checkUserAlreadyRegister(String number){
        api.checkUserRegistration(BuildConfig.APP_API,number).enqueue(new Callback<CheckUserRegisterModel>() {
            @Override
            public void onResponse(@NonNull Call<CheckUserRegisterModel> call, @NonNull Response<CheckUserRegisterModel> response) {
                if (response.body() != null){
                    viewModel.setUserRegisterStatus(response.body());
                }else {
                    viewModel.setUserRegisterStatus(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CheckUserRegisterModel> call, @NonNull Throwable t) {
                viewModel.setUserRegisterStatus(null);
            }
        });
    }

    public void sendOTP(String number){

        api.sendOTP(BuildConfig.APP_API,number).enqueue(new Callback<OTPModel>() {
            @Override
            public void onResponse(@NonNull Call<OTPModel> call, @NonNull Response<OTPModel> response) {
                if (response.body() != null) {
                    viewModel.setOtp(response.body());
                }else {
                    viewModel.setOtp(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<OTPModel> call, @NonNull Throwable t) {
                viewModel.setOtp(null);
            }
        });

    }

    public void registerUser(UserDao userDao, String number, String fullName, String email, String currentDate){
        api.registerUser(BuildConfig.APP_API,fullName,number,email,currentDate).enqueue(new Callback<UserContainer>() {
            @Override
            public void onResponse(@NonNull Call<UserContainer> call, @NonNull Response<UserContainer> response) {
                if (response.body() != null){
                    viewModel.registerStatus(response.body());
                    new InsertUser(userDao).execute(response.body().getData().get(0));
                }else {
                    viewModel.registerStatus(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserContainer> call, @NonNull Throwable t) {
                viewModel.registerStatus(null);
            }
        });
    }


    public void registerUser(UserDao userDao, String number, String fullName, String email, String currentDate,String referralCode){
        api.registerUser(BuildConfig.APP_API,fullName,number,email,currentDate,referralCode).enqueue(new Callback<UserContainer>() {
            @Override
            public void onResponse(@NonNull Call<UserContainer> call, @NonNull Response<UserContainer> response) {
                if (response.body() != null){
                    viewModel.registerStatus(response.body());
                    new InsertUser(userDao).execute(response.body().getData().get(0));
                }else {
                    viewModel.registerStatus(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserContainer> call, @NonNull Throwable t) {
                viewModel.registerStatus(null);
            }
        });
    }


    public void loginUser(UserDao userDao, String number){

        api.loginUser(BuildConfig.APP_API,number).enqueue(new Callback<UserContainer>() {
            @Override
            public void onResponse(@NonNull Call<UserContainer> call, @NonNull Response<UserContainer> response) {
                if (response.body() !=null){
                    viewModel.loginUserStatus(response.body());
                    new InsertUser(userDao).execute(response.body().getData().get(0));
                }else {
                    viewModel.loginUserStatus(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserContainer> call, @NonNull Throwable t) {
                viewModel.loginUserStatus(null);
            }
        });
    }

    private class InsertUser extends AsyncTask<User,Void,Void > {

        private UserDao userDao;

        public InsertUser(UserDao userDao){
            this.userDao=userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.clear();
            userDao.insert(users[0]);
            return null;
        }
    }
}
