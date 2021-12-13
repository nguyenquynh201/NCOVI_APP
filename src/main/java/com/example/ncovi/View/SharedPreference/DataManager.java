package com.example.ncovi.View.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.Model.user;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String OBJECT_USER = "OBJECT_USER";
    private static final String OBJECT_SDT = "OBJECT_SDT";
    private static final String OBEJCT_TINHTRANG = "OBEJCT_TINHTRANG";
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

    /*// danh sách tình trạng*/
    public static void saveTinhTrang(List<TinhTrangSucKhoe> tinhTrangSucKhoes)
    {
        Gson gson = new Gson();
        String strTinhtrang = gson.toJson(tinhTrangSucKhoes);
        DataManager.getInstance().mySharedPreference.getTinhTrang(OBEJCT_TINHTRANG , strTinhtrang);
    }
    public static List<TinhTrangSucKhoe> loadTinhTrang()
    {
        String strTinhtrang = DataManager.getInstance().mySharedPreference.setTinhTrang(OBEJCT_TINHTRANG);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TinhTrangSucKhoe>>() {
        }.getType();
        List<TinhTrangSucKhoe> mTinhTrang = gson.fromJson(strTinhtrang, type);
        return mTinhTrang;
    }
}


