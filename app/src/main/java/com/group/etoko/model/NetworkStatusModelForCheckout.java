package com.group.etoko.model;

public class NetworkStatusModelForCheckout {
    private boolean isUserMobile;
    private boolean isUserInfo;
    private boolean isShipingZone;
    private boolean isDeliveryMethod;
    private boolean isProductVat;


    public void setIsUserMobile(boolean isUserMobile) {
        this.isUserMobile = isUserMobile;
    }

    public void setUserInfo(boolean userInfo) {
        isUserInfo = userInfo;
    }

    public void setShipingZone(boolean shipingZone) {
        isShipingZone = shipingZone;
    }

    public boolean isUserMobile() {
        return isUserMobile;
    }

    public void setProductVat(boolean productVat) {
        isProductVat = productVat;
    }

    public void setUserMobile(boolean userMobile) {
        isUserMobile = userMobile;
    }

    public void setDeliveryMethod(boolean deliveryMethod) {
        isDeliveryMethod = deliveryMethod;
    }

    public boolean getStatus(){
        return isDeliveryMethod&&isShipingZone&&isUserInfo&&isUserMobile;
    }

    public void setAllFalse(Boolean statuse){
        isUserMobile=statuse;
       isUserInfo=statuse;
        isShipingZone=statuse;
        isDeliveryMethod=statuse;
        isProductVat=statuse;
    }
}
