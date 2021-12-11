package com.example.ncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class quanhuyen {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("maqh")
    @Expose
    private String maqh;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("matp")
    @Expose
    private String matp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaqh() {
        return maqh;
    }

    public void setMaqh(String maqh) {
        this.maqh = maqh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMatp() {
        return matp;
    }

    public void setMatp(String matp) {
        this.matp = matp;
    }

}
