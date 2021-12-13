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
    public MutableLiveData<user> mListUser;
    public MutableLiveData<login> mObjectUser;
    public UserApplyViewModel() {
        mListUser = new MutableLiveData<>();
        mObjectUser = new MutableLiveData<>();
    }

    public MutableLiveData<user> getListUser() {
        return mListUser;
    }

    public MutableLiveData<login> getObjectUser() {
        return mObjectUser;
    }

    public void iniData(String name ,
                        String sdt ,
                        String gioitinh ,
                        String cmnd ,
                        String ngaysinh ,
                        String idTinh ,
                        String idHuyen,
                        String idXa,
                        String diachi
                        )
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<user> callUser = apiInterface.LoadUser(name ,sdt , gioitinh , cmnd , ngaysinh , idTinh , idHuyen , idXa , diachi);
        callUser.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if(response.isSuccessful())
                {
                    mListUser.postValue(response.body());
                }else{
                    mListUser.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                mListUser.postValue(null);
            }
        });
    }
    public void iniDataLogin(String sdt)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<login> loginCall = apiInterface.loginUser(sdt);
        loginCall.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                if (response.isSuccessful())
                {
                    mObjectUser.setValue(response.body());
                }else{
                    mObjectUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
                mObjectUser.setValue(null);
            }
        });
    }
}
