package com.group.etoko.Fragment.Cart.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.group.etoko.model.Cart;
import com.group.etoko.model.Product;

public class CartContainer {

    @Embedded
    public Cart cart=null;

    @Relation(parentColumn = "productId",entityColumn = "productId")
    public Product product=null;
}
