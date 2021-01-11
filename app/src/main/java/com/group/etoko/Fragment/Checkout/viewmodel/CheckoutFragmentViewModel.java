package com.group.etoko.Fragment.Checkout.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.IntentSender;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.group.etoko.Fragment.LoginSignUp.model.User;


import java.util.List;

import com.group.etoko.Fragment.Cart.model.CartContainer;
import com.group.etoko.Fragment.Checkout.model.Cheakout;
import com.group.etoko.Fragment.Checkout.model.ConfirmOrderResponse;
import com.group.etoko.Fragment.Checkout.model.ProductVat;
import com.group.etoko.Fragment.Checkout.model.ShippingArea;
import com.group.etoko.Fragment.Checkout.model.ShippingZone;
import com.group.etoko.Fragment.Checkout.repository.CheckOutRepository;
import com.group.etoko.Fragment.Checkout.model.DeliveryMethod;
import com.group.etoko.R;
import com.group.etoko.model.NetworkStatusModelForCheckout;
import com.group.etoko.util.InternetConnectivityInfo;

import java.util.ArrayList;


public class CheckoutFragmentViewModel extends AndroidViewModel implements CheckoutFragmentViewModel_view {
    private InternetConnectivityInfo internetConnectivityInfo;
    private MutableLiveData<Boolean> isInternetOn = new MutableLiveData<>();
    private LiveData<List<User>> userMobileNo = new MutableLiveData<>();
    private MutableLiveData<List<User>> userData = new MutableLiveData<>();
    private LiveData<List<CartContainer>> cartContainer = new MutableLiveData<>();
    private MutableLiveData<List<DeliveryMethod>> deliveryMethod = new MutableLiveData<>();
    private MutableLiveData<List<ShippingZone>> shippingZones = new MutableLiveData<>();
    private MutableLiveData<List<ShippingArea>> shippingAreas = new MutableLiveData<>();
    private MutableLiveData<List<ShippingArea>> shippingAreasByZone = new MutableLiveData<>();
    private MutableLiveData<List<ProductVat>> productVatLive = new MutableLiveData<>();
    private MutableLiveData<ConfirmOrderResponse> confirmOderResponseLive = new MutableLiveData<>();
    private CheckOutRepository repository;
    private MutableLiveData<NetworkStatusModelForCheckout> networkStatusModelForCheckoutLive = new MutableLiveData<>();
    private NetworkStatusModelForCheckout networkStatusModelForCheckout;
    private FusedLocationProviderClient fusedLocationClient = null;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private boolean isLocationEnable = false;

    public CheckoutFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new CheckOutRepository(application);
        repository.viewModel = this;

        networkStatusModelForCheckout = new NetworkStatusModelForCheckout();
        networkStatusModelForCheckout.setAllFalse(false);

        deliveryMethod = repository.getDeliveryMethod();
        userMobileNo = repository.getuserMobileNo();
        cartContainer = repository.getCartlist();
        shippingZones = repository.getShippingZone();
        shippingAreas = repository.getShippinArea();
        productVatLive = repository.getProductVat();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Order");
        if (fusedLocationClient == null) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(application);
        }
    }

    public LiveData<List<CartContainer>> getCartContainer() {

        return cartContainer;
    }

    public void getUserInfoByMobile(String userMobile) {
        repository.getUser(userMobile);
    }

    public LiveData<List<User>> getUserMobileNo() {
        return userMobileNo;
    }

    public MutableLiveData<List<User>> getUserData() {
        return userData;
    }

    public MutableLiveData<List<DeliveryMethod>> getDeliveryMethod() {
        return deliveryMethod;
    }

    public MutableLiveData<List<ShippingZone>> getShippingZones() {
        return shippingZones;
    }

    public MutableLiveData<List<ShippingArea>> getShippingAreas() {
        return shippingAreasByZone;
    }

    public void getshippingareaByZone(int positoin) {

        List<ShippingArea> shippingAreaReturn = new ArrayList<>();
        shippingAreaReturn.add(new ShippingArea("Select Address"));

        if (0 < positoin) {
            String zoneid = shippingZones.getValue().get(positoin).getZoneId();
            for (int i = 0; i < shippingAreas.getValue().size(); i++) {
                if (zoneid.equals(shippingAreas.getValue().get(i).getZoneId())) {
                    shippingAreaReturn.add(shippingAreas.getValue().get(i));
                }
            }
        }
        shippingAreasByZone.postValue(shippingAreaReturn);
    }


    public void sendConfirmOder(Cheakout cheakout, User user) {
        repository.sendConfirmOder(cheakout, user);
    }

    public MutableLiveData<ConfirmOrderResponse> getConfirmOderResponseLive() {
        return confirmOderResponseLive;
    }


    public MutableLiveData<List<ProductVat>> getProductVat() {
        return productVatLive;
    }

    public void clearCart(Cheakout cheakout){
        repository.clearCartData(cheakout);
    }

    @Override
    public void UserInfoStatus(Boolean status) {
        networkStatusModelForCheckout.setUserInfo(status);
        networkStatusModelForCheckoutLive.postValue(networkStatusModelForCheckout);
    }

    @Override
    public void ShippingZoneStatus(Boolean status) {
        networkStatusModelForCheckout.setShipingZone(status);
        networkStatusModelForCheckoutLive.postValue(networkStatusModelForCheckout);
    }

    @Override
    public void DeliveryMethodStatus(Boolean status) {
        networkStatusModelForCheckout.setDeliveryMethod(status);
        networkStatusModelForCheckoutLive.postValue(networkStatusModelForCheckout);
    }

    @Override
    public void ProductVatStatus(Boolean status) {
        networkStatusModelForCheckout.setProductVat(status);
        networkStatusModelForCheckoutLive.postValue(networkStatusModelForCheckout);
    }

    @Override
    public void SetUserInfo(List<User> user) {
        userData.postValue(user);
    }


    @Override
    public void SetConfirmDelivery(ConfirmOrderResponse confirmOrderResponse) {
        confirmOderResponseLive.postValue(confirmOrderResponse);
    }


    public MutableLiveData<NetworkStatusModelForCheckout> getNetworkStatus() {
        return networkStatusModelForCheckoutLive;
    }


    public void storeDataToFirebase(Activity context, User currentUser, Cheakout cheakout, String data) {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
        }
        if (myRef == null) {
            myRef = database.getReference("Order");
        }
        if (cheakout != null) {
            String json = new Gson().toJson(cheakout.getProduct_data());
            myRef.child(currentUser.getCustomerMobileNumber()).child("Address").setValue(context.getString(R.string.firebse_exten_start) + cheakout.getShipping_details_address() + context.getString(R.string.firebse_exten_end));
            myRef.child(currentUser.getCustomerMobileNumber()).child("Area").setValue(context.getString(R.string.firebse_exten_start) + cheakout.getShipping_area_name() + context.getString(R.string.firebse_exten_end));
            myRef.child(currentUser.getCustomerMobileNumber()).child("Name").setValue(context.getString(R.string.firebse_exten_start) + cheakout.getCustomer_full_name() + context.getString(R.string.firebse_exten_end));
            myRef.child(currentUser.getCustomerMobileNumber()).child("Product").setValue(json);
            myRef.child(currentUser.getCustomerMobileNumber()).child("Order ID").setValue(context.getString(R.string.firebse_exten_start) + data + context.getString(R.string.firebse_exten_end));
            myRef.child(currentUser.getCustomerMobileNumber()).child("Total").setValue(context.getString(R.string.firebse_exten_start) + cheakout.getProduct_order_total() + context.getString(R.string.firebse_exten_end));
        }
        if (fusedLocationClient != null && isLocationSatisfied(context)) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(context, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Log.e("CheckoutFragment", "0");
                                myRef.child(currentUser.getCustomerMobileNumber()).child("Lat").setValue(context.getString(R.string.firebse_exten_start) + location.getLatitude() + context.getString(R.string.firebse_exten_end));
                                myRef.child(currentUser.getCustomerMobileNumber()).child("Long").setValue(context.getString(R.string.firebse_exten_start) + location.getLongitude() + context.getString(R.string.firebse_exten_end));
                            } else {
                                Log.e("CheckoutFragment", "2");
                            }
                        }
                    });
        } else {
            Log.e("CheckoutFragment", "1");
        }
    }


    public boolean isLocationSatisfied(Activity context) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(context);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(context, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                isLocationEnable = true;
            }
        });

        task.addOnFailureListener(context, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(context, 1000);
                    } catch (IntentSender.SendIntentException sendEx) {
                        Log.e("Location", sendEx.getMessage() + "");
                    }
                }
            }
        });

        return isLocationEnable;
    }
}
