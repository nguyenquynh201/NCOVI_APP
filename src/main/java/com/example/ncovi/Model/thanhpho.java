package com.example.ncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class thanhpho {
    @SerializedName("matp")
    @Expose
    private String matp;
    @SerializedName("tentinh")
    @Expose
    private String tentinh;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("slug")
    @Expose
    private String slug;

    public thanhpho() {
    }


    public String getMatp() {
        return matp;
    }

    public void setMatp(String matp) {
        this.matp = matp;
    }

    public String getName() {
        return tentinh;
    }

    public void setName(String tentinh) {
        this.tentinh = tentinh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
