package com.example.ncovi.View.SharedPreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static final String SHARE_USER = "SHARE_USER";
    private static final String SHARE_SDT = "SHARE_SDT";
    private static final String MY_OPEN_APP ="MY_OPEN_APP" ;
    private static final String SAVE_TINHTRANG ="SAVE_TINHTRANG" ;
    private Context context;

    public MySharedPreference(Context context) {
        this.context = context;
    }

    // load dữ liệu của member dựa vào idMeber;
    public String getStringUser(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getString("idMember", null);
    }

    //lưu dữ liệu user
    public void putStringUser(String key, String valueUser) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idMember", valueUser);
        editor.putString("name", valueUser);
        editor.putString("sdt", valueUser);
        editor.putString("gioitinh", valueUser);
        editor.putString("cmnd", valueUser);
        editor.putString("ngaysinh", valueUser);
        editor.putString("idTinh", valueUser);
        editor.putString("idHuyen", valueUser);
        editor.putString("idXa", valueUser);
        editor.putString("diachi", valueUser);
        editor.putString("email", valueUser);
        editor.apply();
    }

    //lưu dữ liệu số diện thoại
    public void putStringSDT(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_SDT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //load dữ liệu sdt
    public String getStringSDT(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_SDT, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
    // lưu lại dữ liệu lần đầu tiên mở app
    public void putBooleanValue(String key , boolean value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_OPEN_APP , 0);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key ,value);
        editor.apply();
    }
    // load dữ liệu lần đầu tiên mở app
    public boolean saveOpenApp(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_OPEN_APP, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
    // lưu danh sách tình trạng
    public void getTinhTrang(String key , String value)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SAVE_TINHTRANG , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idMember" , value);
        editor.putString("tinhtrangsuckhoe" , value);
        editor.putString("canhbao" , value);
        editor.putString("ngay" , value);
        editor.putString("gio" , value);
        editor.apply();
    }
    //load danh sach tình trạng
    public String setTinhTrang(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SAVE_TINHTRANG , Context.MODE_PRIVATE);
        return sharedPreferences.getString("idMember" , null);
    }
}
