package com.group.etoko.Fragment.ProfileEdit.repo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.util.Constants;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.Fragment.ProfileEdit.model.ProfileUpdateResponse;
import com.group.etoko.Fragment.ProfileEdit.viewmodel.ProfileEditViewModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditRepository {
    Api api;
    private UserDao userDao;
    Context context;
    public ProfileEditViewModel viewModel;
    public ProfileEditRepository(Context context){
        this.context=context;
        api = RetrofitClient.noInterceptor().create(Api .class);
        LocalDatabase db=LocalDatabase.getReferences(context);
        userDao=db.userDao();

    }
    public LiveData<List<User>> getuser(){
      return userDao.getUser();

    }
    public void edituser(User user){

        api.updateProfile(user).enqueue(new Callback<ProfileUpdateResponse>() {
            @Override
            public void onResponse(Call<ProfileUpdateResponse> call, Response<ProfileUpdateResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus()) {
                        Log.i("tstt",response.body().getMessage());
                        new UpdateUser(userDao).execute(user);
                        viewModel.setResponse(response.body());
                    }
                    else {
                        Log.i("tstt",response.body().getMessage());
                        viewModel.setResponse(response.body());
                    }
                }
                else {
                    Log.i("tstt","faild"+response.code());
                    ProfileUpdateResponse presponse=new ProfileUpdateResponse();
                    presponse.setStatus(false);
                    presponse.setMessage("Something Wrong. "+Constants.INTERNETERROR);
                    viewModel.setResponse(presponse);

                }

            }

            @Override
            public void onFailure(Call<ProfileUpdateResponse> call, Throwable t) {
                  ProfileUpdateResponse response=new ProfileUpdateResponse();
                  response.setStatus(false);
                  response.setMessage(Constants.INTERNETERROR);
                  viewModel.setResponse(response);
            }
        });


    }




    private class UpdateUser extends AsyncTask<User, Void,Void>{
           UserDao userDao;
           public UpdateUser(UserDao userDao){
               this.userDao=userDao;

           }
        @Override
        protected Void doInBackground(User... users) {
               userDao.update(users[0]);
            return null;
        }
    }


}
