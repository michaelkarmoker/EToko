package com.group.etoko.Fragment.productDetails.repo;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.productDetails.Model.ModelShareData;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.model.Cart;
import com.group.etoko.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsFragmentRepository {

    public LiveData<Product> getProduct(ProductDao productDao, String id){
        return productDao.getProduct(id);
    }

    public LiveData<List<Product>> getRelatedProduct(ProductDao productDao, String id){
        return productDao.getProductByCategoryId(id);
    }

    public LiveData<List<User>> getLoginUser(UserDao userDao){
        return userDao.getUser();
    }

    public MutableLiveData<ModelShareData> getProductUrlById(String id){
        MutableLiveData<ModelShareData> shareDataMutableLiveData=new MutableLiveData<>();
        Api api=RetrofitClient.noInterceptor().create(Api.class);
        api.getProductURL(BuildConfig.APP_API,id).enqueue(new Callback<ModelShareData>() {
            @Override
            public void onResponse(@NonNull Call<ModelShareData> call, @NonNull Response<ModelShareData> response) {
                if (response.body() != null){
                    shareDataMutableLiveData.postValue(response.body());
                }else {
                    shareDataMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ModelShareData> call, Throwable t) {
                shareDataMutableLiveData.postValue(null);
            }
        });

        return shareDataMutableLiveData;
    }

    public void addCartList(CartDao cartDao,ProductDao productDao, Cart cart){
        new InsertToCart(cartDao,productDao).execute(cart);
    }


    private class InsertToCart extends AsyncTask<Cart,Void,Void > {

        private CartDao cartDao;
         private ProductDao productDao;
        public InsertToCart(CartDao cartDao,ProductDao productDao){
            this.productDao=productDao;
            this.cartDao=cartDao;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            productDao.updateQtyById(String.valueOf(carts[0].getProductCount()),String.valueOf(carts[0].getProductId()));
            cartDao.insert(carts[0]);
            return null;
        }
    }
}
