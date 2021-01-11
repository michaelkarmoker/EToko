package com.group.etoko.Fragment.OrderHistoryDetails.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProduct {
    @SerializedName("order_details_id")
    @Expose
    private String order_details_id;
    @SerializedName("product_order_id")
    @Expose
    private String product_order_id;
    @SerializedName("custom_order_id")
    @Expose
    private String custom_order_id;
    @SerializedName("product_id")
    @Expose
    private String product_id;
    @SerializedName("product_code")
    @Expose
    private String product_code;
    @SerializedName("product_name")
    @Expose
    private String product_name;
    @SerializedName("product_price")
    @Expose
    private String product_price;
    @SerializedName("product_purchase_price")
    @Expose
    private String product_purchase_price;
    @SerializedName("product_qty")
    @Expose
    private String product_qty;
    @SerializedName("product_image")
    @Expose
    private String product_image;
    @SerializedName("product_shipping_cost")
    @Expose
    private String product_shipping_cost;
    @SerializedName("order_created_date")
    @Expose
    private String order_created_date;


    // Getter Methods

    public String getOrder_details_id() {
        return order_details_id;
    }

    public String getProduct_order_id() {
        return product_order_id;
    }

    public String getCustom_order_id() {
        return custom_order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_purchase_price() {
        return product_purchase_price;
    }

    public String getProduct_qty() {
        return product_qty;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_shipping_cost() {
        return product_shipping_cost;
    }

    public String getOrder_created_date() {
        return order_created_date;
    }

    // Setter Methods

    public void setOrder_details_id(String order_details_id) {
        this.order_details_id = order_details_id;
    }

    public void setProduct_order_id(String product_order_id) {
        this.product_order_id = product_order_id;
    }

    public void setCustom_order_id(String custom_order_id) {
        this.custom_order_id = custom_order_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_purchase_price(String product_purchase_price) {
        this.product_purchase_price = product_purchase_price;
    }

    public void setProduct_qty(String product_qty) {
        this.product_qty = product_qty;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public void setProduct_shipping_cost(String product_shipping_cost) {
        this.product_shipping_cost = product_shipping_cost;
    }

    public void setOrder_created_date(String order_created_date) {
        this.order_created_date = order_created_date;
    }
}
