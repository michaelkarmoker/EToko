package com.group.etoko.Fragment.Checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingArea {

    @SerializedName("shipping_area_id")
    @Expose
    private String areaId;
    @SerializedName("shipping_zone_id")
    @Expose
    private String zoneId;
    @SerializedName("shipping_area_name")
    @Expose
    private String areaName;
    @SerializedName("shipping_area_is_publish")
    @Expose
    private String areaIsPublish;
    @SerializedName("area_added_user_id")
    @Expose
    private String areaAddedUserId;
    @SerializedName("area_added_date")
    @Expose
    private String areaAddedDate;

    public ShippingArea(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaIsPublish() {
        return areaIsPublish;
    }

    public void setAreaIsPublish(String areaIsPublish) {
        this.areaIsPublish = areaIsPublish;
    }

    public String getAreaAddedUserId() {
        return areaAddedUserId;
    }

    public void setAreaAddedUserId(String areaAddedUserId) {
        this.areaAddedUserId = areaAddedUserId;
    }

    public String getAreaAddedDate() {
        return areaAddedDate;
    }

    public void setAreaAddedDate(String areaAddedDate) {
        this.areaAddedDate = areaAddedDate;
    }
    @Override
    public String toString() {
        return this.areaName;
    }
}
