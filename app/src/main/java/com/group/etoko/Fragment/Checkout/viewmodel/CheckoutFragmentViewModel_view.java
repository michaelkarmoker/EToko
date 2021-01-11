package com.group.etoko.Fragment.Checkout.viewmodel;

import com.group.etoko.Fragment.Checkout.model.ConfirmOrderResponse;
import com.group.etoko.Fragment.LoginSignUp.model.User;

import java.util.List;

public interface CheckoutFragmentViewModel_view {

    void UserInfoStatus(Boolean status);
    void ShippingZoneStatus(Boolean status);
    void DeliveryMethodStatus(Boolean status);
    void ProductVatStatus(Boolean status);
    void SetUserInfo(List<User> user);
    void SetConfirmDelivery(ConfirmOrderResponse confirmOrderResponse);
}
