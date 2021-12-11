package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.Model.phuongxa;
import com.example.ncovi.Model.quanhuyen;
import com.example.ncovi.Model.thanhpho;
import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressViewModel extends ViewModel {
    public MutableLiveData<List<thanhpho>> mListThanhPho;
    public MutableLiveData<List<quanhuyen>> mListQuanHuyen;
    public MutableLiveData<List<phuongxa>> mListPhuongXa;
    public AddressViewModel() {
        mListThanhPho = new MutableLiveData<>();
        mListQuanHuyen = new MutableLiveData<>();
        mListPhuongXa  = new MutableLiveData<>();
    }
    public MutableLiveData<List<thanhpho>> getListThanhPho(){
        return mListThanhPho;
    }
    public MutableLiveData<List<quanhuyen>> getListQuanHuyen(){
        return mListQuanHuyen;
    }

    public MutableLiveData<List<phuongxa>> getListPhuongXa() {
        return mListPhuongXa;
    }

    public void iniData(){
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<thanhpho>> call = apiInterface.getThanhPho();
        call.enqueue(new Callback<List<thanhpho>>() {
            @Override
            public void onResponse(Call<List<thanhpho>> call, Response<List<thanhpho>> response) {
                if(response.isSuccessful())
                {
                    mListThanhPho.setValue(response.body());
                }
                else {
                    mListThanhPho.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<thanhpho>> call, Throwable t) {
                mListThanhPho.setValue(null);
            }
        });
    }
    public void iniDataQuanHuyen(String matp){
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<quanhuyen>> call = apiInterface.getQuanHuyen(matp);
        call.enqueue(new Callback<List<quanhuyen>>() {
            @Override
            public void onResponse(Call<List<quanhuyen>> call, Response<List<quanhuyen>> response) {
                if(response.isSuccessful())
                {
                    mListQuanHuyen.setValue(response.body());
                }
                else {
                    mListQuanHuyen.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<quanhuyen>> call, Throwable t) {
                mListQuanHuyen.setValue(null);
            }
        });
    }
    public void iniDataPhuongXa(String maqh){
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<phuongxa>> call = apiInterface.getPhuongXa(maqh);
        call.enqueue(new Callback<List<phuongxa>>() {
            @Override
            public void onResponse(Call<List<phuongxa>> call, Response<List<phuongxa>> response) {
                if(response.isSuccessful())
                {
                    mListPhuongXa.setValue(response.body());
                }
                else {
                    mListPhuongXa.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<phuongxa>> call, Throwable t) {
                mListPhuongXa.setValue(null);
            }
        });
    }
}
