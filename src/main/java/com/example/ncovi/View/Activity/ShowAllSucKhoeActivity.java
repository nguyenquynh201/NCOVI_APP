package com.example.ncovi.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.ncovi.Model.TinhTrangSucKhoe;
import com.example.ncovi.Model.user;
import com.example.ncovi.R;
import com.example.ncovi.View.Adaptor.TinhTrangSucKhoeAdaptor;
import com.example.ncovi.View.SharedPreference.DataManager;
import com.example.ncovi.ViewModel.Response.TinhTrangViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ShowAllSucKhoeActivity extends AppCompatActivity {
    private TinhTrangSucKhoeAdaptor tinhTrangSucKhoeAdaptor;
    private List<TinhTrangSucKhoe> mListSucKhoe;
    private RecyclerView recyclerView;
    private TinhTrangViewModel tinhTrangViewModel;
    private String idMember;
    private EditText edt_search;
    private Toolbar toolbar;
    private com.example.ncovi.Model.user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_suc_khoe);
        callback();
        user = DataManager.loadUser();
        idMember = user.getIdMember();
        iniUI();
        iniData();
    }

    private void iniUI() {
        edt_search = findViewById(R.id.edt_search);
        recyclerView = findViewById(R.id.rcv_show_all);
        // tạo định dạng cho date khi nhập giá  trị vào
        edt_search.addTextChangedListener(new TextWatcher() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchDate(s.toString());
            }
        });
    }
// hàm search
    public void searchDate(String date) {
        ArrayList<TinhTrangSucKhoe> mSucKhoe = new ArrayList<>();
        //so sánh 1 oject tình trạng với 1 list tình trạng
        for (TinhTrangSucKhoe tinhtrang : mListSucKhoe) {
            // kiểm tra điều kiện không phân biệt chữ hoa chữ thường
            if (tinhtrang.getNgay().toLowerCase(Locale.ROOT).contains(date.toLowerCase(Locale.ROOT))) {
                mSucKhoe.add(tinhtrang);
                TinhTrangSucKhoe tinhTrangSucKhoe;
            }
            tinhTrangSucKhoeAdaptor.searchTinhTrang(mSucKhoe);
        }
    }

    private void callback() {
        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void iniData() {
        tinhTrangViewModel = new ViewModelProvider(this).get(TinhTrangViewModel.class);
        tinhTrangViewModel.getShowAllListTinhTrang().observe(this, new Observer<List<TinhTrangSucKhoe>>() {
            @Override
            public void onChanged(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
                mListSucKhoe = tinhTrangSucKhoes;
                if (mListSucKhoe != null) {
                    loadListSucKhoe(mListSucKhoe);
                }

            }
        });
        tinhTrangViewModel.iniAllSelectList(idMember);
    }

    private void loadListSucKhoe(List<TinhTrangSucKhoe> tinhTrangSucKhoes) {
        tinhTrangSucKhoeAdaptor = new TinhTrangSucKhoeAdaptor(this);
        tinhTrangSucKhoeAdaptor.setDataTinhTrang(tinhTrangSucKhoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tinhTrangSucKhoeAdaptor);
    }
}