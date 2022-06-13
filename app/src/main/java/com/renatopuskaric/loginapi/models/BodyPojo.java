package com.renatopuskaric.loginapi.models;

public class BodyPojo {

    private String email;  //"matija@mediatorium.co"
    private String password;  // "matija123"
    private String appId;

    public BodyPojo(String email, String password, String appId) {
        this.email = email;
        this.password = password;
        this.appId = appId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
