package com.group.etoko.Fragment.Profile.repo;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.Fragment.Profile.viewmodel.ProfileViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileRepository {
    Api api;
     public ProfileViewModel viewModel;
    public ProfileRepository() {
        api = RetrofitClient.get().create(Api .class);
    }

    public LiveData<List<User>> getuser(UserDao userDao){
    return userDao.getUser();

  }
public void getUserDataFromServer(String mobileNO){
        api.loginUser(BuildConfig.APP_API,mobileNO).enqueue(new Callback<UserContainer>() {
            @Override
            public void onResponse(Call<UserContainer> call, Response<UserContainer> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    viewModel.setUserdata(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<UserContainer> call, Throwable t) {
                viewModel.setUserdata(null);
            }
        });
}


    public void clearUserData(UserDao userDao) {
        try{
            new DeleteUser(userDao).execute();
        }catch (Exception e){
            Log.e("ProfileRepository",e.getMessage()+"");
        }
    }

    private static class DeleteUser extends AsyncTask<Void,Void,Boolean > {

        private UserDao userDao;

        DeleteUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            userDao.clear();
            return null;
        }
    }
}
