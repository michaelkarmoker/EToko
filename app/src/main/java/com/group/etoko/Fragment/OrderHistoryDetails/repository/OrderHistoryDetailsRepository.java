package com.group.etoko.Fragment.OrderHistoryDetails.repository;

import android.util.Log;

import com.group.etoko.BuildConfig;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.Fragment.OrderHistoryDetails.model.OrderProductContainer;
import com.group.etoko.Fragment.OrderHistoryDetails.viewmodel.OrderHistoryDetailsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryDetailsRepository {
    Api api;
    public OrderHistoryDetailsViewModel viewModel;
    public OrderHistoryDetailsRepository(){
    api = RetrofitClient.get().create(Api .class);
    }

    public void getOrderProduct(String orderId){
        api.getCustomerOrderHistoryDetails(BuildConfig.APP_API,orderId).enqueue(new Callback<OrderProductContainer>() {
            @Override
            public void onResponse(Call<OrderProductContainer> call, Response<OrderProductContainer> response) {
                if(response.isSuccessful()){
                    viewModel.setProductList(response.body().getData());
                    Log.i("cpd",response.body().getMessage());
                }
                else{
                    viewModel.setProductList(null);
                    Log.i("cpd",response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderProductContainer> call, Throwable t) {
                viewModel.setProductList(null);
            }
        });
    }
}
