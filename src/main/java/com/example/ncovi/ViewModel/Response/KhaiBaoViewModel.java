package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhaiBaoViewModel extends ViewModel {
    private MutableLiveData<String> insertKhaiBao;

    public KhaiBaoViewModel() {
        insertKhaiBao = new MutableLiveData<>();
    }

    public MutableLiveData<String> getInsertKhaiBao() {
        return insertKhaiBao;
    }

    public void iniInsertData(String idmember, String th1, String th2, String th3, String th4 , String time) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> insert = apiInterface.InsertKhaiBao(idmember, th1, th2, th3, th4  ,time);
        insert.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    insertKhaiBao.setValue(response.body());
                } else {
                    insertKhaiBao.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                insertKhaiBao.setValue(null);
            }
        });
    }
}
