package com.group.etoko.model;

public class UserModel {
    private boolean isLogin;
    private String name;
    private String email;
    private String phone;
    private String address;

    public UserModel(boolean isLogin, String name, String email, String phone, String address) {
        this.isLogin = isLogin;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
