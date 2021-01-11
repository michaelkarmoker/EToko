package com.group.etoko.Activity.splashactivity.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.group.etoko.Activity.splashactivity.viewmodel.SplashViewModel_View;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.model.Cart;
import com.group.etoko.model.Product;
import com.group.etoko.model.ProductContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityRepository {

    private Api api;
    public SplashViewModel_View viewModel;
    Context context;


    public SplashActivityRepository(Context context){
        this.context=context;
        api= RetrofitClient.get().create(Api.class);
    }

    public void getProduct(ProductDao productDao){
        api.getProduct().enqueue(new Callback<ProductContainer>() {
            @Override
            public void onResponse(@NonNull Call<ProductContainer> call, @NonNull Response<ProductContainer> response) {
                if (response.isSuccessful() && response.body() != null){
                    new InsertProductList(productDao).execute(response.body().getData());
                    viewModel.productStatus(response.body().getStatus());
                    Log.e("DBBBBBBBBBB Call 4","getProduct  "+"Success");
                }else {
                    viewModel.productStatus(false);
                    Log.e("DBBBBBBBBBB  Call 4","getProduct  "+"Failed");
                }
            }

            @Override
            public void onFailure(Call<ProductContainer> call, Throwable t) {
                viewModel.productStatus(false);
            }
        });
    }
    public  void setProductQtyByCart(CartDao cartDao,ProductDao productDao){

        List<Cart> cart=new ArrayList<>();

        try {
            cart=new GetCartContainer(cartDao).execute().get();
            if(cart!=null) {
                for (int i = 0; i < cart.size(); i++) {
                    new InsertProductQty(productDao).execute(cart.get(i));
                }
                viewModel.productQtystatus(true);

            }
            viewModel.productQtystatus(true);
        } catch (ExecutionException e) {
            viewModel.productQtystatus(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    //===================For Background Work================

    private class InsertProductList extends AsyncTask< List<Product>,Void,Void >{

        private ProductDao productDao;

        public InsertProductList(ProductDao productDao){
            this.productDao=productDao;
        }

        @Override
        protected Void doInBackground(List<Product>... lists) {
            productDao.clear();
            productDao.insert(lists[0]);
            return null;
        }
    }


    private class InsertProductQty extends AsyncTask< Cart,Void,Void >{

        private ProductDao productDao;

        public InsertProductQty(ProductDao productDao){
            this.productDao=productDao;
        }


        @Override
        protected Void doInBackground(Cart... cart) {
            productDao.updateQtyById(String.valueOf(cart[0].getProductCount()),cart[0].getProductId());
            return null;
        }
    }
    private class GetCartContainer extends AsyncTask< Void,Void,List<Cart> >{

        private CartDao cartDao;

        public GetCartContainer(CartDao cartDao){
            this.cartDao=cartDao;
        }


        @Override
        protected List<Cart> doInBackground(Void... voids) {
            return cartDao.getCartListforQty();
        }
    }

}