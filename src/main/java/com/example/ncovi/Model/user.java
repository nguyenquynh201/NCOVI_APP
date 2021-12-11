package com.example.ncovi.Model;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("idMember")
    @Expose
    private String idMember;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sdt")
    @Expose
    private String sdt;
    @SerializedName("gioitinh")
    @Expose
    private String gioitinh;
    @SerializedName("cmnd")
    @Expose
    private String cmnd;
    @SerializedName("ngaysinh")
    @Expose
    private String ngaysinh;
    @SerializedName("idTinh")
    @Expose
    private String idTinh;
    @SerializedName("idHuyen")
    @Expose
    private String idHuyen;
    @SerializedName("idXa")
    @Expose
    private String idXa;
    @SerializedName("diachi")
    @Expose
    private String diachi;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;

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
    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(String idTinh) {
        this.idTinh = idTinh;
    }

    public String getIdHuyen() {
        return idHuyen;
    }

    public void setIdHuyen(String idHuyen) {
        this.idHuyen = idHuyen;
    }

    public String getIdXa() {
        return idXa;
    }

    public void setIdXa(String idXa) {
        this.idXa = idXa;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isValidEmail()
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
