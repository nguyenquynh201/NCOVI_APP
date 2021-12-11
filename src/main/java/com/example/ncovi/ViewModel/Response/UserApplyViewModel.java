package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.Model.user;
import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserApplyViewModel extends ViewModel {
    public MutableLiveData<user> mListUser;

    public UserApplyViewModel() {
        mListUser = new MutableLiveData<>();
    }

    public MutableLiveData<user> getListUser() {
        return mListUser;
    }
    public void iniData(String sdt)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<user> callUser = apiInterface.LoadUser(sdt );
        callUser.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if(response.isSuccessful())
                {
                    mListUser.setValue(response.body());
                }else{
                    mListUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                mListUser.setValue(null);
            }
        });
    }
}
