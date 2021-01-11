package com.group.etoko.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = {"categoryId"}, unique = true)})
public class Category {
    @SerializedName("category_id")
    @Expose
    @PrimaryKey
    @NonNull
    public String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_description")
    @Expose
    private String categoryDescription;
    @SerializedName("category_icon")
    @Expose
    private String categoryIcon;
    @SerializedName("category_image")
    @Expose
    @Ignore
    private Object categoryImage;
    @SerializedName("added_user_id")
    @Expose
    @Ignore
    private String addedUserId;
    @SerializedName("updated_user_id")
    @Expose
    @Ignore
    private String updatedUserId;
    @SerializedName("category_created_date")
    @Expose
    @Ignore
    private String categoryCreatedDate;
    @SerializedName("publication_status")
    @Expose
    @Ignore
    private String publicationStatus;
    @SerializedName("category_total_hit_count")
    @Expose
    @Ignore
    private Object categoryTotalHitCount;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public Object getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(Object categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getAddedUserId() {
        return addedUserId;
    }

    public void setAddedUserId(String addedUserId) {
        this.addedUserId = addedUserId;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public String getCategoryCreatedDate() {
        return categoryCreatedDate;
    }

    public void setCategoryCreatedDate(String categoryCreatedDate) {
        this.categoryCreatedDate = categoryCreatedDate;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public Object getCategoryTotalHitCount() {
        return categoryTotalHitCount;
    }

    public void setCategoryTotalHitCount(Object categoryTotalHitCount) {
        this.categoryTotalHitCount = categoryTotalHitCount;
    }
}
