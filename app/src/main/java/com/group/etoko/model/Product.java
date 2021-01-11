package com.group.etoko.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value = {"productId", "productCode"}, unique = true)})
public class Product {

    @SerializedName("product_id")
    @Expose
    @PrimaryKey
    @NonNull
    public String productId;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_search_tag")
    @Expose
    private String productSearchTag;
    @SerializedName("category_id")
    @Expose
    @NonNull
    private String categoryID;
    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("manufacture_id")
    @Expose
    @Ignore
    private String manufactureId;
    @SerializedName("discount_id")
    @Expose
    @Ignore
    private Object discountId;
    @SerializedName("collection_id")
    @Expose
    private String collectionId;
    @SerializedName("seasonal_category_id")
    @Expose
    private String seasonalCategoryId;
    @SerializedName("product_short_description")
    @Expose
    private String productShortDescription;
    @SerializedName("product_primary_image")
    @Expose
    private String productPrimaryImage;
    @SerializedName("product_optional_image")
    @Expose
    @Ignore
    private Object productOptionalImage;
    @SerializedName("product_long_description")
    @Expose
    private String productLongDescription;
    @SerializedName("product_long_description_app")
    @Expose
    @Ignore
    private Object productLongDescriptionApp;
    @SerializedName("product_original_price")
    @Expose
    private String productOriginalPrice;
    @SerializedName("product_unit_price")
    @Expose
    private String productUnitPrice;
    @SerializedName("product_shipping_cost")
    @Expose
    private String productShippingCost;
    @SerializedName("product_minimum_order")
    @Expose
    private String productMinimumOrder;
    @SerializedName("product_measurement")
    @Expose
    private String productMeasurement;
    @SerializedName("product_unit")
    @Expose
    private String productUnit;
    @SerializedName("product_qty")
    @Expose
    private String productQty;
    @SerializedName("product_purchase_price")
    @Expose
    private String productPurchasePrice;
    @SerializedName("product_discount_percent")
    @Expose
    private String productDiscountPercent;
    @SerializedName("product_discount_amount")
    @Expose
    private String productDiscountAmount;
    @SerializedName("product_is_featured")
    @Expose
    private String productIsFeatured;
    @SerializedName("product_is_available")
    @Expose
    private String productIsAvailable;
    @SerializedName("product_is_publish")
    @Expose
    @Ignore
    private String productIsPublish;
    @SerializedName("product_is_discount")
    @Expose
    private String productIsDiscount;
    @SerializedName("product_is_collection")
    @Expose
    private String productIsCollection;
    @SerializedName("product_is_seasonal_category")
    @Expose
    private String productIsSeasonalCategory;
    @SerializedName("added_user_id")
    @Expose
    private String addedUserId;
    @SerializedName("updated_user_id")
    @Expose
    private String updatedUserId;
    @SerializedName("product_created_date")
    @Expose
    @Ignore
    private String productCreatedDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(String manufactureId) {
        this.manufactureId = manufactureId;
    }

    public Object getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Object discountId) {
        this.discountId = discountId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getSeasonalCategoryId() {
        return seasonalCategoryId;
    }

    public void setSeasonalCategoryId(String seasonalCategoryId) {
        this.seasonalCategoryId = seasonalCategoryId;
    }

    public String getProductShortDescription() {
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }

    public String getProductPrimaryImage() {
        return productPrimaryImage;
    }

    public void setProductPrimaryImage(String productPrimaryImage) {
        this.productPrimaryImage = productPrimaryImage;
    }

    public Object getProductOptionalImage() {
        return productOptionalImage;
    }

    public void setProductOptionalImage(Object productOptionalImage) {
        this.productOptionalImage = productOptionalImage;
    }

    public String getProductLongDescription() {
        return productLongDescription;
    }

    public void setProductLongDescription(String productLongDescription) {
        this.productLongDescription = productLongDescription;
    }

    public Object getProductLongDescriptionApp() {
        return productLongDescriptionApp;
    }

    public void setProductLongDescriptionApp(Object productLongDescriptionApp) {
        this.productLongDescriptionApp = productLongDescriptionApp;
    }

    public String getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public void setProductOriginalPrice(String productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductShippingCost() {
        return productShippingCost;
    }

    public void setProductShippingCost(String productShippingCost) {
        this.productShippingCost = productShippingCost;
    }

    public String getProductMinimumOrder() {
        return productMinimumOrder;
    }

    public void setProductMinimumOrder(String productMinimumOrder) {
        this.productMinimumOrder = productMinimumOrder;
    }

    public String getProductMeasurement() {
        return productMeasurement;
    }

    public void setProductMeasurement(String productMeasurement) {
        this.productMeasurement = productMeasurement;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPurchasePrice() {
        return productPurchasePrice;
    }

    public void setProductPurchasePrice(String productPurchasePrice) {
        this.productPurchasePrice = productPurchasePrice;
    }



    public String getProductIsFeatured() {
        return productIsFeatured;
    }

    public void setProductIsFeatured(String productIsFeatured) {
        this.productIsFeatured = productIsFeatured;
    }

    public String getProductIsAvailable() {
        return productIsAvailable;
    }

    public void setProductIsAvailable(String productIsAvailable) {
        this.productIsAvailable = productIsAvailable;
    }


    public String getProductIsDiscount() {
        return productIsDiscount;
    }

    public void setProductIsDiscount(String productIsDiscount) {
        this.productIsDiscount = productIsDiscount;
    }

    public String getProductIsCollection() {
        return productIsCollection;
    }

    public void setProductIsCollection(String productIsCollection) {
        this.productIsCollection = productIsCollection;
    }

    public String getProductIsSeasonalCategory() {
        return productIsSeasonalCategory;
    }

    public void setProductIsSeasonalCategory(String productIsSeasonalCategory) {
        this.productIsSeasonalCategory = productIsSeasonalCategory;
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

    public String getProductCreatedDate() {
        return productCreatedDate;
    }

    public void setProductCreatedDate(String productCreatedDate) {
        this.productCreatedDate = productCreatedDate;
    }

    public void setProductDiscountPercent(String productDiscountPercent) {
        this.productDiscountPercent = productDiscountPercent;
    }

    public void setProductDiscountAmount(String productDiscountAmount) {
        this.productDiscountAmount = productDiscountAmount;
    }

    public String getProductDiscountPercent() {
        return productDiscountPercent;
    }

    public String getProductDiscountAmount() {
        return productDiscountAmount;
    }

    public String getProductSearchTag() {
        return productSearchTag;
    }

    public void setProductSearchTag(String productSearchTag) {
        this.productSearchTag = productSearchTag;
    }
}
