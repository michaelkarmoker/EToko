package com.group.etoko.Fragment.OrderHistory.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.Fragment.OrderHistory.model.Order;
import com.group.etoko.Fragment.OrderHistory.repository.OrderHistoryRepository;

import java.util.List;

import static com.group.etoko.util.Constants.CUSTOMERID;

public class OrderHistoryViewModel extends AndroidViewModel implements OrderHistoryViewModel_View {
     OrderHistoryRepository repository;
     MutableLiveData<List<Order>> orderlist=new MutableLiveData<>();
    // MutableLiveData<List<OrderProduct>> orderProduct=new MutableLiveData<>();
    public OrderHistoryViewModel(@NonNull Application application) {
        super(application);
        repository=new OrderHistoryRepository();
        repository.viewModel=this;

    }
public MutableLiveData<List<Order>> getOrderlistfromServer(){
    return orderlist;
}
    public void getOrderlist(Bundle bundle){
        String customerId=bundle.getString(CUSTOMERID,null);
        if(customerId!=null){

            repository.getOrderlist(customerId);

        }
        else {
            orderlist.postValue(null);
        }

    }



    @Override
    public void setOrderlist(List<Order> orders) {
        orderlist.postValue(orders);
    }


}
