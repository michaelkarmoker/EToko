package com.group.etoko.Fragment.Cart.Repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.Fragment.Cart.model.Coupon;
import com.group.etoko.Fragment.Cart.model.MinimumAmountContainer;
import com.group.etoko.Fragment.Cart.viewmodel.CartFragmentViewModel;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.model.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragmentRepository {
    Api api;
    public CartFragmentViewModel viewModel;
    public CartFragmentRepository (){
        api = RetrofitClient.get().create(Api.class);
    }
    public LiveData<List<CartContainer>> getCartList(CartDao cartDao){
       return cartDao.getCartList();
    }

    public LiveData<List<User>> getLoginUser(UserDao userDao){
        return userDao.getUser();
    }

    public void checkCouponCode(String coupon_code,String order_amount ){
       api.CouponCheck(BuildConfig.APP_API,coupon_code,order_amount).enqueue(new Callback<Coupon>() {
           @Override
           public void onResponse(Call<Coupon> call, Response<Coupon> response) {
               if(response.isSuccessful()&& response.body()!=null){
                   Log.i("cp",response.body().getMessage());
                   viewModel.setCoupon(response.body());
               }
               else {
                   viewModel.setCoupon(null);
               }
           }


           @Override
           public void onFailure(Call<Coupon> call, Throwable t) {
          viewModel.setCoupon(null);
           }
       });
    }

    public void getMinimumAmount(){
        api.getMinimumOrderAmount().enqueue(new Callback<MinimumAmountContainer>() {
            @Override
            public void onResponse(Call<MinimumAmountContainer> call, Response<MinimumAmountContainer> response) {
                if(response.isSuccessful()&&response.body()!=null){
                  viewModel.setMinimumAmount(response.body());
                }
                else {
                    viewModel.setMinimumAmount(null);
                }
            }

            @Override
            public void onFailure(Call<MinimumAmountContainer> call, Throwable t) {
                viewModel.setMinimumAmount(null);
            }
        });
    }


    public void updateCart(CartDao cartDao,ProductDao productDao, Cart cart){
        new UpdateCart(cartDao,productDao).execute(cart);
    }

    public void deleteCart(CartDao cartDao,ProductDao productDao,Cart cart){
        new DeleteCart(cartDao,productDao).execute(cart);
    }



    private class UpdateCart extends AsyncTask<Cart,Void,Void > {

        private CartDao cartDao;
        private ProductDao productDao;

        public UpdateCart(CartDao cartDao, ProductDao productDao){
            this.cartDao=cartDao;
            this.productDao=productDao;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
          productDao.updateQtyById(String.valueOf(carts[0].getProductCount()),String.valueOf(carts[0].getProductId()));
            cartDao.update(carts[0]);

            return null;
        }
    }

    private class DeleteCart extends AsyncTask<Cart,Void,Void > {

        private CartDao cartDao;
        private ProductDao productDao;

        public DeleteCart(CartDao cartDao,ProductDao productDao){
            this.cartDao=cartDao;
            this.productDao=productDao;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            productDao.updateQtyById("0",String.valueOf(carts[0].getProductId()));
            cartDao.deleteCart(carts[0]);
            return null;
        }
    }
}
