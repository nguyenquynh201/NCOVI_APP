package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinhTrangViewModel extends ViewModel {
    public MutableLiveData<List<TinhTrangSucKhoe>> mListTinhTrang;
    public MutableLiveData<List<TinhTrangSucKhoe>> AddListTinhTrang;
    public MutableLiveData<List<TinhTrangSucKhoe>> ShowAllListTinhTrang;

    public TinhTrangViewModel() {
        mListTinhTrang = new MutableLiveData<>();
        AddListTinhTrang = new MutableLiveData<>();
        ShowAllListTinhTrang = new MutableLiveData<>();
    }

    public MutableLiveData<List<TinhTrangSucKhoe>> getAddListTinhTrang() {
        return AddListTinhTrang;
    }

    public MutableLiveData<List<TinhTrangSucKhoe>> getListTinhTrang() {
        return mListTinhTrang;
    }

    public MutableLiveData<List<TinhTrangSucKhoe>> getShowAllListTinhTrang() {
        return ShowAllListTinhTrang;
    }

    public void iniSelectList(String idMember) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<TinhTrangSucKhoe>> listCall = apiInterface.ListTinhTrang(idMember);
        listCall.enqueue(new Callback<List<TinhTrangSucKhoe>>() {
            @Override
            public void onResponse(Call<List<TinhTrangSucKhoe>> call, Response<List<TinhTrangSucKhoe>> response) {
                if (response.isSuccessful()) {
                    mListTinhTrang.setValue(response.body());
                } else {
                    mListTinhTrang.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<TinhTrangSucKhoe>> call, Throwable t) {
                mListTinhTrang.setValue(null);

            }
        });
    }

    public void iniAllSelectList(String idMember) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<TinhTrangSucKhoe>> showCall = apiInterface.showListTinhTrang(idMember);
        showCall.enqueue(new Callback<List<TinhTrangSucKhoe>>() {
            @Override
            public void onResponse(Call<List<TinhTrangSucKhoe>> call, Response<List<TinhTrangSucKhoe>> response) {
                if (response.isSuccessful()) {
                    ShowAllListTinhTrang.setValue(response.body());
                } else {
                    ShowAllListTinhTrang.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<TinhTrangSucKhoe>> call, Throwable t) {
                ShowAllListTinhTrang.setValue(null);

            }
        });
    }

    public void iniAddTinhTrang(String idMember, String tinhtrang, String canhbao, String ngay, String gio) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<TinhTrangSucKhoe>> addList = apiInterface.AddListSucKhoe(idMember, tinhtrang, canhbao, ngay, gio);
        addList.enqueue(new Callback<List<TinhTrangSucKhoe>>() {
            @Override
            public void onResponse(Call<List<TinhTrangSucKhoe>> call, Response<List<TinhTrangSucKhoe>> response) {
                if (response.isSuccessful()) {
                    AddListTinhTrang.setValue(response.body());
                } else {
                    AddListTinhTrang.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<TinhTrangSucKhoe>> call, Throwable t) {
                AddListTinhTrang.setValue(null);
            }
        });
    }
}
