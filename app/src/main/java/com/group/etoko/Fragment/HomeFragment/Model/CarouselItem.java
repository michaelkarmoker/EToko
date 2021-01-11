package com.group.etoko.Fragment.HomeFragment.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CarouselItem {
    @PrimaryKey
    @NonNull
    @SerializedName("image_slide_id")
    @Expose
    private String imageSlideId;
    @NonNull
    @SerializedName("image_name")
    @Expose
    private String imageName;
    @SerializedName("image_link")
    @Expose
    @NonNull
    private String imageLink;
    @SerializedName("image_slide_added_user_id")
    @Expose
    @Ignore
    private String imageSlideAddedUserId;
    @SerializedName("image_slide_is_publish")
    @Expose
    @Ignore
    private String imageSlideIsPublish;
    @SerializedName("image_slider_created_date")
    @Expose
    @Ignore
    private String imageSliderCreatedDate;

    public String getImageSlideId() {
        return imageSlideId;
    }

    public void setImageSlideId(String imageSlideId) {
        this.imageSlideId = imageSlideId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageSlideAddedUserId() {
        return imageSlideAddedUserId;
    }

    public void setImageSlideAddedUserId(String imageSlideAddedUserId) {
        this.imageSlideAddedUserId = imageSlideAddedUserId;
    }

    public String getImageSlideIsPublish() {
        return imageSlideIsPublish;
    }

    public void setImageSlideIsPublish(String imageSlideIsPublish) {
        this.imageSlideIsPublish = imageSlideIsPublish;
    }

    public String getImageSliderCreatedDate() {
        return imageSliderCreatedDate;
    }

    public void setImageSliderCreatedDate(String imageSliderCreatedDate) {
        this.imageSliderCreatedDate = imageSliderCreatedDate;
    }
}
