package com.group.etoko.Fragment.Cart.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.group.etoko.Fragment.Cart.adapter.CartAdapter;
import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.Fragment.Cart.model.Coupon;
import com.group.etoko.Fragment.Cart.model.MinimumAmountContainer;
import com.group.etoko.Fragment.Cart.viewmodel.CartFragmentViewModel;
import com.group.etoko.R;
import com.group.etoko.databinding.FragmentCartBinding;
import com.group.etoko.util.AlertDialogBox;
import com.group.etoko.util.InternetConnectivityInfo;
import com.group.etoko.util.SnackbarBuilder;

import java.util.List;

import static com.group.etoko.util.Constants.Taka_sign;
import static com.group.etoko.util.Constants.numberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartFragmentViewModel viewModel;
    private CartAdapter cartAdapter;
    ProgressDialog progressDialog;
    private static double total,subTotal,discount,couponAmount;
    private int isAdapterAlreadySet=0;
    LinearLayoutManager manager;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCartBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel= new ViewModelProvider(this).get(CartFragmentViewModel.class);
        observer(savedInstanceState);

        manager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);



        progressDialog =new ProgressDialog(getContext(), R.style.CustomAlertDialogStyle);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        binding.applyTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(binding.cuponEditTxt.getText().toString())) {
                    viewModel.checkCouponCode(binding.cuponEditTxt.getText().toString(),String.valueOf(subTotal));
                    progressDialog.show();
                }
                else{
                    setSnackbarText("Please Enter Your Coupon Code!!");
                }
            }
        });

        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InternetConnectivityInfo internetConnectivityInfo = new InternetConnectivityInfo(view.getContext());
                if (internetConnectivityInfo.isConnected()) {
                    progressDialog.show();
                    viewModel.getMinimumOrderAmount();
                }
                else{
                    SnackbarBuilder snackbarBuilder = new SnackbarBuilder.Builder(getContext())
                            .setLayoutId(view.getRootView())
                            .setMessage("Check your Internet Connection")
                            .build();
                    snackbarBuilder.show();
                    ;
                }

            }
        });
    }

    private void observer(Bundle savedInstanceState) {

        viewModel.getCartList().observe(getViewLifecycleOwner(), new Observer<List<CartContainer>>() {
            @Override
            public void onChanged(List<CartContainer> cartContainers) {

                if(cartContainers!=null && cartContainers.size()>0) {

                    total = 0.0;
                    subTotal = 0.0;
                    discount = 0.0;
                    couponAmount=0.0;
                    for (CartContainer container : cartContainers) {

                        subTotal = subTotal + (Double.parseDouble(container.product.getProductOriginalPrice()) * container.cart.getProductCount());
                        if (container.product.getProductDiscountAmount() != null)
                            discount = discount + (Double.parseDouble(container.product.getProductDiscountAmount()) * container.cart.getProductCount());
                    }
                    total = subTotal - discount;

                    binding.cartSubTotalTxtValue.setText( Taka_sign+String.format(numberFormat,subTotal));
                    binding.cartDiscountValueTxt.setText("-" +  Taka_sign+String.format(numberFormat,discount));
                    binding.cartTotalValueTxt.setText(Taka_sign+String.format(numberFormat,total));
                    binding.couponOfferValueTxt.setVisibility(View.GONE);
                    binding.couponOfferTxt.setVisibility(View.GONE);


                    if (isAdapterAlreadySet==0){
                        isAdapterAlreadySet++;
                        binding.cartProductRecyclerView.setLayoutManager(manager);
                        binding.cartProductRecyclerView.setHasFixedSize(true);
                        cartAdapter = new CartAdapter(cartContainers, viewModel);
                        binding.cartProductRecyclerView.setAdapter(cartAdapter);

                    }else {
                        if (viewModel.getNotifyCartItemRemove(getContext())!=-1){
                            cartAdapter.notifyDataSetChanged();
                             viewModel.notifyCartItemRemove(getContext(),-1);
                        }
                        if (viewModel.getNotifyCartItemChange(getContext()) != -1){
                            cartAdapter.notifyItemChanged(viewModel.getNotifyCartItemChange(getContext()));
                            viewModel.notifyCartItemChange(getContext(),-1);
                        }
                    }




                }
                else{
                    binding.cartContainer.setVisibility(View.GONE);
                    Glide.with(requireActivity())
                            .load(R.drawable.product_not_found)
                            .placeholder(R.drawable.product_not_found)
                            .into(binding.productNotFoundImage);
                    binding.notFoundViewContianer.setVisibility(View.VISIBLE);
                }
            }
        });


        viewModel.getCouponData().observe(getViewLifecycleOwner(), new Observer<Coupon>() {
            @Override
            public void onChanged(Coupon coupon) {
                progressDialog.dismiss();
                if (coupon!=null) {
                    if (coupon.getStatus()) {
                        AlertDialogBox.Box(getContext(),"Congratulation!!","Get "+Taka_sign+coupon.getData()+" Coupon Offer");
                        couponAmount=Double.parseDouble(coupon.getData().toString());
                        binding.couponOfferValueTxt.setVisibility(View.VISIBLE);
                        binding.couponOfferTxt.setVisibility(View.VISIBLE);
                        binding.couponOfferValueTxt.setText(":-"+Taka_sign+String.format(numberFormat,couponAmount));
                        binding.cartTotalValueTxt.setText(": " + Taka_sign+String.format(numberFormat,total-couponAmount));
                        viewModel.couponData.postValue(null);
                    }
                    else if(coupon.getFailedCode()==2){
                        AlertDialogBox.Box(getContext(),"Coupon Code","Buy "+Taka_sign+coupon.getData()+" more amount and get Coupon offer");
                        binding.couponOfferValueTxt.setVisibility(View.GONE);
                        binding.couponOfferTxt.setVisibility(View.GONE);
                        viewModel.couponData.postValue(null);
                    }
                    else{
                        AlertDialogBox.Box(getContext(),"Coupon Code",coupon.getMessage());
                        binding.couponOfferValueTxt.setVisibility(View.GONE);
                        binding.couponOfferTxt.setVisibility(View.GONE);
                        viewModel.couponData.postValue(null);
                    }
                }
            }
        });
        viewModel.getMinimumAmountResponder().observe(getViewLifecycleOwner(), new Observer<MinimumAmountContainer>() {
            @Override
            public void onChanged(MinimumAmountContainer minimumAmountContainer) {
                if(minimumAmountContainer!=null){
                    if(minimumAmountContainer.getStatus()&&minimumAmountContainer.getData()!=null){
                        if(minimumAmountContainer.getData()<=total){
                            progressDialog.dismiss();
                            viewModel.minimumAmountContainer.postValue(null);
                            navigateForwordFromCart(getView());
                        }
                        else{
                            progressDialog.dismiss();
                            Double amount=minimumAmountContainer.getData()-total;
                            AlertDialogBox.Box(getContext(),"Minimum Order Amount is "+Taka_sign+minimumAmountContainer.getData(),"Buy More "+ Taka_sign+String.format(numberFormat,amount)  );
                        }
                    }
                   else{
                        progressDialog.dismiss();
                       viewModel.minimumAmountContainer.postValue(null);
                        navigateForwordFromCart(getView());
                    }

                }
                else{
                    progressDialog.dismiss();

                }
            }
        });
    }

    private  void navigateForwordFromCart(View view){
        viewModel.navigateForward(view,getViewLifecycleOwner(),String.valueOf(couponAmount));
    }

    private void setSnackbarText(String text) {
        SnackbarBuilder snackbarBuilder = new SnackbarBuilder.Builder(getContext())
                .setLayoutId(binding.getRoot())
                .setMessage(text)
                .build();
        snackbarBuilder.show();
        ;
    }

    @Override
    public void onStop() {
        super.onStop();
        isAdapterAlreadySet=0;
    }
}