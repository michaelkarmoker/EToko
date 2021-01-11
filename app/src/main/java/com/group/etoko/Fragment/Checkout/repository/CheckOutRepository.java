package com.group.etoko.Fragment.Checkout.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.group.etoko.BuildConfig;
import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.Fragment.Checkout.model.Cheakout;
import com.group.etoko.Fragment.Checkout.model.ConfirmOrderResponse;
import com.group.etoko.Fragment.Checkout.model.ProductVat;
import com.group.etoko.Fragment.Checkout.model.ProductVatContainer;
import com.group.etoko.Fragment.Checkout.model.ShippingArea;
import com.group.etoko.Fragment.Checkout.model.ShippingAreaContainer;
import com.group.etoko.Fragment.Checkout.model.ShippingZone;
import com.group.etoko.Fragment.Checkout.model.ShippingZoneContainer;
import com.group.etoko.Fragment.Checkout.viewmodel.CheckoutFragmentViewModel;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;
import com.group.etoko.Database.dao.CartDao;
import com.group.etoko.Database.dao.ProductDao;
import com.group.etoko.Database.dao.UserDao;
import com.group.etoko.Database.db.LocalDatabase;
import com.group.etoko.Network.Api;
import com.group.etoko.Network.RetrofitClient;
import com.group.etoko.Fragment.Checkout.model.DeliveryMethod;
import com.group.etoko.Fragment.Checkout.model.DeliveryMethodContainer;
import com.group.etoko.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutRepository {
    Api api;
    UserDao userDao;
    CartDao cartDao;
    ProductDao productDao;
    MutableLiveData<List<DeliveryMethod>> deliveryMethodsLive = new MutableLiveData<>();
    MutableLiveData<List<ShippingZone>> shippingZonesLive = new MutableLiveData<>();
    MutableLiveData<List<ShippingArea>> shippingAreasLive = new MutableLiveData<>();
    MutableLiveData<List<ProductVat>> productVatLive = new MutableLiveData<>();
    MutableLiveData<ConfirmOrderResponse> confirmOderResponseLive = new MutableLiveData<>();
    public CheckoutFragmentViewModel viewModel;

    public CheckOutRepository(Context context) {
        api = RetrofitClient.get().create(Api.class);
        LocalDatabase db = LocalDatabase.getReferences(context);
        userDao = db.userDao();
        productDao=db.productDao();
        cartDao=db.cartDao();
    }

    public LiveData<List<User>> getuserMobileNo() {

        return userDao.getUser();

    }

    public void getUser(String mobileNo){
        api.loginUser(BuildConfig.APP_API,mobileNo).enqueue(new Callback<UserContainer>() {
            @Override
            public void onResponse(Call<UserContainer> call, Response<UserContainer> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    viewModel.UserInfoStatus(response.body().getStatus());
                    viewModel.SetUserInfo(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<UserContainer> call, Throwable t) {

            }
        });
    }



    public LiveData<List<CartContainer>> getCartlist() {
        return cartDao.getCartList();
    }
    public MutableLiveData<List<DeliveryMethod>> getDeliveryMethod() {
        api.getDeliveryMethods().enqueue(new Callback<DeliveryMethodContainer>() {
            @Override
            public void onResponse(Call<DeliveryMethodContainer> call, Response<DeliveryMethodContainer> response) {

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        deliveryMethodsLive.postValue(response.body().getData());
                        viewModel.DeliveryMethodStatus(true);
                    }
                }

            }

            @Override
            public void onFailure(Call<DeliveryMethodContainer> call, Throwable t) {
                Log.i("delivery ", "failed");
            }
        });
        return deliveryMethodsLive;
    }

    public MutableLiveData<List<ShippingZone>> getShippingZone() {

        api.getShippingZones().enqueue(new Callback<ShippingZoneContainer>() {
            @Override
            public void onResponse(Call<ShippingZoneContainer> call, Response<ShippingZoneContainer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        response.body().getData().add(0, new ShippingZone("Shipping Select Delivery Zone"));
                        shippingZonesLive.postValue(response.body().getData());
                        viewModel.ShippingZoneStatus(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ShippingZoneContainer> call, Throwable t) {

            }
        });
        return shippingZonesLive;
    }

    public MutableLiveData<List<ShippingArea>> getShippinArea() {

        api.getShippingAreas().enqueue(new Callback<ShippingAreaContainer>() {
            @Override
            public void onResponse(Call<ShippingAreaContainer> call, Response<ShippingAreaContainer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        shippingAreasLive.postValue(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ShippingAreaContainer> call, Throwable t) {

            }
        });
        return shippingAreasLive;
    }




    public MutableLiveData<List<ProductVat>> getProductVat() {
        api.getProductVat().enqueue(new Callback<ProductVatContainer>() {
            @Override
            public void onResponse(Call<ProductVatContainer> call, Response<ProductVatContainer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus()) {
                        productVatLive.postValue(response.body().getData());
                        viewModel.ProductVatStatus(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductVatContainer> call, Throwable t) {

            }

        });
        return productVatLive;
    }
public void sendConfirmOder(Cheakout cheakout,User user){

        api.sendConfirmOrder(cheakout).enqueue(new Callback<ConfirmOrderResponse>() {
            @Override
            public void onResponse(Call<ConfirmOrderResponse> call, Response<ConfirmOrderResponse> response) {
                if(response.isSuccessful()) {
                  viewModel.SetConfirmDelivery(response.body());
                  new UpdateUser(userDao).execute(user);
                }
                else {

                    viewModel.SetConfirmDelivery(null);
                }
            }
            @Override
            public void onFailure(Call<ConfirmOrderResponse> call, Throwable t) {

               viewModel.SetConfirmDelivery(null);
            }

        });
}
    public void clearCartData(Cheakout cheakout){
        new ClearCart(cartDao).execute();
        List<Product> products=cheakout.getProduct_data();
        for(Product product:products){
            new ClearProductQty(productDao).execute(product);
        }

    }


    private class UpdateUser extends AsyncTask<User, Void,Void> {
        UserDao userDao;
        public UpdateUser(UserDao userDao){
            this.userDao=userDao;

        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private class ClearCart extends AsyncTask<Void, Void,Void> {
        CartDao cartDao;
        public ClearCart(CartDao cartDao){
            this.cartDao=cartDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            cartDao.clear();
            return null;
        }
    }
    private class ClearProductQty extends AsyncTask<Product,Void,Void >{

        private ProductDao productDao;

        public ClearProductQty(ProductDao productDao){
            this.productDao=productDao;
        }


        @Override
        protected Void doInBackground(Product... product) {
            productDao.updateQtyById("0",product[0].getProductId());
            return null;
        }
    }
}
