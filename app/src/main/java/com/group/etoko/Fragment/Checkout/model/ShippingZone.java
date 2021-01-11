package com.group.etoko.Fragment.Checkout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingZone {

    @SerializedName("shipping_zone_id")
    @Expose
    private String zoneId;
    @SerializedName("shipping_zone_name")
    @Expose
    private String zoneName;
    @SerializedName("shipping_zone_is_publish")
    @Expose
    private String zoneIsPublish;
    @SerializedName("zone_added_user_id")
    @Expose
    private String zoneAddedUserId;
    @SerializedName("zone_added_date")
    @Expose
    private String zoneAddedDate;

    public ShippingZone(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneIsPublish() {
        return zoneIsPublish;
    }

    public void setZoneIsPublish(String zoneIsPublish) {
        this.zoneIsPublish = zoneIsPublish;
    }

    public String getZoneAddedUserId() {
        return zoneAddedUserId;
    }

    public void setZoneAddedUserId(String zoneAddedUserId) {
        this.zoneAddedUserId = zoneAddedUserId;
    }

    public String getZoneAddedDate() {
        return zoneAddedDate;
    }

    public void setZoneAddedDate(String zoneAddedDate) {
        this.zoneAddedDate = zoneAddedDate;
    }
    @Override
    public String toString() {
        return this.zoneName;
    }
}
