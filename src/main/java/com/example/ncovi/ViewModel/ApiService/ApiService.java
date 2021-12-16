package com.example.ncovi.ViewModel.ApiService;

public class ApiService {
    public static String BASE_URL = "https://androidbookapp.000webhostapp.com/ncovi/";
    public static String BASE_URL_API = "https://corona.lmao.ninja/v2/";
    public static ApiInterface apiInterface(){
        return ApiClient.getApiClient(BASE_URL).create(ApiInterface.class);
    }
    public static ApiInterfaceAPI apiInterfaceAPI()
    {
        return ApiClient.getApiClient(BASE_URL_API).create(ApiInterfaceAPI.class);
    }


}
