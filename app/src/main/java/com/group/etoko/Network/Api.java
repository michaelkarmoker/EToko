package com.group.etoko.Network;

import com.group.etoko.Fragment.Cart.model.Coupon;
import com.group.etoko.Fragment.Cart.model.MinimumAmountContainer;
import com.group.etoko.Fragment.Checkout.model.Cheakout;
import com.group.etoko.Fragment.Checkout.model.ConfirmOrderResponse;
import com.group.etoko.Fragment.Checkout.model.ProductVatContainer;
import com.group.etoko.Fragment.Checkout.model.ShippingAreaContainer;
import com.group.etoko.Fragment.Checkout.model.ShippingZoneContainer;
import com.group.etoko.Fragment.LoginSignUp.model.CheckUserRegisterModel;
import com.group.etoko.Fragment.LoginSignUp.model.OTPModel;
import com.group.etoko.Fragment.LoginSignUp.model.User;
import com.group.etoko.Fragment.LoginSignUp.model.UserContainer;
import com.group.etoko.Fragment.productDetails.Model.ModelShareData;
import com.group.etoko.model.CategoryContainer;
import com.group.etoko.model.CollectionContainer;
import com.group.etoko.Fragment.Checkout.model.DeliveryMethodContainer;
import com.group.etoko.model.ProductContainer;
import com.group.etoko.model.ReqModel;
import com.group.etoko.model.SeasonalCategoryContainer;
import com.group.etoko.model.SubCategoryContainer;
import com.group.etoko.Fragment.HomeFragment.Model.CarouselItemContainer;
import com.group.etoko.Fragment.OrderHistory.model.OrderContainer;
import com.group.etoko.Fragment.OrderHistoryDetails.model.OrderProductContainer;
import com.group.etoko.Fragment.ProfileEdit.model.ProfileUpdateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @GET("/cholo/app/api/v1/ImageSlider")
    Call<CarouselItemContainer> getSliderImageData();

    @GET("/cholo/app/api/v1/Category")
    Call<CategoryContainer> getCategory();

    @GET("/cholo/app/api/v1/SubCategory")
    Call<SubCategoryContainer> getSubCategory();

    @GET("/cholo/app/api/v1/Collection")
    Call<CollectionContainer> getCollection();

    @GET("/cholo/app/api/v1/Product")
    Call<ProductContainer> getProduct();

    @GET("/cholo/app/api/v1/SeasonalCategory")
    Call<SeasonalCategoryContainer> getSeasonalCategory();

    //--------cheak out apis--------------------------------------
    @GET("/cholo/app/api/v1/ShippingMethod")
    Call<DeliveryMethodContainer> getDeliveryMethods();

    @GET("/cholo/app/api/v1/ShippingZone")
    Call<ShippingZoneContainer> getShippingZones();

    @GET("/cholo/app/api/v1/ShippingArea")
    Call<ShippingAreaContainer> getShippingAreas();

    @GET("/cholo/app/api/v1/ProductVat")
    Call<ProductVatContainer> getProductVat();


//----checkout apii------------------
    @POST("cholo/app/api/v1/Checkout")
    Call<ConfirmOrderResponse> sendConfirmOrder(@Body Cheakout body);

//-------cart api---------------------------
    @GET("/cholo/app/api/v1/MinimumOrderAmount")
    Call<MinimumAmountContainer> getMinimumOrderAmount();

    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerCouponCheck")
    Call<Coupon> CouponCheck(@Field("choloshop_api") String apiKey, @Field("coupon_code") String couponCode, @Field("order_amount") String oderAmount);

    //------login Registration ----------------------
    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerNumberCheck")
    Call<CheckUserRegisterModel> checkUserRegistration(@Field("choloshop_api") String apiKey, @Field("customer_mobile_number") String number);

    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerOTP")
    Call<OTPModel> sendOTP(@Field("choloshop_api") String apiKey, @Field("customer_mobile_number") String number);

    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerRegistration")
    Call<UserContainer> registerUser(@Field("choloshop_api") String apiKey, @Field("customer_full_name") String fullName, @Field("customer_mobile_number") String number, @Field("customer_email_address") String email, @Field("customer_reg_date") String regDate);


    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerRegistration")
    Call<UserContainer> registerUser(@Field("choloshop_api") String apiKey, @Field("customer_full_name") String fullName, @Field("customer_mobile_number") String number, @Field("customer_email_address") String email, @Field("customer_reg_date") String regDate, @Field("customer_other_ref_code") String refCode);

    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerDataByMobile")
    Call<UserContainer> loginUser(@Field("choloshop_api") String apiKey, @Field("customer_mobile_number") String number);

//--------------------profile update----
    @PUT("cholo/app/api/v1/CustomerDataUpdate")
    Call<ProfileUpdateResponse> updateProfile(@Body User body);


    @FormUrlEncoded
    @POST("/cholo/app/api/v1/ProductIdToString")
    Call<ModelShareData> getProductURL(@Field("choloshop_api") String apiKey, @Field("product_id") String productId);

    //=------------------Order history--------------
    @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerProductOrderInfo")
    Call<OrderContainer> getCustomerOrderHistory(@Field("choloshop_api") String apiKey, @Field("customer_id") String customer_id);
    
  @FormUrlEncoded
    @POST("cholo/app/api/v1/CustomerProductOrderDetails")
    Call<OrderProductContainer> getCustomerOrderHistoryDetails(@Field("choloshop_api") String apiKey, @Field("product_order_id") String order_id);
//======Request===========
    @FormUrlEncoded
    @POST("cholo/app/api/v1/ProductRequest")
    Call<ReqModel> RequestProduct(
            @Field("choloshop_api") String apiKey,
            @Field("product_request_name") String productName,
            @Field("product_request_price") String productPrice,
            @Field("product_request_image") String image,
            @Field("product_request_customer_name") String userName,
            @Field("product_request_customer_mobile_number") String number,
            @Field("product_request_customer_id") String userId,
            @Field("product_request_image_title") String imageTitle,
            @Field("product_request_image_ext") String imageExtension
    );

    @FormUrlEncoded
    @POST("cholo/app/api/v1/EmergencyService")
    Call<ReqModel> RequestService(
            @Field("choloshop_api") String apiKey,
            @Field("emergency_service_name") String serviceName,
            @Field("emergency_service_image") String image,
            @Field("emergency_service_customer_name") String userName,
            @Field("emergency_service_customer_mobile_number") String number,
            @Field("emergency_service_customer_id") String userId,
            @Field("emergency_service_image_title") String imageTitle,
            @Field("emergency_service_image_ext") String imageExtension
    );
}
