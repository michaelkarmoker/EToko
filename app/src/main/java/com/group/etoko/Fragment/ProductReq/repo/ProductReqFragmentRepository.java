package com.group.etoko.Fragment.ProductReq.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.model.ReqModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductReqFragmentRepository {

    private Api api;

    public ProductReqFragmentRepository(){
            api= RetrofitClient.noInterceptor().create(Api.class);
    }

    public MutableLiveData<ReqModel> getRequestProduct(String productName, String productPrice, String image, String userName, String userNumber, String userId){

        MutableLiveData<ReqModel> isReqSuccess=new MutableLiveData<>();

        api.RequestProduct(BuildConfig.APP_API,productName,productPrice,image,userName,userNumber,userId,"Image Title","JPEG").enqueue(new Callback<ReqModel>() {
            @Override
            public void onResponse(Call<ReqModel> call, @NonNull Response<ReqModel> response) {
                if (response.isSuccessful()){
                    if (response.body()!= null){
                        isReqSuccess.postValue(response.body());
                    }
                    else {
                        isReqSuccess.postValue(null);
                    }
                }else {
                    isReqSuccess.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ReqModel> call, @NonNull Throwable t) {
                Log.e("ProductReqFragment","Failed  :"+t.getMessage());

            }
        });
        return isReqSuccess;
    }

    public LiveData<List<User>> getUser(UserDao userDao){
        return userDao.getUser();
    }
}
