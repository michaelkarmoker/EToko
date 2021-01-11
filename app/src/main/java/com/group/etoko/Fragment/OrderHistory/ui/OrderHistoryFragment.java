package com.group.etoko.Fragment.OrderHistory.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group.etoko.Fragment.OrderHistory.adaptor.AdapterOrderHistory;
import com.group.etoko.Fragment.OrderHistory.model.Order;
import com.group.etoko.Fragment.OrderHistory.viewmodel.OrderHistoryViewModel;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentOrderHistoryBinding;

import java.util.List;


public class OrderHistoryFragment extends Fragment {

    public FragmentOrderHistoryBinding binding;
      OrderHistoryViewModel viewModel;
      ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(OrderHistoryViewModel.class);
        progressDialog =new ProgressDialog(getContext(), R.style.CustomAlertDialogStyle);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        viewModel.getOrderlist(getArguments());
        Observe();

    }

    private void Observe() {
       viewModel.getOrderlistfromServer().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
           @Override
           public void onChanged(List<Order> orders) {
               if(orders!=null&&orders.size()>0){
               binding.orderListRv.setLayoutManager(new LinearLayoutManager(getContext()));
               binding.orderListRv.setHasFixedSize(true);
               AdapterOrderHistory adapter = new AdapterOrderHistory(orders);
               binding.orderListRv.setAdapter(adapter);
                   progressDialog.dismiss();
               }
               progressDialog.dismiss();
           }
       });
    }


}
