package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.Model.phananh;
import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackViewModel extends ViewModel {
    public MutableLiveData<phananh> mFeedback;

    public FeedbackViewModel() {
        mFeedback = new MutableLiveData<>();
    }

    public MutableLiveData<phananh> getFeedback() {
        return mFeedback;
    }
    public void iniInsertFeedback(String idMember,
                                  String th1,
                                  String th2,
                                  String th3,
                                  String noidung,
                                  String thoigian,
                                  String tinh,
                                  String huyen,
                                  String xa,
                                  String diachi
                                  ){
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<phananh> insertFeedBack = apiInterface.insertFeedback(idMember , th1, th2 ,th3 , noidung, thoigian,tinh , huyen , xa , diachi);
        insertFeedBack.enqueue(new Callback<phananh>() {
            @Override
            public void onResponse(Call<phananh> call, Response<phananh> response) {
                if(response.isSuccessful())
                {
                    mFeedback.setValue(response.body());
                }else {
                    mFeedback.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<phananh> call, Throwable t) {
                    mFeedback.setValue(null);

            }
        });
    }
}
