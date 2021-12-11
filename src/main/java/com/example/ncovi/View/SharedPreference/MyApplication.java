package com.example.ncovi.View.SharedPreference;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(getApplicationContext());
    }
}
