package com.example.ncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class phuongxa {
    @SerializedName("xaid")
    @Expose
    private String xaid;
    @SerializedName("tenxa")
    @Expose
    private String tenxa;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("maqh")
    @Expose
    private String maqh;

    public String getXaid() {
        return xaid;
    }

    public void setXaid(String xaid) {
        this.xaid = xaid;
    }

    public String getTenxa() {
        return tenxa;
    }

    public void setTenxa(String tenxa) {
        this.tenxa = tenxa;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaqh() {
        return maqh;
    }

    public void setMaqh(String maqh) {
        this.maqh = maqh;
    }
}
