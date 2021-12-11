package com.example.ncovi.ViewModel.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;
    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder().readTimeout(10000, TimeUnit.MILLISECONDS)
            .writeTimeout(10000 ,TimeUnit.MILLISECONDS)
            .connectTimeout(10000 , TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .protocols(Arrays.asList(Protocol.HTTP_1_1));
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    public static Retrofit getApiClient(String url)
    {
        Gson gson = new GsonBuilder().setDateFormat("dd MM yyyy").setLenient().create();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}
