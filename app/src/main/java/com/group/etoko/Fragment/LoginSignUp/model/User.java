package com.group.etoko.Fragment.LoginSignUp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.group.etoko.BuildConfig;

@Entity(indices = {@Index(value = {"customerId"}, unique = true)})
public class User {
    @SerializedName("customer_id")
    @Expose
    @PrimaryKey
    @NonNull
    private String customerId;
    @SerializedName("customer_full_name")
    @Expose
    private String customerFullName;
    @SerializedName("customer_mobile_number")
    @Expose
    private String customerMobileNumber;
    @SerializedName("customer_email_address")
    @Expose
    private String customerEmailAddress;
    @SerializedName("customer_password")
    @Expose
    @Ignore
    private Object customerPassword;
    @SerializedName("customer_reg_date")
    @Expose
    @Ignore
    private String customerRegDate;
    @SerializedName("customer_reg_by")
    @Expose
    @Ignore
    private String customerRegBy;
    @SerializedName("customer_ref_code")
    @Expose
    private String customerRefCode;
    @SerializedName("customer_ref_balance")
    @Expose
    private String customerRefBalance;
    @SerializedName("ref_customer_id")
    @Expose
    @Ignore
    private String refCustomerId;
    @SerializedName("customer_ref_limit")
    @Expose
    @Ignore
    private String customerRefLimit;
    @SerializedName("customer_other_ref_code")
    @Expose
    @Ignore
    private String customerOtherRefCode;

    @SerializedName("customer_activation_status")
    @Expose
    @Ignore
    private String customerActivationStatus;

    private String customerAddress;
    @SerializedName("choloshop_api")
    private String choloshop_api= BuildConfig.APP_API;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId( String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }



    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public Object getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(Object customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerRegDate() {
        return customerRegDate;
    }

    public void setCustomerRegDate(String customerRegDate) {
        this.customerRegDate = customerRegDate;
    }

    public String getCustomerRegBy() {
        return customerRegBy;
    }

    public void setCustomerRegBy(String customerRegBy) {
        this.customerRegBy = customerRegBy;
    }

    public String getCustomerActivationStatus() {
        return customerActivationStatus;
    }

    public void setCustomerActivationStatus(String customerActivationStatus) {
        this.customerActivationStatus = customerActivationStatus;
    }

    public String getCustomerRefCode() {
        return customerRefCode;
    }

    public void setCustomerRefCode(String customerRefCode) {
        this.customerRefCode = customerRefCode;
    }

    public String getCustomerRefBalance() {
        return customerRefBalance;
    }

    public void setCustomerRefBalance(String customerRefBalance) {
        this.customerRefBalance = customerRefBalance;
    }

    public String getRefCustomerId() {
        return refCustomerId;
    }

    public void setRefCustomerId(String refCustomerId) {
        this.refCustomerId = refCustomerId;
    }

    public String getCustomerRefLimit() {
        return customerRefLimit;
    }

    public void setCustomerRefLimit(String customerRefLimit) {
        this.customerRefLimit = customerRefLimit;
    }

    public String getCustomerOtherRefCode() {
        return customerOtherRefCode;
    }

    public void setCustomerOtherRefCode(String customerOtherRefCode) {
        this.customerOtherRefCode = customerOtherRefCode;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCholoshop_api() {
        return choloshop_api;
    }

    public void setCholoshop_api(String choloshop_api) {
        this.choloshop_api = choloshop_api;
    }


}
