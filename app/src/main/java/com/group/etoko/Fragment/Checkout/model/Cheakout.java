package com.group.etoko.Fragment.Checkout.model;

import com.google.gson.annotations.SerializedName;
import com.group.etoko.BuildConfig;
import com.group.etoko.model.Product;

import java.util.List;

public class Cheakout {
    @SerializedName("customer_id")
    private String customer_id;
    @SerializedName("shipping_id")
    private String shipping_id;
    @SerializedName("customer_full_name")
    private String customer_full_name;
    @SerializedName("customer_mobile_number")
    private String customer_mobile_number;
    @SerializedName("shipping_zone_name")
    private String shipping_zone_name;
    @SerializedName("shipping_zone_id")
    private String shipping_zone_id;
    @SerializedName("shipping_area_name")
    private String shipping_area_name;
    @SerializedName("shipping_area_id")
    private String shipping_area_id;
    @SerializedName("shipping_details_address")
    private String shipping_details_address;
    @SerializedName("customer_email_address")
    private String customer_email_address;
    @SerializedName("save_shipping_info")
    private String save_shipping_info;
    @SerializedName("product_order_total")
    private String product_order_total;
    @SerializedName("product_total_vat")
    private String product_total_vat;
    @SerializedName("product_order_coupon_amount")
    private String product_order_coupon_amount;
    @SerializedName("product_order_shipping_rate")
    private String product_order_shipping_rate;
    @SerializedName("product_order_shipping_method_name")
    private String product_order_shipping_method_name;
    @SerializedName("product_order_customer_ref_balance")
    private String product_order_customer_ref_balance;

    @SerializedName("product_order_additional_shipping_cost")
    private String product_order_additional_shipping_cost;
    @SerializedName("product_order_net")
    private String product_order_net;
    @SerializedName("product_order_qty")
    private String product_order_qty;
    @SerializedName("payment_method")
    private String payment_method;
    @SerializedName("choloshop_api")
    private String choloshop_api= BuildConfig.APP_API;
    @SerializedName("product_data")
    private List<Product> product_data;

    public Cheakout(String customer_id, String shipping_id, String customer_full_name, String customer_mobile_number, String shipping_zone_name, String shipping_zone_id, String shipping_area_name, String shipping_area_id, String shipping_details_address, String customer_email_address, String save_shipping_info, String product_order_total, String product_total_vat, String product_order_coupon_amount, String product_order_shipping_rate, String product_order_shipping_method_name, String product_order_customer_ref_balance, String product_order_additional_shipping_cost, String product_order_net, String product_order_qty, String payment_method, List<Product> product_data) {
        this.customer_id = customer_id;
        this.shipping_id = shipping_id;
        this.customer_full_name = customer_full_name;
        this.customer_mobile_number = customer_mobile_number;
        this.shipping_zone_name = shipping_zone_name;
        this.shipping_zone_id = shipping_zone_id;
        this.shipping_area_name = shipping_area_name;
        this.shipping_area_id = shipping_area_id;
        this.shipping_details_address = shipping_details_address;
        this.customer_email_address = customer_email_address;
        this.save_shipping_info = save_shipping_info;
        this.product_order_total = product_order_total;
        this.product_total_vat = product_total_vat;
        this.product_order_coupon_amount = product_order_coupon_amount;
        this.product_order_shipping_rate = product_order_shipping_rate;
        this.product_order_shipping_method_name = product_order_shipping_method_name;
        this.product_order_customer_ref_balance = product_order_customer_ref_balance;
        this.product_order_additional_shipping_cost = product_order_additional_shipping_cost;
        this.product_order_net = product_order_net;
        this.product_order_qty = product_order_qty;
        this.payment_method = payment_method;
        this.product_data = product_data;
    }

    // Getter Methods

    public String getCustomer_id() {
        return customer_id;
    }

    public String getShipping_id() {
        return shipping_id;
    }

    public String getCustomer_full_name() {
        return customer_full_name;
    }

    public String getCustomer_mobile_number() {
        return customer_mobile_number;
    }

    public String getShipping_zone_name() {
        return shipping_zone_name;
    }

    public String getShipping_zone_id() {
        return shipping_zone_id;
    }

    public String getShipping_area_name() {
        return shipping_area_name;
    }

    public String getShipping_area_id() {
        return shipping_area_id;
    }

    public String getShipping_details_address() {
        return shipping_details_address;
    }

    public String getCustomer_email_address() {
        return customer_email_address;
    }

    public String getSave_shipping_info() {
        return save_shipping_info;
    }

    public String getProduct_order_total() {
        return product_order_total;
    }

    public String getProduct_total_vat() {
        return product_total_vat;
    }

    public String getProduct_order_coupon_amount() {
        return product_order_coupon_amount;
    }

    public String getProduct_order_shipping_rate() {
        return product_order_shipping_rate;
    }

    public String getProduct_order_additional_shipping_cost() {
        return product_order_additional_shipping_cost;
    }

    public String getProduct_order_net() {
        return product_order_net;
    }

    public String getProduct_order_qty() {
        return product_order_qty;
    }

    public String getPayment_method() {
        return payment_method;
    }

    // Setter Methods

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setShipping_id(String shipping_id) {
        this.shipping_id = shipping_id;
    }

    public void setCustomer_full_name(String customer_full_name) {
        this.customer_full_name = customer_full_name;
    }

    public void setCustomer_mobile_number(String customer_mobile_number) {
        this.customer_mobile_number = customer_mobile_number;
    }

    public void setShipping_zone_name(String shipping_zone_name) {
        this.shipping_zone_name = shipping_zone_name;
    }

    public void setShipping_zone_id(String shipping_zone_id) {
        this.shipping_zone_id = shipping_zone_id;
    }

    public void setShipping_area_name(String shipping_area_name) {
        this.shipping_area_name = shipping_area_name;
    }

    public void setShipping_area_id(String shipping_area_id) {
        this.shipping_area_id = shipping_area_id;
    }

    public void setShipping_details_address(String shipping_details_address) {
        this.shipping_details_address = shipping_details_address;
    }

    public void setCustomer_email_address(String customer_email_address) {
        this.customer_email_address = customer_email_address;
    }

    public void setSave_shipping_info(String save_shipping_info) {
        this.save_shipping_info = save_shipping_info;
    }

    public void setProduct_order_total(String product_order_total) {
        this.product_order_total = product_order_total;
    }

    public void setProduct_total_vat(String product_total_vat) {
        this.product_total_vat = product_total_vat;
    }

    public void setProduct_order_coupon_amount(String product_order_coupon_amount) {
        this.product_order_coupon_amount = product_order_coupon_amount;
    }

    public void setProduct_order_shipping_rate(String product_order_shipping_rate) {
        this.product_order_shipping_rate = product_order_shipping_rate;
    }

    public void setProduct_order_additional_shipping_cost(String product_order_additional_shipping_cost) {
        this.product_order_additional_shipping_cost = product_order_additional_shipping_cost;
    }

    public void setProduct_order_net(String product_order_net) {
        this.product_order_net = product_order_net;
    }

    public void setProduct_order_qty(String product_order_qty) {
        this.product_order_qty = product_order_qty;
    }

    public String getProduct_order_shipping_method_name() {
        return product_order_shipping_method_name;
    }

    public void setProduct_order_shipping_method_name(String product_order_shipping_method_name) {
        this.product_order_shipping_method_name = product_order_shipping_method_name;
    }

    public String getProduct_order_customer_ref_balance() {
        return product_order_customer_ref_balance;
    }

    public void setProduct_order_customer_ref_balance(String product_order_customer_ref_balance) {
        this.product_order_customer_ref_balance = product_order_customer_ref_balance;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public List<Product> getProduct_data() {
        return product_data;
    }

    public void setProduct_data(List<Product> product_data) {
        this.product_data = product_data;
    }

    public String getCholoshop_api() {
        return choloshop_api;
    }

    @Override
    public String toString() {
        return "Cheakout{" +
                "customer_id='" + customer_id + '\'' +
                ", shipping_id='" + shipping_id + '\'' +
                ", customer_full_name='" + customer_full_name + '\'' +
                ", customer_mobile_number='" + customer_mobile_number + '\'' +
                ", shipping_zone_name='" + shipping_zone_name + '\'' +
                ", shipping_zone_id='" + shipping_zone_id + '\'' +
                ", shipping_area_name='" + shipping_area_name + '\'' +
                ", shipping_area_id='" + shipping_area_id + '\'' +
                ", shipping_details_address='" + shipping_details_address + '\'' +
                ", customer_email_address='" + customer_email_address + '\'' +
                ", save_shipping_info='" + save_shipping_info + '\'' +
                ", product_order_total='" + product_order_total + '\'' +
                ", product_total_vat='" + product_total_vat + '\'' +
                ", product_order_coupon_amount='" + product_order_coupon_amount + '\'' +
                ", product_order_shipping_rate='" + product_order_shipping_rate + '\'' +
                ", product_order_shipping_method_name='" + product_order_shipping_method_name + '\'' +
                ", product_order_customer_ref_balance='" + product_order_customer_ref_balance + '\'' +
                ", product_order_additional_shipping_cost='" + product_order_additional_shipping_cost + '\'' +
                ", product_order_net='" + product_order_net + '\'' +
                ", product_order_qty='" + product_order_qty + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", choloshop_api='" + choloshop_api + '\'' +
                ", product_data=" + product_data +
                '}';
    }
}
