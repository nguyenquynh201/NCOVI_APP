package com.example.ncovi.ViewModel.ApiService;

import com.example.ncovi.Model.ModelCovid.covid;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterfaceAPI {
    @GET("countries/vietnam")
    Call<covid> ApiCovid();
    @GET("countries")
    Call<ArrayList<covid>> SelectCovid();
}
