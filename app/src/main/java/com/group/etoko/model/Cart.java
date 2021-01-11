package com.group.etoko.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String productId;

    @NonNull
    private double productCount;

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getProductId() {
        return productId;
    }

    public int getProductCount() {
        return (int) productCount;
    }

    public void setProductCount(double productCount) {
        this.productCount = productCount;
    }

    public void setProductId(@NonNull String productId) {
        this.productId = productId;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
