package com.example.ncovi.ViewModel.Response;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.Model.ModelCovid.covid;
import com.example.ncovi.ViewModel.ApiService.ApiInterfaceAPI;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidViewModel extends ViewModel {
    public MutableLiveData<covid> mCovid;
    public MutableLiveData<ArrayList<covid>> mSelectCovid;


    public CovidViewModel() {
        mCovid = new MutableLiveData<>();
        mSelectCovid = new MutableLiveData<>();
    }

    public MutableLiveData<covid> getCovid() {
        return mCovid;
    }

    public MutableLiveData<ArrayList<covid>> getSelectCovid() {
        return mSelectCovid;
    }

    public void iniDataCovid()
    {
        ApiInterfaceAPI apiInterfaceAPI = ApiService.apiInterfaceAPI();
        Call<covid> covidCall = apiInterfaceAPI.ApiCovid();
        covidCall.enqueue(new Callback<covid>() {
            @Override
            public void onResponse(Call<covid> call, Response<covid> response) {
                if(response.isSuccessful())
                {
                    mCovid.setValue(response.body());
                }else {
                    mCovid.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<covid> call, Throwable t) {
                mCovid.setValue(null);
            }
        });
    }
public void iniSelectDate()
{
    ApiInterfaceAPI apiInterfaceAPI = ApiService.apiInterfaceAPI();
    Call<ArrayList<covid>> allTheWorld = apiInterfaceAPI.SelectCovid();
    allTheWorld.enqueue(new Callback<ArrayList<covid>>() {
        @Override
        public void onResponse(Call<ArrayList<covid>> call, Response<ArrayList<covid>> response) {
            if (response.isSuccessful()){
                mSelectCovid.setValue(response.body());
            }else {
                mSelectCovid.setValue(null);
            }
        }

        @Override
        public void onFailure(Call<ArrayList<covid>> call, Throwable t) {
            mSelectCovid.setValue(null);

        }
    });
}
}
