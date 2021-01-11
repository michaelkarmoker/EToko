package com.group.etoko.Fragment.Checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductVat {

    @SerializedName("product_vat_id")
    @Expose
    private String productVatId;
    @SerializedName("vat_name")
    @Expose
    private String vatName;
    @SerializedName("vat_percent")
    @Expose
    private String vatPercent;
    @SerializedName("added_user_id")
    @Expose
    private String addedUserId;
    @SerializedName("vat_is_publish")
    @Expose
    private String vatIsPublish;
    @SerializedName("vat_added_date")
    @Expose
    private String vatAddedDate;

    public String getProductVatId() {
        return productVatId;
    }

    public void setProductVatId(String productVatId) {
        this.productVatId = productVatId;
    }

    public String getVatName() {
        return vatName;
    }

    public void setVatName(String vatName) {
        this.vatName = vatName;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getAddedUserId() {
        return addedUserId;
    }

    public void setAddedUserId(String addedUserId) {
        this.addedUserId = addedUserId;
    }

    public String getVatIsPublish() {
        return vatIsPublish;
    }

    public void setVatIsPublish(String vatIsPublish) {
        this.vatIsPublish = vatIsPublish;
    }

    public String getVatAddedDate() {
        return vatAddedDate;
    }

    public void setVatAddedDate(String vatAddedDate) {
        this.vatAddedDate = vatAddedDate;
    }
}
