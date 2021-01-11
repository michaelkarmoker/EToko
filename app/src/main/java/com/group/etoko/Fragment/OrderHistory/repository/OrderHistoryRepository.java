package com.group.etoko.Fragment.OrderHistory.repository;

import com.group.etoko.BuildConfig;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.Fragment.OrderHistory.model.OrderContainer;
import com.group.etoko.Fragment.OrderHistory.viewmodel.OrderHistoryViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryRepository {
    Api api;
    public OrderHistoryViewModel viewModel;
    public OrderHistoryRepository(){
    api = RetrofitClient.get().create(Api .class);
    }

    public void getOrderlist(String customerId){

        api.getCustomerOrderHistory(BuildConfig.APP_API,customerId).enqueue(new Callback<OrderContainer>() {
            @Override
            public void onResponse(Call<OrderContainer> call, Response<OrderContainer> response) {
              if(response.isSuccessful()){

                  viewModel.setOrderlist(response.body().getData());
              }
              else{
                  viewModel.setOrderlist(null);
              }
            }

            @Override
            public void onFailure(Call<OrderContainer> call, Throwable t) {
                viewModel.setOrderlist(null);
            }
        });
    }
}
