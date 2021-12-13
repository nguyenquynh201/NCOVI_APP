package com.example.ncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class login {
    @SerializedName("user")
    @Expose
    private user user;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
