package com.group.etoko.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = {"collectionId"}, unique = true)})
public class Collection {
    @SerializedName("collection_id")
    @Expose
    @PrimaryKey
    @NonNull
    private String collectionId;
    @SerializedName("collection_name")
    @Expose
    private String collectionName;
    @SerializedName("collection_image")
    @Expose
    @Ignore
    private Object collectionImage;
    @SerializedName("added_user_id")
    @Expose
    @Ignore
    private String addedUserId;
    @SerializedName("collection_is_active")
    @Expose
    @Ignore
    private String collectionIsActive;
    @SerializedName("collection_created_date")
    @Expose
    @Ignore
    private String collectionCreatedDate;


    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Object getCollectionImage() {
        return collectionImage;
    }

    public void setCollectionImage(Object collectionImage) {
        this.collectionImage = collectionImage;
    }

    public String getAddedUserId() {
        return addedUserId;
    }

    public void setAddedUserId(String addedUserId) {
        this.addedUserId = addedUserId;
    }

    public String getCollectionIsActive() {
        return collectionIsActive;
    }

    public void setCollectionIsActive(String collectionIsActive) {
        this.collectionIsActive = collectionIsActive;
    }

    public String getCollectionCreatedDate() {
        return collectionCreatedDate;
    }

    public void setCollectionCreatedDate(String collectionCreatedDate) {
        this.collectionCreatedDate = collectionCreatedDate;
    }
}
