package com.group.etoko.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = {"subCategoryId"}, unique = true)})
public class SubCategory {
    @PrimaryKey
    @NonNull
    @SerializedName("sub_category_id")
    @Expose
    public String subCategoryId;
    @SerializedName("category_id")
    @Expose
    private String categoryID;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("sub_category_description")
    @Expose
    @Ignore
    private String subCategoryDescription;
    @SerializedName("sub_category_icon")
    @Expose
    private String subCategoryIcon;
    @SerializedName("sub_category_image")
    @Expose
    private String subCategoryImage;
    @SerializedName("added_user_id")
    @Expose
    @Ignore
    private String addedUserId;
    @SerializedName("updated_user_id")
    @Expose
    @Ignore
    private Object updatedUserId;
    @SerializedName("sub_category_created_date")
    @Expose
    @Ignore
    private String subCategoryCreatedDate;
    @SerializedName("subcategory_publication_status")
    @Expose
    @Ignore
    private String subcategoryPublicationStatus;
    @SerializedName("category_total_hit_count")
    @Expose
    @Ignore
    private Object categoryTotalHitCount;

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    public String getSubCategoryIcon() {
        return subCategoryIcon;
    }

    public void setSubCategoryIcon(String subCategoryIcon) {
        this.subCategoryIcon = subCategoryIcon;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public String getAddedUserId() {
        return addedUserId;
    }

    public void setAddedUserId(String addedUserId) {
        this.addedUserId = addedUserId;
    }

    public Object getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(Object updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public String getSubCategoryCreatedDate() {
        return subCategoryCreatedDate;
    }

    public void setSubCategoryCreatedDate(String subCategoryCreatedDate) {
        this.subCategoryCreatedDate = subCategoryCreatedDate;
    }

    public String getSubcategoryPublicationStatus() {
        return subcategoryPublicationStatus;
    }

    public void setSubcategoryPublicationStatus(String subcategoryPublicationStatus) {
        this.subcategoryPublicationStatus = subcategoryPublicationStatus;
    }

    public Object getCategoryTotalHitCount() {
        return categoryTotalHitCount;
    }

    public void setCategoryTotalHitCount(Object categoryTotalHitCount) {
        this.categoryTotalHitCount = categoryTotalHitCount;
    }
}
