package com.group.etoko.Fragment.Cart.viewmodel;

import com.group.etoko.Fragment.Cart.model.Coupon;
import com.group.etoko.Fragment.Cart.model.MinimumAmountContainer;

public interface CartFragmentViewModelView {
    void setCoupon(Coupon coupon);
    void setMinimumAmount(MinimumAmountContainer minimumAmount);
}
