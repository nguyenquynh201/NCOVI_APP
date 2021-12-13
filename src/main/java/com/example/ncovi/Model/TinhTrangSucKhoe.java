package com.example.ncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TinhTrangSucKhoe {
    @SerializedName("mask")
    @Expose
    private String mask;
    @SerializedName("idMember")
    @Expose
    private String idMember;
    @SerializedName("tinhtrangsuckhoe")
    @Expose
    private String tinhtrangsuckhoe;
    @SerializedName("canhbao")
    @Expose
    private String canhbao;
    @SerializedName("ngay")
    @Expose
    private String ngay;
    @SerializedName("gio")
    @Expose
    private String gio;

    public String getCanhbao() {
        return canhbao;
    }

    public void setCanhbao(String canhbao) {
        this.canhbao = canhbao;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getTinhtrangsuckhoe() {
        return tinhtrangsuckhoe;
    }

    public void setTinhtrangsuckhoe(String tinhtrangsuckhoe) {
        this.tinhtrangsuckhoe = tinhtrangsuckhoe;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }
}
