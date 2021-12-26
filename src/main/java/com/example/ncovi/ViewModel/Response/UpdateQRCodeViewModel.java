package com.example.ncovi.ViewModel.Response;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ncovi.ViewModel.ApiService.ApiInterface;
import com.example.ncovi.ViewModel.ApiService.ApiService;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateQRCodeViewModel extends ViewModel {
    public MutableLiveData<MultipartBody.Part> updateQrCode;

    public UpdateQRCodeViewModel() {
        updateQrCode = new MutableLiveData<>();
    }

    public MutableLiveData<MultipartBody.Part> getUpdateQrCode() {
        return updateQrCode;
    }
    public void iniDate(MultipartBody.Part image)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> callImage = apiInterface.UploadQRCode(image);
        callImage.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
