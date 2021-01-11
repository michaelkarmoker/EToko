package com.group.etoko.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(indices = {@Index(value = {"seasonalCatId"}, unique = true)})
public class SeasonalCategory {

    @SerializedName("seasonal_category_id")
    @Expose
    @PrimaryKey
    @NonNull
    public String seasonalCatId;
    @SerializedName("seasonal_category_name")
    @Expose
    public String seasonalCatName;
    @SerializedName("seasonal_category_image")
    @Expose
    public String seasonalCatImage;
    @SerializedName("seasonal_category_is_publish")
    @Expose
    public String seasonalCatPublish;
    @SerializedName("added_user_id")
    @Expose
    public String addedUserId;
    @SerializedName("seasonal_category_created_date")
    @Expose
    public String seasonalCatCreatedDate;

    public SeasonalCategory(@NonNull String seasonalCatId, String seasonalCatName, String seasonalCatImage, String seasonalCatPublish, String addedUserId, String seasonalCatCreatedDate) {
        this.seasonalCatId = seasonalCatId;
        this.seasonalCatName = seasonalCatName;
        this.seasonalCatImage = seasonalCatImage;
        this.seasonalCatPublish = seasonalCatPublish;
        this.addedUserId = addedUserId;
        this.seasonalCatCreatedDate = seasonalCatCreatedDate;
    }

    @NonNull
    public String getSeasonalCatId() {
        return seasonalCatId;
    }

    public void setSeasonalCatId(@NonNull String seasonalCatId) {
        this.seasonalCatId = seasonalCatId;
    }

    public String getSeasonalCatName() {
        return seasonalCatName;
    }

    public void setSeasonalCatName(String seasonalCatName) {
        this.seasonalCatName = seasonalCatName;
    }

    public String getSeasonalCatImage() {
        return seasonalCatImage;
    }

    public void setSeasonalCatImage(String seasonalCatImage) {
        this.seasonalCatImage = seasonalCatImage;
    }

    public String getSeasonalCatPublish() {
        return seasonalCatPublish;
    }

    public void setSeasonalCatPublish(String seasonalCatPublish) {
        this.seasonalCatPublish = seasonalCatPublish;
    }

    public String getAddedUserId() {
        return addedUserId;
    }

    public void setAddedUserId(String addedUserId) {
        this.addedUserId = addedUserId;
    }

    public String getSeasonalCatCreatedDate() {
        return seasonalCatCreatedDate;
    }

    public void setSeasonalCatCreatedDate(String seasonalCatCreatedDate) {
        this.seasonalCatCreatedDate = seasonalCatCreatedDate;
    }
}
