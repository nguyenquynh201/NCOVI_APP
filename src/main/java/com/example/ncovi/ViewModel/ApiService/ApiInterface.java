package com.example.ncovi.ViewModel.ApiService;

import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.Model.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("thanhpho.php")
    Call<List<thanhpho>> getThanhPho();

    @FormUrlEncoded
    @POST("huyen.php")
    Call<List<quanhuyen>> getQuanHuyen(@Field("matp") String matp);

    @FormUrlEncoded
    @POST("phuongxa.php")
    Call<List<phuongxa>> getPhuongXa(@Field("maqh") String maqh);

    @FormUrlEncoded
    @POST("login.php")
    Call<user> LoadUser(@Field("sdt") String sdt);
}
