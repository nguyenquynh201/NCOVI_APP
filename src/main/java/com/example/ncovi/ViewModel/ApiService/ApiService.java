package com.example.ncovi.ViewModel.ApiService;

public class ApiService {
    public static String BASE_URL = "https://androidbookapp.000webhostapp.com/ncovi/";
    public static ApiInterface apiInterface(){
        return ApiClient.getApiClient(BASE_URL).create(ApiInterface.class);
    }

}
