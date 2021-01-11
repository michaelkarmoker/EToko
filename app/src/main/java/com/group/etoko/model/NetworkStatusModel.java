package com.group.etoko.model;

public class NetworkStatusModel {
    private boolean isProductStore;
    private boolean isProductQtyUpdated;

    public void setProductStore(boolean productStore) {
        isProductStore = productStore;
    }

    public void setProductQtyUpdated(boolean productQtyUpdated) {
        isProductQtyUpdated = productQtyUpdated;
    }

    public boolean getStatus(){
        return isProductStore  && isProductQtyUpdated;
    }
}