package com.example.ncovi.View.Activity;


import static com.example.ncovi.View.Activity.OTPActivity.SAVE_OPEN_APP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ncovi.R;
import com.example.ncovi.View.SharedPreference.MySharedPreference;

public class OnbroadingView_Activity extends AppCompatActivity {
private MySharedPreference mySharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onbroading_view);
        mySharedPreference = new MySharedPreference(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharedPreference.saveOpenApp(SAVE_OPEN_APP))
                {
                    starActivity(InformationActivity.class);
                }else {
                    starActivity(OnBroadingMain_Activity.class);
                    mySharedPreference.putBooleanValue(SAVE_OPEN_APP , true);
                }
            }
        }, 2000);
    }
    private void starActivity(Class<?> cls)
    {
        Intent intent = new Intent(this , cls);
        startActivity(intent);
        finish();
    }
}