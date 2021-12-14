package com.example.ncovi.View.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ncovi.Class.WrapContentLinearLayoutManager;
import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Activity.Home;
import com.example.ncovi.View.Activity.ShowAllSucKhoeActivity;
import com.example.ncovi.View.Adaptor.TinhTrangSucKhoeAdaptor;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.TinhTrangViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class SucKhoeFragment extends Fragment {
    private TextView tv_show_all;
    private CheckBox checkBox_sk_1, checkBox_sk_2, checkBox_sk_3, checkBox_sk_4, checkBox_sk_5;
    private LinearLayout lnl_sk_1, lnl_sk_2, lnl_sk_3, lnl_sk_4, lnl_sk_5;
    ForegroundColorSpan foregroundColorSpan;
    private Button btn_send_sk;
    private View view;
    private Home home;
    private RecyclerView rcv_sk;
    private TinhTrangViewModel tinhTrangViewModel;
    private TinhTrangSucKhoeAdaptor tinhTrangSucKhoeAdaptor;
    private user user;
    private ArrayList<TinhTrangSucKhoe> ListtinhTrang;
    private String idMember , tinhtrang , canhbao , ngay , gio;
    private Date date;
    public SucKhoeFragment() {
        // Required empty public constructor
    }

    public static SucKhoeFragment newInstance(String param1, String param2) {
        SucKhoeFragment fragment = new SucKhoeFragment();
        return fragment;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_suckhoe, container, false);
        home = new Home();
        user = DataManager.loadUser();
        iniUi();
        return view;
    }

    private void iniUi() {
        // ánh xạ recyclerview
        rcv_sk = view.findViewById(R.id.rcv_sk);
        //ánh xạ checkbox
        checkBox_sk_1 = view.findViewById(R.id.cb_suckhoe_0);
        checkBox_sk_2 = view.findViewById(R.id.cb_suckhoe_1);
        checkBox_sk_3 = view.findViewById(R.id.cb_suckhoe_2);
        checkBox_sk_4 = view.findViewById(R.id.cb_suckhoe_3);
        checkBox_sk_5 = view.findViewById(R.id.cb_suckhoe_4);
        //ánh xạ textview
        tv_show_all = view.findViewById(R.id.tv_all);
        //set kiểu chữ cho edittext;
        UnderlineSpan underlineSpan = new UnderlineSpan();
        String strAll  = "Xem tất cả";
        SpannableString ss_all = new SpannableString(strAll);
        ss_all.setSpan(underlineSpan , 0 , 10 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_show_all.setText(ss_all);
        tv_show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , ShowAllSucKhoeActivity.class);
                getActivity().startActivity(intent);
            }
        });
        checkBox_sk_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox_sk_5.isChecked()) {
                    checkBox_sk_1.setChecked(false);
                    checkBox_sk_2.setChecked(false);
                    checkBox_sk_3.setChecked(false);
                    checkBox_sk_4.setChecked(false);

                }
            }
        });
        checkBox_sk_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox_sk_1.isChecked()) {
                    checkBox_sk_5.setChecked(false);
                }

            }
        });
        checkBox_sk_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox_sk_2.isChecked()) {
                    checkBox_sk_5.setChecked(false);
                }
            }
        });
        checkBox_sk_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox_sk_3.isChecked()) {
                    checkBox_sk_5.setChecked(false);
                }
            }
        });
        checkBox_sk_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox_sk_4.isChecked()) {
                    checkBox_sk_5.setChecked(false);
                }
            }
        });
        btn_send_sk = view.findViewById(R.id.btn_add_sk);
        btn_send_sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = Calendar.getInstance().getTime();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                ngay = dateFormat.format(date);
                gio = timeFormat.format(date);
                if (checkBox_sk_1.isChecked() || checkBox_sk_2.isChecked() || checkBox_sk_3.isChecked() || checkBox_sk_4.isChecked())
                {
                    canhbao = "Nguy Hiểm";
                    tinhtrang = "Có nguy cơ ";
                    DataManager.saveTinhTrang(ListtinhTrang);
                    Dialog_NguyHiem(Gravity.CENTER);
                    AddTinhTrang();
                    checkBox_sk_1.setChecked(false);
                    checkBox_sk_2.setChecked(false);
                    checkBox_sk_3.setChecked(false);
                    checkBox_sk_4.setChecked(false);


                }else if(checkBox_sk_5.isChecked())
                {
                    canhbao = "An Toàn";
                    tinhtrang = "Bình thường";
                    DataManager.saveTinhTrang(ListtinhTrang);
                    Dialog_AnToan(Gravity.CENTER);
                    AddTinhTrang();
                    checkBox_sk_1.setChecked(false);
                    checkBox_sk_2.setChecked(false);
                    checkBox_sk_3.setChecked(false);
                    checkBox_sk_4.setChecked(false);
                    checkBox_sk_5.setChecked(false);

                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        idMember = user.getIdMember();
        tinhTrangViewModel = new ViewModelProvider(getActivity()).get(TinhTrangViewModel.class);
        tinhTrangViewModel.getListTinhTrang().observe(getActivity(), new Observer<List<TinhTrangSucKhoe>>() {
            @Override
            public void onChanged(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
                ListtinhTrang = (ArrayList<TinhTrangSucKhoe>) tinhTrangSucKhoes;
                if (ListtinhTrang != null) {
                    DataManager.saveTinhTrang(tinhTrangSucKhoes);
                    loadData(ListtinhTrang);
                }
            }
        });
        tinhTrangViewModel.iniSelectList(idMember);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadData(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
        tinhTrangSucKhoeAdaptor = new TinhTrangSucKhoeAdaptor(getActivity());
        tinhTrangSucKhoeAdaptor.setDataTinhTrang(tinhTrangSucKhoes);
        rcv_sk.setHasFixedSize(true);
        rcv_sk.setLayoutManager(new WrapContentLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_sk.setAdapter(tinhTrangSucKhoeAdaptor);
    }

    private void AddTinhTrang() {
        idMember = user.getIdMember();
        tinhTrangViewModel.getAddListTinhTrang().observe(getActivity(), new Observer<List<TinhTrangSucKhoe>>() {
            @Override
            public void onChanged(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
                ListtinhTrang = (ArrayList<TinhTrangSucKhoe>) tinhTrangSucKhoes;
                if(ListtinhTrang!=null) {
                    loadData(ListtinhTrang);
                }
            }
        });
        tinhTrangViewModel.iniAddTinhTrang(idMember , tinhtrang , canhbao , ngay , gio);

    }
    private void Dialog_AnToan(int gravity)
    {
        Dialog dialog_antoan = new Dialog(getActivity());
        dialog_antoan.setCanceledOnTouchOutside(false);
        dialog_antoan.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_antoan.setContentView(R.layout.custom_dialog_antoan);
        Button btn_continue = dialog_antoan.findViewById(R.id.btn_continue);
        Window window = dialog_antoan.getWindow();
        if(window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT  ,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.loadTinhTrang();
                dialog_antoan.dismiss();
            }
        });
        dialog_antoan.show();

    }
    private void Dialog_NguyHiem(int gravity)
    {
        Dialog dialog_canhbao = new Dialog(getActivity());
        dialog_canhbao.setCanceledOnTouchOutside(false);
        dialog_canhbao.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_canhbao.setContentView(R.layout.custom_dialog_canhbao);
        Button btn_lienhe = dialog_canhbao.findViewById(R.id.btn_lienhe);
        Button btn_continue = dialog_canhbao.findViewById(R.id.btn_continue);
        Window window = dialog_canhbao.getWindow();
        if(window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT  ,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.loadTinhTrang();
                dialog_canhbao.dismiss();
            }
        });
        dialog_canhbao.show();

    }


}