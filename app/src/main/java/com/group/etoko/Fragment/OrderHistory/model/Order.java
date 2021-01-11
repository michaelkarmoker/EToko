package com.group.etoko.Fragment.OrderHistory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {
    @SerializedName("product_order_id")
    @Expose
    private String product_order_id;
    @SerializedName("custom_order_id")
    @Expose
    private String custom_order_id;
    @SerializedName("customer_id")
    @Expose
    private String customer_id;
    @SerializedName("shipping_id")
    @Expose
    private String shipping_id;
    @SerializedName("payment_info_id")
    @Expose
    private String payment_info_id;
    @SerializedName("shipping_full_name")
    @Expose
    private String shipping_full_name;
    @SerializedName("shipping_mobile_number")
    @Expose
    private String shipping_mobile_number;
    @SerializedName("shipping_zone_name")
    @Expose
    private String shipping_zone_name;
    @SerializedName("shipping_zone_id")
    @Expose
    private String shipping_zone_id;
    @SerializedName("shipping_area_id")
    @Expose
    private String shipping_area_id;
    @SerializedName("shipping_area_name")
    @Expose
    private String shipping_area_name;
    @SerializedName("shipping_details_address")
    @Expose
    private String shipping_details_address;
    @SerializedName("product_order_total")
    @Expose
    private String product_order_total;
    @SerializedName("product_order_coupon_amount")
    @Expose
    private String product_order_coupon_amount;
    @SerializedName("product_order_customer_ref_balance")
    @Expose
    private String product_order_customer_ref_balance;
    @SerializedName("product_order_shipping_rate")
    @Expose
    private String product_order_shipping_rate;
    @SerializedName("product_order_shipping_method_name")
    @Expose
    private String product_order_shipping_method_name;
    @SerializedName("product_total_vat")
    @Expose
    private String product_total_vat;
    @SerializedName("product_order_additional_shipping_cost")
    @Expose
    private String product_order_additional_shipping_cost;
    @SerializedName("product_order_net")
    @Expose
    private String product_order_net;
    @SerializedName("product_order_qty")
    @Expose
    private String product_order_qty;
    @SerializedName("product_order_status")
    @Expose
    private String product_order_status;
    @SerializedName("product_order_date_time")
    @Expose
    private String product_order_date_time;
    @SerializedName("product_order_by")
    @Expose
    private String product_order_by;
    @SerializedName("product_order_process_time")
    @Expose
    private String product_order_process_time;
    @SerializedName("product_order_shipping_time")
    @Expose
    private String product_order_shipping_time;
    @SerializedName("product_order_completed_time")
    @Expose
    private String product_order_completed_time;
    @SerializedName("product_order_cancel_time")
    @Expose
    private String product_order_cancel_time;



    private boolean open=false;

    public boolean isopen() {
        return open;
    }

    public void setopen(boolean isopen) {
        this.open = isopen;
    }

    public String getProduct_order_id() {
        return product_order_id;
    }

    public void setProduct_order_id(String product_order_id) {
        this.product_order_id = product_order_id;
    }

    public String getCustom_order_id() {
        return custom_order_id;
    }

    public void setCustom_order_id(String custom_order_id) {
        this.custom_order_id = custom_order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getPayment_info_id() {
        return payment_info_id;
    }

    public void setPayment_info_id(String payment_info_id) {
        this.payment_info_id = payment_info_id;
    }

    public String getShipping_full_name() {
        return shipping_full_name;
    }

    public void setShipping_full_name(String shipping_full_name) {
        this.shipping_full_name = shipping_full_name;
    }

    public String getShipping_mobile_number() {
        return shipping_mobile_number;
    }

    public void setShipping_mobile_number(String shipping_mobile_number) {
        this.shipping_mobile_number = shipping_mobile_number;
    }

    public String getShipping_zone_name() {
        return shipping_zone_name;
    }

    public void setShipping_zone_name(String shipping_zone_name) {
        this.shipping_zone_name = shipping_zone_name;
    }

    public String getShipping_zone_id() {
        return shipping_zone_id;
    }

    public void setShipping_zone_id(String shipping_zone_id) {
        this.shipping_zone_id = shipping_zone_id;
    }

    public String getShipping_area_id() {
        return shipping_area_id;
    }

    public void setShipping_area_id(String shipping_area_id) {
        this.shipping_area_id = shipping_area_id;
    }

    public String getShipping_area_name() {
        return shipping_area_name;
    }

    public void setShipping_area_name(String shipping_area_name) {
        this.shipping_area_name = shipping_area_name;
    }

    public String getShipping_details_address() {
        return shipping_details_address;
    }

    public void setShipping_details_address(String shipping_details_address) {
        this.shipping_details_address = shipping_details_address;
    }

    public String getProduct_order_total() {
        return product_order_total;
    }

    public void setProduct_order_total(String product_order_total) {
        this.product_order_total = product_order_total;
    }

    public String getProduct_order_coupon_amount() {
        return product_order_coupon_amount;
    }

    public void setProduct_order_coupon_amount(String product_order_coupon_amount) {
        this.product_order_coupon_amount = product_order_coupon_amount;
    }

    public String getProduct_order_customer_ref_balance() {
        return product_order_customer_ref_balance;
    }

    public void setProduct_order_customer_ref_balance(String product_order_customer_ref_balance) {
        this.product_order_customer_ref_balance = product_order_customer_ref_balance;
    }

    public String getProduct_order_shipping_rate() {
        return product_order_shipping_rate;
    }

    public void setProduct_order_shipping_rate(String product_order_shipping_rate) {
        this.product_order_shipping_rate = product_order_shipping_rate;
    }

    public String getProduct_order_shipping_method_name() {
        return product_order_shipping_method_name;
    }

    public void setProduct_order_shipping_method_name(String product_order_shipping_method_name) {
        this.product_order_shipping_method_name = product_order_shipping_method_name;
    }

    public String getProduct_total_vat() {
        return product_total_vat;
    }

    public void setProduct_total_vat(String product_total_vat) {
        this.product_total_vat = product_total_vat;
    }

    public String getProduct_order_additional_shipping_cost() {
        return product_order_additional_shipping_cost;
    }

    public void setProduct_order_additional_shipping_cost(String product_order_additional_shipping_cost) {
        this.product_order_additional_shipping_cost = product_order_additional_shipping_cost;
    }

    public String getProduct_order_net() {
        return product_order_net;
    }

    public void setProduct_order_net(String product_order_net) {
        this.product_order_net = product_order_net;
    }

    public String getProduct_order_qty() {
        return product_order_qty;
    }

    public void setProduct_order_qty(String product_order_qty) {
        this.product_order_qty = product_order_qty;
    }

    public String getProduct_order_status() {
        return product_order_status;
    }

    public void setProduct_order_status(String product_order_status) {
        this.product_order_status = product_order_status;
    }

    public String getProduct_order_date_time() {
        return product_order_date_time;
    }

    public void setProduct_order_date_time(String product_order_date_time) {
        this.product_order_date_time = product_order_date_time;
    }

    public String getProduct_order_by() {
        return product_order_by;
    }

    public void setProduct_order_by(String product_order_by) {
        this.product_order_by = product_order_by;
    }

    public String getProduct_order_process_time() {
        return product_order_process_time;
    }

    public void setProduct_order_process_time(String product_order_process_time) {
        this.product_order_process_time = product_order_process_time;
    }

    public String getProduct_order_shipping_time() {
        return product_order_shipping_time;
    }

    public void setProduct_order_shipping_time(String product_order_shipping_time) {
        this.product_order_shipping_time = product_order_shipping_time;
    }

    public String getProduct_order_completed_time() {
        return product_order_completed_time;
    }

    public void setProduct_order_completed_time(String product_order_completed_time) {
        this.product_order_completed_time = product_order_completed_time;
    }

    public String getProduct_order_cancel_time() {
        return product_order_cancel_time;
    }

    public void setProduct_order_cancel_time(String product_order_cancel_time) {
        this.product_order_cancel_time = product_order_cancel_time;
    }

}
