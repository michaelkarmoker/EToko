package com.group.etoko.Fragment.Checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryMethod {
    @SerializedName("shipping_method_id")
    @Expose
    private String methodId;
    @SerializedName("shipping_method_name")
    @Expose
    private String methodName;
    @SerializedName("shipping_method_rate")
    @Expose
    private String methodRate;
    @SerializedName("shipping_method_is_publish")
    @Expose
    private String methodIsPublish;
    @SerializedName("shipping_method_added_user_id")
    @Expose
    private String methodAddedUserId;
    @SerializedName("shipping_method_created_date")
    @Expose
    private String methodCreatedDate;


    public DeliveryMethod(String methodId, String methodName, String methodRate, String methodIsPublish, String methodAddedUserId, String methodCreatedDate) {
        this.methodId = methodId;
        this.methodName = methodName;
        this.methodRate = methodRate;
        this.methodIsPublish = methodIsPublish;
        this.methodAddedUserId = methodAddedUserId;
        this.methodCreatedDate = methodCreatedDate;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodRate() {
        return methodRate;
    }

    public void setMethodRate(String methodRate) {
        this.methodRate = methodRate;
    }

    public String getMethodIsPublish() {
        return methodIsPublish;
    }

    public void setMethodIsPublish(String methodIsPublish) {
        this.methodIsPublish = methodIsPublish;
    }

    public String getMethodAddedUserId() {
        return methodAddedUserId;
    }

    public void setMethodAddedUserId(String methodAddedUserId) {
        this.methodAddedUserId = methodAddedUserId;
    }

    public String getMethodCreatedDate() {
        return methodCreatedDate;
    }

    public void setMethodCreatedDate(String methodCreatedDate) {
        this.methodCreatedDate = methodCreatedDate;
    }

}
