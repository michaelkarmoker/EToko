package com.group.etoko.Fragment.OrderHistoryDetails.viewmodel;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.OrderHistoryDetails.model.OrderProduct;
import com.group.etoko.Fragment.OrderHistoryDetails.repository.OrderHistoryDetailsRepository;

import java.util.List;

public class OrderHistoryDetailsViewModel extends AndroidViewModel implements OrderHistoryDetailsViewModel_View {
     OrderHistoryDetailsRepository repository;
     MutableLiveData<List<OrderProduct>> productlistLive=new MutableLiveData<>();
    public OrderHistoryDetailsViewModel(@NonNull Application application) {
        super(application);
        repository=new OrderHistoryDetailsRepository();
        repository.viewModel=this;

    }

public MutableLiveData<List<OrderProduct>> getProductlist(Bundle bundle){
    Log.i("cpd","orderId");
    if(bundle!=null){
        String orderId=bundle.getString("orderId",null);
        Log.i("cpd",orderId);
        if(orderId!=null) {
            Log.i("cpd",orderId);
            repository.getOrderProduct(orderId);

        }
    }
        return productlistLive;
}


    @Override
    public void setProductList(List<OrderProduct> productListlist) {
        productlistLive.setValue(productListlist);
    }
}
