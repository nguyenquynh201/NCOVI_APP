package com.example.ncovi.View.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ncovi.Model.user;
import com.google.gson.Gson;

public class DataManager {
    private static final String OBJECT_USER = "OBJECT_USER";
    private static final String OBJECT_SDT = "OBJECT_SDT";
    private static DataManager instance;
    private static Context context;
    private MySharedPreference mySharedPreference;
    public static void init(Context context)
    {
        instance = new DataManager();
        instance.mySharedPreference = new MySharedPreference(context);
    }
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public static void saveUserName(user user) {
        Gson gson = new Gson();
        String srtUser = gson.toJson(user);
        DataManager.getInstance().mySharedPreference.putStringUser(OBJECT_USER, srtUser);
    }
    public static user loadUser() {
        String strUser = DataManager.getInstance().mySharedPreference.getStringUser(OBJECT_USER);
        Gson gson = new Gson();
        user user = gson.fromJson(strUser, user.class);
        return user;
    }
    //SDT
    public static void savePhone(String sdt)
    {
        DataManager.getInstance().mySharedPreference.putStringSDT(OBJECT_SDT, sdt);
    }
    public static String loadSDT()
    {
        return DataManager.getInstance().mySharedPreference.getStringSDT(OBJECT_SDT);
    }
}


