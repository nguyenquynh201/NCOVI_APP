package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.Model.login;
import com.example.ncovi.Model.user;
import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserApplyViewModel extends ViewModel {
    //register
    public MutableLiveData<user> mListUser;
    //long
    public MutableLiveData<login> mObjectUser;
    //checksdt
    public MutableLiveData<String> mCheck;
    //update member
    public MutableLiveData<login> upDateMember;

    public UserApplyViewModel() {
        mListUser = new MutableLiveData<>();
        mObjectUser = new MutableLiveData<>();
        mCheck = new MutableLiveData<>();
        upDateMember = new MutableLiveData<>();
    }

    public MutableLiveData<user> getListUser() {
        return mListUser;
    }

    public MutableLiveData<login> getObjectUser() {
        return mObjectUser;
    }

    public MutableLiveData<String> getCheck() {
        return mCheck;
    }

    public MutableLiveData<login> getUpDateMember() {
        return upDateMember;
    }

    // call register
    public void iniData(String name,
                        String sdt,
                        String gioitinh,
                        String cmnd,
                        String ngaysinh,
                        String idTinh,
                        String idHuyen,
                        String idXa,
                        String diachi
    ) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<user> callUser = apiInterface.LoadUser(name, sdt, gioitinh, cmnd, ngaysinh, idTinh, idHuyen, idXa, diachi);
        callUser.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if (response.isSuccessful()) {
                    mListUser.postValue(response.body());
                } else {
                    mListUser.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                mListUser.postValue(null);
            }
        });
    }

    // call login
    public void iniDataLogin(String sdt) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<login> loginCall = apiInterface.loginUser(sdt);
        loginCall.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                if (response.isSuccessful()) {
                    mObjectUser.setValue(response.body());
                } else {
                    mObjectUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
                mObjectUser.setValue(null);
            }
        });
    }
    // call check register thông qua số điện thoại
    public void intCheckSdt(String sdt) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> checkSdt = apiInterface.CheckSdt(sdt);
        checkSdt.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    mCheck.setValue(response.body());
                } else {
                    mCheck.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mCheck.setValue(null);
            }
        });
    }
    // update member
    public void iniDataUpdate(String idmember ,
                              String name,
                              String sdt,
                              String gioitinh,
                              String cmnd,
                              String ngaysinh,
                              String idTinh,
                              String idHuyen,
                              String idXa,
                              String diachi,
                              String email)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<login> callUpdate = apiInterface.updateMember(idmember,name, sdt, gioitinh, cmnd, ngaysinh, idTinh, idHuyen, idXa, diachi, email);
        callUpdate.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                if (response.isSuccessful())
                {
                    upDateMember.setValue(response.body());
                }
                else {
                    upDateMember.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
                upDateMember.setValue(null);
            }
        });
    }
}
