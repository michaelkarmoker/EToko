package com.group.etoko.Fragment.Cart.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.group.etoko.Fragment.Cart.Repository.CartFragmentRepository;
import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.Fragment.Cart.model.Coupon;
import com.group.etoko.Fragment.Cart.model.MinimumAmountContainer;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.R;
import com.group.etoko.model.Cart;
import com.group.etoko.util.MySharedPreference;

import java.util.List;

import static com.group.etoko.util.Constants.CART_ITEM_POSITION;
import static com.group.etoko.util.Constants.COUPONAMOUNT;
import static com.group.etoko.util.Constants.IS_CART_ITEM_REMOVE;

public class CartFragmentViewModel extends AndroidViewModel implements CartFragmentViewModelView{

    private CartFragmentRepository repository;
    private CartDao cartDao;
    ProductDao productDao;
    private UserDao userDao;
    public MutableLiveData<Coupon> couponData=new MutableLiveData<>();
    public MutableLiveData<MinimumAmountContainer> minimumAmountContainer=new MutableLiveData<>();
    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        LocalDatabase database=LocalDatabase.getReferences(application);
        repository=new CartFragmentRepository();
        repository.viewModel=this;
        cartDao=database.cartDao();
        productDao=database.productDao();
        userDao=database.userDao();
    }

    public void navigateForward(View view, LifecycleOwner viewLifecycleOwner,String couponAmount){

        userLogin().observe(viewLifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                    if (users != null && users.size() != 0) {
                        Bundle bundle=new Bundle();
                        bundle.putString(COUPONAMOUNT,couponAmount);
                        Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_checkoutFragment,bundle);
                    } else {
                        Navigation.findNavController(view).navigate(R.id.action_global_loginSignUpFragment);
                    }


            }
        });
    }

    public void updateCart(Cart cart){
        repository.updateCart(cartDao,productDao,cart);
    }

    public void getMinimumOrderAmount() {
            repository.getMinimumAmount();


    }
    public void notifyCartItemChange(Context context,int position ) {
        MySharedPreference.getInstance(context)
                .edit()
                .putInt(CART_ITEM_POSITION,position)
                .apply();
    }

    public int getNotifyCartItemChange(Context context){
        return MySharedPreference.getInstance(context)
                .getInt(CART_ITEM_POSITION,-1);
    }
    public int getNotifyCartItemRemove(Context context){
        return MySharedPreference.getInstance(context)
                .getInt(IS_CART_ITEM_REMOVE,-1);
    }
    public void notifyCartItemRemove(Context context,int position){
        MySharedPreference.getInstance(context)
                .edit()
                .putInt(IS_CART_ITEM_REMOVE,position)
                .apply();
    }
    public void deleteCart(Cart cart){
        repository.deleteCart(cartDao,productDao,cart);
    }

    public LiveData<List<CartContainer>> getCartList() {
        return repository.getCartList(cartDao);
    }

    private LiveData<List<User>> userLogin(){
        return repository.getLoginUser(userDao);
    }

    public MutableLiveData<Coupon> getCouponData(){
        return couponData;
    }

    public MutableLiveData<MinimumAmountContainer> getMinimumAmountResponder(){
        return minimumAmountContainer;
    }
    public void checkCouponCode(String coupon_code, String order_amount){

        repository.checkCouponCode(coupon_code,order_amount);
    }

    @Override
    public void setCoupon(Coupon coupon) {
        couponData.postValue(coupon);
    }

    @Override
    public void setMinimumAmount(MinimumAmountContainer minimumAmount) {
        minimumAmountContainer.postValue(minimumAmount);
    }
}
