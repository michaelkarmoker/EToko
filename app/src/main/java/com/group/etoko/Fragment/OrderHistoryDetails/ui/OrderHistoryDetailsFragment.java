
package com.group.etoko.Fragment.OrderHistoryDetails.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.group.etoko.Fragment.OrderHistoryDetails.model.OrderProduct;
import com.group.etoko.Fragment.OrderHistoryDetails.adaptor.AdapterOrderProductList;
import com.group.etoko.Fragment.OrderHistoryDetails.viewmodel.OrderHistoryDetailsViewModel;
import com.group.etoko.databinding.FragmentOrderHistoryDetailsBinding;

import java.util.List;

import static com.group.etoko.util.Constants.Taka_sign;
import static com.group.etoko.util.Constants.numberFormat;


public class OrderHistoryDetailsFragment extends Fragment {

    public FragmentOrderHistoryDetailsBinding binding;
      OrderHistoryDetailsViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(OrderHistoryDetailsViewModel.class);
        uiDataset();
       Observe();


    }


private void Observe() {
     viewModel.getProductlist(getArguments()).observe(getViewLifecycleOwner(), new Observer<List<OrderProduct>>() {
         @Override
         public void onChanged(List<OrderProduct> orderProducts) {
               binding.orderProductsRv.setLayoutManager(new LinearLayoutManager(getContext()));
               binding.orderProductsRv.setHasFixedSize(true);
               AdapterOrderProductList adapter = new AdapterOrderProductList(orderProducts);
               binding.orderProductsRv.setAdapter(adapter);

            Log.i("cpd",""+orderProducts.size());
         }
     });
    }
private void uiDataset(){
    Bundle bundle=getArguments();
    if(bundle!=null){
        String customOrderId=bundle.getString("customOrderId",null);
        String orderPlaceTime=bundle.getString("orderPlaceTime",null);
        String orderstatus=bundle.getString("orderstatus",null);
        String subtotal=bundle.getString("subtotal",null);
        String delivery=bundle.getString("delivery",null);
        String additionalShippingCost=bundle.getString("additionalCost",null);
        String couponDiscount=bundle.getString("couponDiscount",null);
        String referralCost=bundle.getString("referralBlnc",null);
        String tax=bundle.getString("tax",null);
        String total=bundle.getString("total",null);

        binding.orderIdTv.setText("Order: "+customOrderId);
        binding.orderPlacedTimeTv.setText("placed on "+orderPlaceTime);
        binding.orderStatusTv.setText(orderstatus);
        binding.subTotalValueTv.setText(Taka_sign+String.format(numberFormat,Double.parseDouble(subtotal)));
        if(couponDiscount!=null&&Integer.parseInt(couponDiscount)>0){
            binding.couponDiscountLayout.setVisibility(View.VISIBLE);
            binding.couponDiscountValueTv.setText("-"+Taka_sign+String.format(numberFormat,Double.parseDouble(couponDiscount)));
        }
        else{
            binding.couponDiscountLayout.setVisibility(View.GONE);
        }
        if(referralCost!=null&&Integer.parseInt(referralCost)>0){
            binding.referralUsedLayout.setVisibility(View.VISIBLE);
            binding.referralUsedValueTv.setText("-"+Taka_sign+String.format(numberFormat,Double.parseDouble(referralCost)));
        }
        else{
            binding.referralUsedLayout.setVisibility(View.GONE);
        }
        binding.productTaxValueTv.setText(Taka_sign+String.format(numberFormat,Double.parseDouble(tax)));
        binding.deliveryChargeValueTv.setText(Taka_sign+String.format(numberFormat,Double.parseDouble(delivery)));
        binding.additionalShippingCostValueTv.setText(Taka_sign+String.format(numberFormat,Double.parseDouble(additionalShippingCost)));
        binding.totalValueTv.setText(Taka_sign+String.format(numberFormat,Double.parseDouble(total)));
    }
}


}

